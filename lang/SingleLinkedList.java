package lang;

import java.util.Iterator;

public class SingleLinkedList<T> implements Iterable<T>/*使用迭代器，遍历算法O(n²)-->O(n)*/{
	public Node<T> head;
	public SingleLinkedList() {
		this.head = new Node<T>();
	}
	public SingleLinkedList(T[] values) {
		this();
		Node<T> curr = head;//创建游标
		for(T i:values) {
			curr.next = new Node<T>(i,null);
			curr = curr.next;
		}
	}
	public SingleLinkedList(SingleLinkedList<T> in) {
		this(in.toSeqList().toArray());
	}
	public SingleLinkedList(SeqList<T> in) {
		this(in.toArray());
	}
	public boolean isEmpty() {
		return this.head.next==null;
	}
	public T get(int i) {
		if(i<0)//负数不被允许
			return null;
		Node<T> curr=this.head.next;
		for(int j=0;j<i&&curr!=null;j++)
			curr = curr.next;
		if(curr!=null)
			return curr.data;
		else
			return null;
	}
	public void set(int pos,T data) {
		if(data==null)
			throw new NullPointerException("Trying to insert a null data");
		if(pos < 0)
			throw new ArrayIndexOutOfBoundsException(pos);
		Node<T> curr = this.head;
		int tmp =size();
		if(tmp-1<pos) //pos长度大于链表长度
			throw new ArrayIndexOutOfBoundsException(pos);
		for(int j=1;j<tmp;j++)
			curr = curr.next;
		curr.data = data;
	}
	public int size() {
		Node<T> curr = this.head.next;
		int tmp=0;
		while(curr!=null) {
			curr = curr.next;
			++tmp;
		}
		return tmp;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getName()+'(');
		for(Node<T> curr=this.head.next;curr!=null;curr = curr.next) {
			sb.append(curr.data);
			if(curr.next!=null)//链表结尾
				sb.append(',');
		}
		sb.append(')');
		return sb.toString();
	}
	public SeqList<T> toSeqList() {
		SeqList<T> val = new SeqList<T>();
		Node<T> curr = head;
		while((curr=curr.next)!=null)
			val.append(curr.data);
		return val;
	}
	public Node<T> insert(int pos,T data) {
		if(data==null)
			throw new NullPointerException("Trying to insert a null data");
		Node<T> curr = this.head;
		if(pos<0)
			pos = 0;
		for(int i=0;i<pos&&curr.next!=null;i++)
			curr = curr.next;
		curr.next = new Node<T>(data,curr.next);
		return curr.next;
	}
	public Node<T> append(T data) {
		return insert(Integer.MAX_VALUE,data);
	}
	public T remove(int pos) {
		Node<T> curr = head;
		int i;
		for(i=0;curr.next!=null&&i<pos;i++)
			curr = curr.next;
		//若未找到，返回null
		if(i!=pos)
			return null;
		if(pos>=0 && curr.next!=null) {
			T ret = curr.next.data;
			curr.next = curr.next.next;
			return ret;
		}
		return null;
	}
	public void clear() {
		head.next = null;
	}
	public Node<T> search(T key){
		if(key==null)
			return null;
		Node<T> curr=head.next;
		while(curr.next!=null&&!(curr.data.equals(key))) 
			curr = curr.next;
		if(!(curr.data.equals(key)))
			return null;
		return curr;
	}
	public boolean contains(T key) {
		return search(key)!=null;
	}
	public Node<T> insertDifferent(T indata) {
		if(contains(indata))
			return null;
		return append(indata);	
	}
	public T remove(T key) {
		/*int count=0;
		Node<T> data = search(key),curr=head.next;
		if(data==null)
			return null;
		while(curr.next!=null&&!(curr.equals(data))) {
			curr = curr.next;
			count++;
		}
		return remove(count);*/
		//使用迭代器实现的版本。
		Iterator<T> iter = iterator();
		T ret = null,tmp=null;
		while(iter.hasNext()) {
			tmp = iter.next();
			if(tmp.equals(key)) {
				ret = tmp;
				iter.remove();
			}
		}
		return ret;
	}
	@Override
	public Iterator<T> iterator() {
		// TODO 自动生成的方法存根
		return new LinkedListIterator();
	}
	private class LinkedListIterator implements Iterator<T>{
		private Node<T> curr = head;
		private Node<T> prev = head;
		@Override
		public boolean hasNext() {
			return curr.next!=null;
		}
		@Override
		public T next() {
			// TODO 自动生成的方法存根
			prev = curr;
			curr = curr.next;
			return curr.data;
		}
		@Override
		public void remove() {
			prev.next =prev.next.next;
		}
	}
}
