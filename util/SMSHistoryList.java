package util;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import defs.CallHistory;
import defs.Contact;
import defs.SMSHistory;
import lang.SeqList;

public class SMSHistoryList implements Serializable {
	private static final long serialVersionUID = 2L;
	//此处采取SeqList存储通话信息。
	public enum Choice{Missed,Received,Dialout};
	private SeqList<SMSHistory> missedCalls = new SeqList<SMSHistory>();
	private SeqList<SMSHistory> receivedCalls = new SeqList<SMSHistory>();
	private SeqList<SMSHistory> dialoutCalls = new SeqList<SMSHistory>();
	private Map<String,String> nameMapping = new HashMap<String,String>(); //Map<电话号码，姓名>
	private SeqList<SMSHistory> selectCalls = missedCalls;
	public void changeSMS(Choice choice) {
		switch(choice) {
			case Missed:{selectCalls = missedCalls;break;}
			case Received:{selectCalls = receivedCalls;break;}
			case Dialout:{selectCalls = dialoutCalls;break;}
		}
	}
	/*
	 * 读入Contacts并将其转换为Map中的键值。
	 * 此处提供了实现Iterable的一切集合的转换方法。
	 */
	public int setContacts(Iterable<Contact> contacts) {
		int count=0;
		for(Contact i:contacts) {
			nameMapping.put(i.getPhone(),i.getName());
			count++;
		}
		return count;
	}
	//此处实现了数组的转换方法。
	public int setContacts(Contact[] contacts) {
		int count=0;
		for(Contact i:contacts) {
			nameMapping.put(i.getPhone(),i.getName());
			count++;
		}
		return count;
	}
	/*
	 * 插入CallHistory的方法。
	 * 此处提供了实现Iterable的一切集合的转换方法。
	 */
	public void setHistory(Iterable<SMSHistory> histories) {
		String name;
		for(SMSHistory i:histories) {
			selectCalls.append(i);
			if((name=nameMapping.get(i.getSource()))!=null)
				i.setSourcename(name);
			if((name=nameMapping.get(i.getTarget()))!=null)
				i.setTargetname(name);
		}
	}
	public SeqList<SMSHistory> getCurrHistory() {
		return selectCalls;
	}
	/*
	 * 排序通话记录的方法。
	 * 此处采取了传递比较器的方法进行排序。
	 */
	public void sort(Comparator<SMSHistory> method) {
		selectCalls.sort(method);
	}
	public void addHistory(SMSHistory in) {
		String name;
		//首先，我们将姓名替换到记录中。
		if((name=nameMapping.get(in.getSource()))!=null)
			in.setSourcename(name);
		if((name=nameMapping.get(in.getTarget()))!=null)
			in.setTargetname(name);
		//然后，我们将数据添加到列表中。
		selectCalls.append(in);
	}
}
