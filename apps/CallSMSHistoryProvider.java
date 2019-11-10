package apps;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Comparator;
import java.util.Scanner;

import defs.CallHistory;
import defs.Contact;
import defs.SMSHistory;
import defs.Triple;
import lang.SeqList;
import lang.SeqMatrix;
import util.CallHistoryList;
import util.SMSHistoryList;

public class CallSMSHistoryProvider {
	static Scanner sc;
	static int selection = 0;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SMSHistoryList sms = new SMSHistoryList();
		CallHistoryList call = new CallHistoryList();
		sc = new Scanner(System.in);
		SeqList<Contact> contacts = null;
		//首先，我们导入通讯录。
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
				contacts = new SeqList<Contact>((SeqList<Contact>)ois.readObject());
			ois.close();
			System.out.println("读取成功！");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		call.setContacts(contacts);
		sms.setContacts(contacts);
		printmenu();
		System.out.print("请输入您的选择：");
		loop:while(true) {
			char action = sc.next().charAt(0);
			switch(action) {
			case 'm':case 'M':{
				printmenu();
				break;
			}
			case 'q':case 'Q':{
				selection = chooseCallSMS();
				break;
			}
			case 'n':case 'N':{
				if(selection==1) {
					System.out.println("正在插入一条通话记录");
					call.addHistory(new CallHistory(sc.next(),sc.next()));
				}
				else {
					System.out.println("正在插入一条短信记录");
					sms.addHistory(new SMSHistory(sc.next(),sc.next(),sc.next()));
				}
				System.out.println("插入成功！");
				break;
			}
			case 'c':case 'C':{
				int result=chooseEntry()-1;
				if(selection==1)
					call.changeCalls(CallHistoryList.Choice.values()[result]);
				else
					sms.changeSMS(SMSHistoryList.Choice.values()[result]);
				System.out.println("切换成功！");
				break;
			}
			case 'p':case 'P':{
				//排序。
				Comparator<?> method = chooseComparator();
				if(selection==1)
					call.sort((Comparator<CallHistory>)method);
				else
					sms.sort((Comparator<SMSHistory>)method);
				//显示。
				SeqList<?> result = selection==1?call.getCurrHistory():sms.getCurrHistory();
				for(Object i:result)
					System.out.println(i);
				break;
			}
			case 'd':case 'D':{
				System.out.println("当前加载的通讯录有：");
				for(Contact i:contacts)
					System.out.println(i);
				break;
			}
			case 'x':case 'X':{
				break loop;
			}
			}
		System.out.println("请输入您的选择：");
		}
		sc.close();
	}
	static void printmenu() {
		System.out.println(
				  "欢迎使用通话记录/短信记录管理系统\n"
				+ "输入功能入口前的序号即可执行对应操作。\n"
				+ "m:显示此菜单。\n"
				+ "p:输出排序后的通话记录/短信记录。\n"
				+ "c:切换入口(未接/已接/呼出)。\n"
				+ "d:显示联系人。\n"
				+ "q:切换通话记录/短信记录。\n"
				+ "n:添加通话记录/短信记录。\n"
				+ "x:退出程序。");
	}
	static int chooseCallSMS() {
		int ret = 0;
		while(ret!=1&&ret!=2) {
			System.out.println("请输入处理的对象：\n1.通话记录\n2.短信记录");
			ret = sc.nextInt();
		}
		return ret;
	}
	static int chooseEntry() {
		int ret = 0;
		while(ret!=1&&ret!=2&&ret!=3) {
			System.out.println("请输入处理的入口：\n1.未接/未读\n2.已接/已读\n3.呼出/发送");
			ret = sc.nextInt();
		}
		return ret;
	}
	static Comparator<?> chooseComparator(){
		int ret = 0;
		while(ret!=1&&ret!=2) {
			System.out.println("请输入排序依据：\n1.姓名\n2.通话/短信时间");
			ret = sc.nextInt();
		}
		if(selection==1) 
			return ret==1?CallHistory.SortByName:CallHistory.SortByDate;
		else
			return ret==1?SMSHistory.SortByName:SMSHistory.SortByDate;
	}
}
