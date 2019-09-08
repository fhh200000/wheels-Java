package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import defs.Contact;
import lang.SeqList;
import lang.SingleLinkedList;
import lang.SortedSingleLinkedList;

public class LinkedListTester {
	public static void main(String[] args) {
		SingleLinkedList<Contact> test = new SingleLinkedList<Contact>(
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
			SingleLinkedList<Contact> testcontacts = new SingleLinkedList<Contact>((SeqList<Contact>) ois.readObject());
			System.out.println("If we are reading from SortedSingleLinkedList file: "
					+ Boolean.toString(testcontacts instanceof SortedSingleLinkedList));
			System.out.println("Imported data from file:" + testcontacts.toString());
			ois.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
