package util;

import defs.TermX;
import lang.SortedSingleLinkedList;

public class Polynomial implements Comparable<Polynomial> {
	//创建主数组
	SortedSingleLinkedList<TermX> data;
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
	public int compareTo(Polynomial arg0) {
		// TODO 自动生成的方法存根
		return 0;
	}

}
