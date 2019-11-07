package test;


import defs.CallHistory;
import defs.Contact;
import lang.SeqList;
import util.CallHistoryList;

public class CallHistoryTester {
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
				new Contact("潘炬豪","2222")
		};
		CallHistoryList chl = new CallHistoryList();
		System.out.println("Name replace:");
		System.out.println("Inserted "+chl.setContacts(contacts)+" contacts.");
		chl.setHistory(histories);		
		for(CallHistory i:chl.getCurrHistory())
			System.out.println(i);
	}
}
