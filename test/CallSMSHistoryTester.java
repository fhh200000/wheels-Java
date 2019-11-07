package test;


import defs.CallHistory;
import defs.Contact;
import defs.SMSHistory;
import lang.SeqList;
import util.CallHistoryList;
import util.SMSHistoryList;

public class CallSMSHistoryTester {
	public static void main(String[] args) {
		//测试输出。
		CallHistory ch = new CallHistory("10010","10086");
		System.out.println("Direct out:"+ch);
		//测试比较。
		SeqList<CallHistory> histories = new SeqList<CallHistory>();
		histories.append(new CallHistory("10012","2222"));
		histories.append(ch);
		histories.append(new CallHistory("4008802801","2222"));
		histories.append(new CallHistory("1","2"));
		histories.sort(CallHistory.SortByDate);
		System.out.println("Sort by date:");
		for(CallHistory i:histories)
			System.out.println(i);
		System.out.println("Sort by name:");
		histories.sort(CallHistory.SortByName);
		for(CallHistory i:histories)
			System.out.println(i);
		//测试姓名替换。
		Contact[] contacts = new Contact[] {
				new Contact("温翾","10012"),
				new Contact("梁红兵","4008802801"),
				new Contact("潘炬豪","2222"),
				new Contact("中国联通","10010")
		};
		CallHistoryList chl = new CallHistoryList();
		System.out.println("Name replace:");
		System.out.println("Inserted "+chl.setContacts(contacts)+" contacts.");
		chl.setHistory(histories);		
		for(CallHistory i:chl.getCurrHistory())
			System.out.println(i);
		//测试短信
		SeqList<SMSHistory> smshistory = new SeqList<SMSHistory>();
		smshistory.append(new SMSHistory("10010","10012","今日无事可做。"));
		smshistory.append(new SMSHistory("4008802801","10012","今日没有学习Java。"));
		smshistory.append(new SMSHistory("10010","2222","您的手机已欠费。"));
		smshistory.append(new SMSHistory("10010","12222","准备数据结构重修。"));
		SMSHistoryList shl = new SMSHistoryList();
		shl.setContacts(contacts);
		shl.setHistory(smshistory);
		System.out.println("SMS test:");
		for(SMSHistory i:shl.getCurrHistory())
			System.out.println(i);
		System.out.println("Finished test.");
	}
}
