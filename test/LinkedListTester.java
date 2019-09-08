package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import defs.Contact;
import lang.SeqList;
import lang.SingleLinkedList;
import lang.SortedSeqList;
import lang.SortedSingleLinkedList;
import lang.Node;
@SuppressWarnings("unused")
public class LinkedListTester {
	public static void main(String[] args) {
		SingleLinkedList<Contact> test = new SortedSingleLinkedList<Contact>(
				new Contact[] { 
						new Contact("fhh", "123456"),
						new Contact("szh", "10086"), 
						new Contact("pjh", "4008823823"), 
						new Contact("wx", "10010")
				}
		);
		System.out.println("Direct out:" + test);
		// 替换某位
		Contact tmp = new Contact("lhb", "10000");
		test.set(2, tmp);
		System.out.println("Replace out:" + test);
		// 测试替换警告位（数位小于0)
		try {
			test.set(-1, tmp);
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("Replace with negative pos: " + ex.toString());
		}
		// 测试替换警告位（数位大于长度)
		try {
			test.set(test.size(), tmp);
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("Replace with exceeded pos: " + ex.toString());
		}
		// 测试替换警告位（无效内容)
		try {
			test.set(0, null);
		} catch (NullPointerException ex) {
			System.out.println("Insert with null data: " + ex.toString());
		}
		// 测试导出到文件
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(System.getProperty("java.class.path") + "/tstlink.info"));
			oos.writeObject(test.toSeqList());
			oos.flush();
			oos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Exported to file:" + System.getProperty("java.class.path") + "/tstlink.info");
		// 测试从文件导入
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream(System.getProperty("java.class.path") + "/tstlink.info"));
			@SuppressWarnings("unchecked")
			SeqList<Contact> test2 = (SeqList<Contact>) ois.readObject();
			System.out.println("If we are reading from SortedSeqList file: "
					+ Boolean.toString(test2 instanceof SortedSeqList));
			SingleLinkedList<Contact> testcontacts;
			if(test2 instanceof SortedSeqList)
				testcontacts = new SortedSingleLinkedList<Contact>(test2);
			else
				testcontacts = new SingleLinkedList<Contact>(test2);
			System.out.println("Imported data from file:" + testcontacts.toString());
			ois.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//测试插入
		test.insert(3,new Contact("hzc","130333"));
		System.out.println("Insert test:"+test);
		//测试附加
		test.append(new Contact("dzy","123132"));
		System.out.println("Append test:"+test);
		//测试移除
		test.remove(0);
		System.out.println("Remove test:"+test);
		//测试查找
		System.out.println("Search result of (hzc,130333) is:"+test.search(new Contact("hzc","130333")));
		System.out.println("Search result of (hzc,130) is:"+test.search(new Contact("hzc","130")));
		//测试不重复插入
		System.out.println("Exclusive insert of redundant data (hzc,130333) is:"+test.insertDifferent(new Contact("hzc","130333")));
		System.out.println("Exclusive insert of normal data (hzc,130) is:"+test.insertDifferent(new Contact("hzc","130")));
		//测试删除
		test.remove(new Contact("hzc","130"));
		System.out.println("Remove normal data (hzc,130) is:"+test);
		//完成测试：
		System.out.println("Finished test.");
	}
}
