package test;
import lang.SortedSeqList;
import lang.SeqList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import defs.Contact;
class ListTester {
	public static void main(String[] args) {
		Contact contacts[] = new Contact[] {
				new Contact("fhh","123456"),
				new Contact("szh","10086"),
				new Contact("pjh","4008823823"),
				new Contact("wx","10010"),
		};
		SeqList<Contact> test = new SortedSeqList<Contact>(contacts);
		//直接输出
		System.out.println("Direct out:"+test);
		//倒序输出
		System.out.println("Reverse out:"+test.toPreviousString());
		Contact tmp = new Contact("lhb","10000");
		//替换某位
		test.set(2,tmp);
		System.out.println("Replace out:"+test);
		//测试替换警告位（数位小于0)
		System.out.print("Replace with negative pos:");
		try {
			test.set(-1,tmp);
		}
		catch(ArrayIndexOutOfBoundsException ex) {
			System.out.print(ex.toString());
		}
		System.out.println();
		//测试替换警告位（数位大于长度)
		System.out.print("Replace with exceeded pos:");
		try {
			test.set(test.size(),tmp);
		}
		catch(ArrayIndexOutOfBoundsException ex) {
			System.out.print(ex.toString());
		}
		System.out.println();
		//测试替换警告位（无效内容)
		System.out.print("Insert with null data: ");
		try {
			test.set(0,null);
		}
		catch(NullPointerException ex) {
			System.out.print(ex.toString());
		}
		System.out.println();
		//测试导出到文件
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(System.getProperty("java.class.path")+"/tst.info"));
			oos.writeObject(test);
			oos.flush();
			oos.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Exported to file:"+System.getProperty("java.class.path")+"/tst.info");
		//测试从文件导入
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(System.getProperty("java.class.path")+"/tst.info"));
			@SuppressWarnings("unchecked")
			SeqList<Contact> testcontacts = (SeqList<Contact>)ois.readObject();
			System.out.println("If we are reading from SortedSeqList file: "+Boolean.toString(testcontacts instanceof SortedSeqList));
			System.out.println("Imported data from file:"+testcontacts.toString());
			ois.close();
		}
		catch(Exception ex) {
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