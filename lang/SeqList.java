package lang;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

public class SeqList<T> implements Serializable,Iterable<T> /*支持序列化从而实现导入/导出*/{
	private static final long serialVersionUID = 1L;
	protected T[] data; 
	private static final int DEFAULT_LENGTH = 64; /*默认长度为64位*/
	int length;
	@SuppressWarnings("unchecked")
	public SeqList(int len){
		this.data = (T[])new Object[len];
		this.length = 0;
	}
	public SeqList() {
		this(DEFAULT_LENGTH); /*建立一个默认长度的顺序表*/
	}
	public SeqList(T[] values) {
		this(values.length);
		/*使用Java中的快速数组拷贝(Native Method)*/
		System.arraycopy(values,0,this.data,0,values.length);
		this.length = values.length;
	}
	public SeqList(SeqList<T> in) {
		this.data = in.data;
		this.length = in.length;
	}
	public boolean isEmpty() {
		return this.length==0;
	}
	public int size() {
		return this.length;
	}
	public T get(int pos) {
		if(pos<0||pos>length)
			return null;
		return (T)data[pos];
	}
	public void set(int pos,T indata) {
		if(indata==null)
			throw new NullPointerException("Trying to insert a null data");
		if(pos >= this.length || pos < 0)
			throw new ArrayIndexOutOfBoundsException(pos);
		this.data[pos] = indata;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getName());
		sb.append('(');
		if(this.length>0) {
			for(int i=0;i<this.length;i++) {
				sb.append(this.data[i].toString());
				if(i<this.length-1)
					sb.append(',');
			}
		}
		sb.append(')');
		return sb.toString();
	}
	public String toPreviousString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getName());
		sb.append('(');
		if(this.length>0) {
			for(int i=this.length-1;i>=0;i--) {
				sb.append(this.data[i].toString());
				if(i>0)
					sb.append(',');
			}
		}
		sb.append(')');
		return sb.toString();
	}
	public int insert(int pos,T indata) {
		int ret=0;
		if(pos<0)
			pos=0;
		if(pos>this.length)
			pos = this.length;
		if(pos<data.length-1 && pos == this.length) {//尾部附加
			data[pos]=indata;
			this.length++;
			return pos;
		}
		/*分配新数组*/
		@SuppressWarnings("unchecked")
		T[] newdata = (T[])new Object[this.length+1];
		/*将头部数据复制进新数组*/
		System.arraycopy(data, 0, newdata, 0, pos);
		newdata[pos] = indata;
		System.arraycopy(data, pos, newdata, pos+1,this.length-pos);
		this.data = newdata;
		newdata = null;
		this.length++;
		ret = pos;
		return ret;
	}
	public int append(T indata) {
		return this.insert(length, indata);
	}
	public T remove(int index) {
		T temp = get(index);
		if(temp!=null) { //在结构中存在
			System.arraycopy(data,index+1,data,index,this.length-index-1);
			this.length--;
		}
		return temp;
	}
	public void clear() {
		this.length = 0;
	}
	public int search(T key) {
		for(int i=0;i<this.length;i++) {
			if(key.equals(data[i]))
				return i;
		}
		return -1;
	}
	public boolean contains(T key) {
		return search(key)!=-1;
	}
	public int insertDifferent(T key) {
		if(contains(key))
			return -1;
		return append(key);	
	}
	public T remove(T key) {
		return remove(search(key));
	}
	@SuppressWarnings("unchecked")
	public T[] toArray() {
		T[] tmp = (T[]) new Object[length];
		System.arraycopy(data,0,tmp,0,length);
		return (T[])tmp;
	}
	@Override
	public Iterator<T> iterator() {
		// TODO 自动生成的方法存根
		return new SeqListIterator();
	}
	private class SeqListIterator implements Iterator<T>{
		int pos=-1;
		@Override
		public boolean hasNext() {
			return length-1!=pos;
		}
		@Override
		public T next() {
			// TODO 自动生成的方法存根
			return (T) data[++pos];
		}
	}
	/*
	 * 仿照Java的Collections.sort(Comparator)，实现依据比较器排序功能。
	 * 使用Shell排序算法进行数据排序。
	 */
	public void sort(Comparator<T> method) {
		for(int range=length/2;range>0;range/=2) {
			for(int i=range;i<length;i++) {
				T temp = data[i];
				int j;
				for(j=i-range;j>=0&&method.compare(temp,data[j])<0;j-=range)
					data[j+range] = data[j];
				data[j+range] = temp;
			}
		} 
	}
	//同时，提供不传入比较器的Comparable排序。
	public void sort() {
		T[] tmp = toArray();
		Sort.mergeSort(tmp);
		System.arraycopy(tmp, 0, data, 0, length);
	}
}
