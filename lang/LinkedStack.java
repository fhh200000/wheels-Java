package lang;

public class LinkedStack<T> implements Stack<T>{
	private DoubleLinkedList<T> data;
	private int size;
	private Node<T> end;
	public LinkedStack() {
		data = new DoubleLinkedList<T>();
		end = data.head;
	}
	@Override
	public void push(T indata) {
		if(size>=Integer.MAX_VALUE-2)
    		throw new StackOverflowException(Integer.MAX_VALUE-2);
		data.append(indata);
		size++;
		end = end.next;
	}

	@Override
	public T pop() {
		if(size==0)
			throw new EmptyStackException();
		T ret = end.data;
		end = end.prev;
		size--;
		return ret;
	}

	@Override
	public T peek() {
		return end.data;
	}

	@Override
	public int size() {
		return size;
	}
	/* 遍历操作： 若栈非空，遍历栈中所有元素  */
    public String toString() {
    	StringBuilder sb = new StringBuilder("(");
    	for(T i:data)
    		sb.append(i+",");
    	sb.replace(sb.length()-1,sb.length(),")");
    	return sb.toString();
    };
}
