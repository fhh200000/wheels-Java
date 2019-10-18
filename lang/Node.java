package lang;
public class Node<T> {
	public T data;
	public Node<T> prev,next;
	public Node(T data,Node<T> next) { //单链表
		this.data = data;
		this.next = next;
	}
	public Node(T data,Node<T> prev,Node<T> next) { //双链表
		this.data = data;
		this.next = next;
		this.prev = prev;
	}
	public Node(Node<T> in) {
		this.data = in.data;
		this.prev = in.prev;
		this.next = in.next;
	}
	public Node() {
		this(null,null);
	}
	@Override
	public String toString() {
		return this.data.toString();
	}
}
