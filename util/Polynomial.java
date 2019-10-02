package util;

import java.io.Serializable;
import java.util.Iterator;

import defs.TermX;
import lang.SortedSingleLinkedList;

public class Polynomial implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//创建主数组
	private SortedSingleLinkedList<TermX> data;
	
	public SortedSingleLinkedList<TermX> getData() {
		return data;
	}
	public Polynomial() {
		this.data = new SortedSingleLinkedList<TermX>();
	}
	public Polynomial(Polynomial in) {
		this.data = new SortedSingleLinkedList<TermX>(in.data);
	}
	public Polynomial(TermX[] in) {
		this.data = new SortedSingleLinkedList<TermX>(in);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(TermX i:data)
			sb.append(i);
		//如果起始数据为'+'，那么删除开头
		if(sb.charAt(0)=='+')
			sb.delete(0, 1);
		return sb.toString();
	}
	public void add(Polynomial in) {
		//对两个多项式对象进行遍历。O(n²)
		//这里使用迭代器进行操作。
		Iterator<TermX> inIter = in.getData().iterator();
	out:while(inIter.hasNext()) {
			TermX inData = inIter.next();
			Iterator<TermX> outIter = this.data.iterator();
			while(outIter.hasNext()) {
				if(outIter.next().add(inData)) 
					continue out;
			}
			//没有匹配的项目，将该项目添加到主链。
			this.data.append(inData);
		}
		//完成后，对多项式进行去0操作。
		trim();
	}
	public void trim() {
		/*
		 * 去除“0”的迭代器实现版本。
		 * 未使用迭代器时，需要对链表进行多次数据访问。
		 * 使用迭代器后，无需反复调用get()方法取得数据。
		 * 在顺序表中，使用迭代器时的性能提升不明显。
		 * 在链表中，使用迭代器可获得明显的性能提升。
		 * 以下注释代码为未使用迭代器的传统代码实现。
			int length = data.size();
			for(int i=0;i<length;i++) {
				TermX data = this.data.get(i);
				if(data.removable()) {
					this.data.remove(data);
					length--;
				}
			}
		 * 现在使用的是迭代器版本。
		 */
		
		for(TermX i:data)
			if(i.removable())
				this.data.remove(i);
	}
	
}
