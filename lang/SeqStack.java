package lang;

public class SeqStack<T> implements Stack<T> {

	private T[] stackdata;
	private int stacksize;
	//指定默认栈大小。
	private static final int DEFAULTSIZE = 10;
    public boolean isEmpty() {
    	return stacksize==0;
    }
    @SuppressWarnings("unchecked")
	public SeqStack(int size) {
    	//直接创建一个比Integer的最大值还要大的栈很显然是行不通的。
    	if(size>Integer.MAX_VALUE-2)
    		throw new StackOverflowException(size);
    	this.stackdata = (T[])new Object[size];
    	this.stacksize=0;
    }
    public SeqStack() {
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
    	if(isEmpty())
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
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder("(");
    	for(int i=0;i<stacksize;i++)
    		sb.append(stackdata[i]+",");
    	sb.replace(sb.length()-1,sb.length(),")");
    	return sb.toString();
    };
}
