package lang;

public class Stack<T> {
	/*
	 * 栈的数组实现。
	 * 参考Java.util.Stack(Java.util.Vector)的写法。
	 * 使用加以改造的链表或许可以提高其性能。
	 * TODO:使用链表构造栈并进行复杂度分析。
	 */
	/* 判断是否为空栈；若为空，返回TRUE, 否则返回FALSE */
	private T[] stackdata;
	private int stacksize;
	//指定默认栈大小。
	private static final int DEFAULTSIZE = 10;
    public boolean isEmpty() {
    	return false;
    }
    @SuppressWarnings("unchecked")
	public Stack(int size) {
    	//直接创建一个比Integer的最大值还要大的栈很显然是行不通的。
    	if(size>Integer.MAX_VALUE-2)
    		throw new StackOverflowException(size);
    	this.stackdata = (T[])new Object[size];
    	this.stacksize=0;
    }
    public Stack() {
    	this(DEFAULTSIZE);
    }
    @SuppressWarnings("unchecked")
	public void push(T indata) {
    	//将信息放置到队尾，同时增加长度。
    	if((long)stacksize+DEFAULTSIZE>=Integer.MAX_VALUE-2)
    		throw new StackOverflowException(Integer.MAX_VALUE-2);
    	//如果数组长度不够，则进行扩列。
    	if(stacksize==stackdata.length) {
    		T[] stackdata = (T[])new Object[stacksize+DEFAULTSIZE];
    		/*将头部数据复制进新数组*/
    		System.arraycopy(this.stackdata, 0,stackdata, 0, stacksize);
    		this.stackdata = stackdata;
    	}
    	stackdata[stacksize] = indata;
    	stacksize++;
    }
    public T pop() {
    	//如果栈长度为0,则抛出异常。
    	if(stacksize==0)
    		throw new EmptyStackException();
    	return stackdata[--stacksize];
    };
    
    /* 返回栈顶元素，但并不将其弹出  */
    public T peek() {
    	return stackdata[stacksize-1];
    };
    
    /* 返回栈长度，即栈中元素的数目 */
    public int size() {
    	return stacksize;
    };
    
    /* 遍历操作： 若栈非空，遍历栈中所有元素  */
    public String toString() {
    	StringBuilder sb = new StringBuilder("(");
    	for(int i=0;i<stacksize;i++)
    		sb.append(stackdata[i]+",");
    	sb.replace(sb.length()-1,sb.length(),")");
    	return sb.toString();
    };
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