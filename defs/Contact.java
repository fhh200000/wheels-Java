package defs;

import java.io.Serializable;
import java.util.UUID;

public class Contact implements Serializable/*支持序列化从而实现导入/导出*/,Comparable<Contact>/*允许比较*/{
	private static final long serialVersionUID = 1L;
	private UUID uuid;
	private String name,phone;
	public Contact(UUID uuid,String name,String phone) {//完全输入
		this.uuid = uuid;
		this.name = name;
		this.phone = phone;
	}
	public Contact(String name,String phone) {//不完全输入
		this(UUID.randomUUID(),name,phone);
	}
	@Override
	public String toString() {
		return String.format("(Name:%s,Phone:%s)",name,phone);
	}
	public UUID getUuid() {
		return uuid;
	}
	public String getName() {
		return name;
	}
	public String getPhone() {
		return phone;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public boolean equals(Object in) {
		if(!(in instanceof Contact))/*类型不匹配，必然不相等*/
			return false;
		Contact indata = (Contact)in;
		if((indata.getUuid().equals(this.uuid))
		 ||(indata.getName().equals(name)
		  &&indata.getPhone().equals(phone)))/*GUID相等，或名称、电话号码相等时，为相等*/
			return true;
		return false;
	}
	@Override
	public int compareTo(Contact arg0) {
		/*排序原理：根据首字符的UCS-2数据进行排序。
		 在UCS-2表中，汉字按照字母顺序排列，因此可间接视作按照拼音排列。*/
		return this.name.charAt(0) - arg0.getName().charAt(0);
	}
}
