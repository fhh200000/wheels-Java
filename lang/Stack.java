package lang;


public interface Stack<T>{
	public void push(T indata);
	public T pop();
	public T peek();
	public int size();
	public void clear();
	/* 遍历操作： 若栈非空，遍历栈中所有元素  */
}
class StackOverflowException extends RuntimeException{
	/*
	 * 堆栈溢出的异常处理程序。
	 * 一般而言，int类型的堆栈不会遇到溢出错误。
	 * 但，当堆栈溢出时，程序运行结果将无法确定，故声明为运行时异常交由用户处理。
	 */
	private static final long serialVersionUID = 1L;
	public StackOverflowException(int size) {
		super(String.format("堆栈溢出：%d", size));
	}
}
class EmptyStackException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public EmptyStackException() {
		super("空栈");
	}
}