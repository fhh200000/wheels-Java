package apps;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import defs.Contact;
import lang.SeqList;
import lang.SingleLinkedList;
import lang.SortedSeqList;
import lang.SortedSingleLinkedList;
public class ContactsProvider {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		boolean isChecked=false;
		Scanner sc = new Scanner(System.in);
		char choice=0;
		Object data=null;
		while(!isChecked) {
		System.out.println("请选择后端格式：\n1.线性表\n2.排序线性表\n3.链表\n4.排序链表");
			switch(choice=sc.next().charAt(0)) {
			case '1':{data = new SeqList<Contact>();isChecked=true;break;}
			case '2':{data = new SortedSeqList<Contact>();isChecked=true;break;}
			case '3':{data = new SingleLinkedList<Contact>();isChecked=true;break;}
			case '4':{data = new SortedSingleLinkedList<Contact>();isChecked=true;break;}
			default:System.out.println("输入错误！");
			}
		}
		printmenu();
		System.out.print("请输入您的选择：");
		while((choice=sc.next().charAt(0))!='x'&&choice!='X') {
			switch(choice) {
			case 'N':case 'n':{
				System.out.println("请输入用户名：");
				String name=sc.next();
				System.out.println("请输入电话号码：");
				String phone=sc.next();
				System.out.println("请输入插入位置(-1为在尾部附加)：");
				int pos=sc.nextInt();
				if(data instanceof SeqList) {
					if(pos!=-1)
						((SeqList<Contact>)data).insert(pos, new Contact(name,phone));
					else
						((SeqList<Contact>)data).append(new Contact(name,phone));
				}
				else {
					if(pos!=-1)
						((SingleLinkedList<Contact>)data).insert(pos, new Contact(name,phone));
					else
						((SingleLinkedList<Contact>)data).append(new Contact(name,phone));
				}
				System.out.println("成功插入了联系人。");
				break;}
			case 'D':case 'd':{
				System.out.println("请输入待删除的联系人名称：");
				String name = sc.next();
				System.out.println("请输入待删除的电话号码：");
				String number = sc.next();
				Contact ret;
				if(data instanceof SeqList)  //顺序表
					ret = ((SeqList<Contact>)data).remove(new Contact(name,number));
				else
					ret = ((SingleLinkedList<Contact>)data).remove(new Contact(name,number));
				if(ret==null) 
					System.out.println("删除失败，未找到联系人！");
				else
					System.out.println("删除成功！");
				break;}
			case 'Q':case 'q':{
				System.out.println("当前的数据有：");
				//将数据保存至SeqList。
				SeqList<Contact> outdata;
				if(data instanceof SeqList)
					outdata = (SeqList<Contact>)data;
				else {
					outdata = new SeqList<Contact>(((SingleLinkedList<Contact>)data).toSeqList());
				}
				System.out.println("姓名\t电话号码");
				for(int i=0;i<outdata.size();i++) {
					System.out.println(outdata.get(i));
				}
				
				
				break;}
			case 'I':case 'i':{
				//当前版本中，只允许将数据文件保存至程序运行目录。
				File filelist = new File(System.getProperty("java.class.path"));
				String[] currfiles = filelist.list();
				System.out.println("当前存储的文件有：");
				String[] split;
				//列出所有文件
				for(String i:currfiles) {
					split = i.split("\\.");
					if(split[split.length-1].equals("contacts")) {
							System.out.print(i.substring(0,i.length()-9));
						System.out.println();
					}
				}
				System.out.println("请输入您希望读取的文件名：");
				String filename = sc.next();
				File outfile = new File(System.getProperty("java.class.path")+"/"+filename+".contacts");
				while(!outfile.exists()) {
					System.out.println("输入的文件不存在！\n请输入您希望读取的文件名：");
					filename = sc.next();
					outfile = new File(System.getProperty("java.class.path")+"/"+filename+".contacts");
				}
				try {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(outfile));
					if(data instanceof SeqList) { //线性表
						if(data instanceof SortedSeqList) //排序线性表
							data = new SortedSeqList<Contact>((SeqList<Contact>)ois.readObject());
						else
							data = new SeqList<Contact>((SeqList<Contact>)ois.readObject());
					}
					else { //链表
						if(data instanceof SortedSingleLinkedList) //排序单链表
							data = new SortedSingleLinkedList<Contact>((SeqList<Contact>)ois.readObject());
						else
							data = new SingleLinkedList<Contact>((SeqList<Contact>)ois.readObject());
					}
					ois.close();
					System.out.println("读取成功！");
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				break;}
			case 'S':case 's':{
				//当前版本中，只允许将数据文件保存至程序运行目录。
				File filelist = new File(System.getProperty("java.class.path"));
				String[] currfiles = filelist.list();
				System.out.println("当前存储的文件有：");
				String[] split;
				//列出所有文件
				for(String i:currfiles) {
					split = i.split("\\.");
					if(split[split.length-1].equals("contacts")) {
							System.out.print(i.substring(0,i.length()-9));
						System.out.println();
					}
				}
				System.out.println("请输入您希望保存的文件名：");
				String filename = sc.next();
				ObjectOutputStream oos;
				try {
				oos = new ObjectOutputStream(new FileOutputStream(System.getProperty("java.class.path")+"/"+filename+".contacts"));
				if(data instanceof SeqList) { //线性表
						oos.writeObject((SeqList<Contact>)data);
				}
				else { //链表
						oos.writeObject(((SingleLinkedList<Contact>)data).toSeqList());
				}
				oos.flush();
				oos.close();
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
				System.out.println("保存成功！");
				break;}
			case 'M':case 'm':{
				printmenu();
				break;}
			default:continue;
			}
			System.out.print("请输入您的选择：");
		}
		sc.close();
	}
	static void printmenu() {
		System.out.println(
		  "欢迎使用通讯录管理系统\n"
		+ "输入功能入口前的序号即可执行对应操作。\n"
		+ "m:显示此菜单。\n"
		+ "n:新建一条通讯录记录。\n"
		+ "d:删除一条通讯录记录。\n"
		+ "q:列出所有通讯录记录。\n"
		+ "i:导入通讯录存储文件。\n"
		+ "s:保存通讯录为本地文件。\n"
		+ "x:退出程序。");
	}
}
