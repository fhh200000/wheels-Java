package lang;
public class Node<T> {
	public T data;
	public Node<T> next;
	public Node(T data,Node<T> next) {
		this.data = data;
		this.next = next;
	}
	public Node(Node<T> in) {
		this.data = in.data;
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
