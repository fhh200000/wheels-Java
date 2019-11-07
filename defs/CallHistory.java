package defs;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class CallHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date time;
	private String source,target;
	private String sourcename,targetname;
	public static final DateFormat dateformat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM, Locale.CHINA);
	public static final Comparator<CallHistory> SortByDate = new CallDateComparator();
	public static final Comparator<CallHistory> SortByName = new CallNameComparator();
	public String getTimeString() {
		return dateformat.format(time);
	}
	public Date getTime() {
		return time;
	}
	public String getSource() {
		return source;
	}
	public String getTarget() {
		return target;
	}
	public CallHistory(Date time, String source, String target) {
		super();
		this.time = time;
		this.sourcename = this.source = source;
		this.targetname = this.target = target;
	}
	public CallHistory(String source, String target) {
		this(new Date(),source,target);
	}
	public String getSourcename() {
		return sourcename;
	}
	public String getTargetname() {
		return targetname;
	}
	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}
	public void setTargetname(String targetname) {
		this.targetname = targetname;
	}
	@Override 
	public String toString() {
		return String.format("%s\t%s\t%s", getTimeString(),sourcename,targetname);
	}
	/*
	 * 按时间或按姓名排序。
	 * 此处使用Java自带的比较器接口控制排序依据。
	 */
	
}
class CallDateComparator implements Comparator<CallHistory> {
	@Override
	public int compare(CallHistory o1, CallHistory o2) {
		return o1.getTime().compareTo(o2.getTime());
	}
}
class CallNameComparator implements Comparator<CallHistory> {
	@Override
	public int compare(CallHistory o1, CallHistory o2) {
		return o1.getSourcename().compareTo(o2.getSourcename());
	}
}