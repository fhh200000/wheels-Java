package lang;

public class RecycledSingleLinkedList<T> extends SingleLinkedList<T> {
	//重写构造方法
	public RecycledSingleLinkedList() {
		super();
	}
	public RecycledSingleLinkedList(T[] values) {
		super();
		int i=0;
		while(i<values.length-1)
			super.append(values[i]);
		Node<T> last = super.append(values[++i]);
		last.next = super.head.next;
	}
	public RecycledSingleLinkedList(SingleLinkedList<T> in) {
		this(in.toSeqList().toArray());
	}
	public RecycledSingleLinkedList(SeqList<T> in) {
		this(in.toArray());
	}
	//循环单链表特有操作：闭环。
	public void closeCircle() {
		//定位到尾部节点。
		Node<T> curr = head.next;
		while(curr.next!=null)
			curr = curr.next;
		curr.next = head.next;
		
	}
}
