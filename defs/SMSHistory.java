package defs;

import java.text.DateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class SMSHistory {
	private Date time;
	private String source,target;
	private String sourcename,targetname;
	//由于短信只能发送文字（MMS才能发送特殊数据），此处在这里将数据类型设置成String.
	private String message;
	
	public Date getTime() {
		return time;
	}
	public String getSource() {
		return source;
	}
	public String getTarget() {
		return target;
	}
	public String getSourcename() {
		return sourcename;
	}
	public String getTargetname() {
		return targetname;
	}
	public String getMessage() {
		return message;
	}
	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}
	public void setTargetname(String targetname) {
		this.targetname = targetname;
	}
	public static final DateFormat dateformat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM, Locale.CHINA);
	public static final Comparator<SMSHistory> SortByDate = new SMSDateComparator();
	public static final Comparator<SMSHistory> SortByName = new SMSNameComparator();
}
class SMSDateComparator implements Comparator<SMSHistory> {
	@Override
	public int compare(SMSHistory o1, SMSHistory o2) {
		return o1.getTime().compareTo(o2.getTime());
	}
}
class SMSNameComparator implements Comparator<SMSHistory> {
	@Override
	public int compare(SMSHistory o1, SMSHistory o2) {
		return o1.getSourcename().compareTo(o2.getSourcename());
	}
}