package lang;

public class SortedSingleLinkedList<T extends Comparable<? super T>> extends SingleLinkedList<T> {
	
	public SortedSingleLinkedList() {
		super();
	}
	public SortedSingleLinkedList(T[] data) {
		super();
		for(T i:data)
			insert(i);
	}
	public SortedSingleLinkedList(SortedSingleLinkedList<T> in) {
		super(in);
	}
	@SuppressWarnings("unchecked")
	public SortedSingleLinkedList(SeqList<T> in) {
		super();
		for(int i=0;i<in.length;i++) {
			this.insert((T)in.data[i]);
		}
	}
	public SortedSingleLinkedList(SingleLinkedList<T> in) {
		super();
		for(Node<T> p=in.head.next;p!=null;p=p.next)
			insert(p.data);
	}
	//由于列表包含顺序，所以不允许直接调用insert(int,T)方法插入。
	@Override
	public Node<T> insert(int pos,T data) {
		return this.insert(data);
	}
	//同样的，不允许直接调用set(int,T)方法改写。
	@Override
	public void set(int pos,T data) {
		if(pos >= size() || pos < 0)
			throw new ArrayIndexOutOfBoundsException(pos);
		if(data==null)
			throw new NullPointerException();
		//首先将pos位置的数据删除，再将新数据插入，来营造“修改”的假象。
		remove(pos);
		insert(data);	
	}
	@Override
	public Node<T> append(T indata) {
		//由于排序的append不被允许，因此直接调用insert。
		return insert(indata);
	}
	public Node<T> insert(T data) {
		Node<T> front = head,curr = head.next;
		//如果链表为空，那么直接插入
		if(curr==null) {
			head.next = new Node<T>(data,curr);
			return curr;
		}
		while(curr!=null&&data.compareTo(curr.data)>0)
		{
			front = curr;
			curr = curr.next;
		}
		return front.next = new Node<T>(data,curr);
		
	}
	//因为经过排序，我们直接导出为SortedSeqList,防止在读入时重新排序
	@Override
	public SeqList<T> toSeqList() {
		SeqList<T> val = new SortedSeqList<T>();
		Node<T> curr = head;
		while((curr=curr.next)!=null)
			val.append(curr.data);
		return val;
	}
}
