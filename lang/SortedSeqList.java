package lang;

public class SortedSeqList<T extends Comparable<? super T>> extends SeqList<T> {
	private static final long serialVersionUID = 1L;
	public SortedSeqList() {
		super();
	}
	public SortedSeqList(int length) {
		super(length);
	}
	@SuppressWarnings("unchecked")
	public SortedSeqList(SeqList<T> indata) {
		super(indata.length);
		//如果已经为排序表，则直接执行拷贝
		if(indata instanceof SortedSeqList) {
			this.data = indata.data;
		}
		//如果不是，则在导入时进行转化
		else {
			for(Object i:indata.data)
				insert((T)i);
		}
	}
	public SortedSeqList(T[] data) {
		super(data.length);
		//在此处直接执行插入
		for(T i:data)
			insert(i);
	}
	//由于列表包含顺序，所以不允许直接调用insert(int,T)方法插入。
	@Override
	public int insert(int pos,T data) {
		return this.insert(data);
	}
	//同样的，不允许直接调用set(int,T)方法改写。
	@Override
	public void set(int pos,T data) {
		if(pos > this.length || pos < 0)
			throw new ArrayIndexOutOfBoundsException(pos);
		if(data==null)
			throw new NullPointerException();
		//首先将pos位置的数据删除，再将新数据插入，来营造“修改”的假象。
		remove(pos);
		insert(data);	
	}
	@SuppressWarnings("unchecked")
	public int insert(T indata) {
		int pos=0;
		if(length==0||indata.compareTo((T)data[length-1])>0)
			pos = length;
		else {
			while(pos<length&&indata.compareTo((T)data[pos])>0)
				pos++;
		}
		return super.insert(pos, indata);
	}
	@Override
	public int append(T indata) {
		//由于排序的append不被允许，因此直接调用insert。
		return insert(indata);
	}
}
