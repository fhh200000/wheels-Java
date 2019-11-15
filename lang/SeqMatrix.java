package lang;

import java.io.Serializable;
import java.util.Iterator;

import defs.Triple;

public class SeqMatrix implements Serializable{
	private static final long serialVersionUID = 1L;
	private SeqList<Triple<Integer>> data;
	private int rows,columns;
	public int defaultValue = 0;
	public int defaultCrossValue=0;
	public SeqMatrix(int rows,int columns) {
		if(rows<=0||columns<=0)
			throw new UnsupportedOperationException("Zero or negative size");
		this.rows = rows;
		this.columns = columns;
		//压缩算法，不需要预先分配所有的内存空间
		this.data = new SortedSeqList<>();
	}
	public SeqMatrix(int rows) {
		this(rows,rows);
	}
	public SeqMatrix(int rows,int columns,Triple<Integer>[] data) {
		this(rows,columns);
		for(Triple<Integer> i:data)
			set(i);
	}
	public int getRows() {
		return rows;
	}
	public int getColumns() {
		return columns;
	}
	public void set(int row,int column,int data) {
		set(new Triple<Integer>(row,column,data));
	}
	public void set(Triple<Integer> in) {
		/*
		 * 对教材代码的重写版本。
		 * 如果数字为“0”，则视为删除该位置的数据。
		 * 如果查找结果存在，则覆盖。
		 * 如果查找结果不存在，则尾部插入。
		 */
		if(in.getRow()<0||in.getRow()>=rows||in.getColumn()<0||in.getColumn()>=columns)
			throw new ArrayIndexOutOfBoundsException("("+in.getRow()+","+in.getColumn()+")");
		//先将虚拟下标转换为真实下标。
		int realcolumn = getRealPos(in.getRow(),in.getColumn());
		if(in.removable()) { //插入数值无效
			if(realcolumn!=-1) //设置位置有效
				data.remove(realcolumn);
			return;
		}
		else { //插入数值有效
			if(realcolumn!=-1)
				data.set(realcolumn,in);
			else
				data.append(in);
		}
	}
	public int getRealPos(int row,int column) {
		int i=0;
		while(i<data.size()&&(data.get(i).getColumn()!=column||data.get(i).getRow()!=row))
			i++;
		if(i==data.size())
			i = -1;
		return i;
	}
	public int get(int row,int column) {
		if(row<0||row>=rows|column<0||column>=columns)
			throw new ArrayIndexOutOfBoundsException("("+row+","+column+")");
		int result=getRealPos(row,column);
		if(result==-1)
			if(row==column)
				result=defaultCrossValue;
			else
				result=defaultValue;
		else
			result=data.get(result).getValue();
		return result;
	}
	@Override
	public String toString() {
		/*
		 * 对书本的toString方法的重写。
		 * （书本上的实在是太丑了…………）
		 */
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<rows;i++) {
			sb.append("| ");
			for(int j=0;j<columns;j++)
				sb.append(get(i,j)+" ");
			sb.append("|\n");
		}
		return sb.toString();
	}
	public void add(SeqMatrix in) {
		Iterator<Triple<Integer>> inIter = in.data.iterator();
		out:while(inIter.hasNext()) {
			Triple<Integer> inData = inIter.next();
				Iterator<Triple<Integer>> outIter = this.data.iterator();
				while(outIter.hasNext()) {
					if(outIter.next().add(inData)) 
						continue out;
				}
				//没有匹配的项目，将该项目添加到主链。
				this.data.append(inData);
			}
			//完成后，对多项式进行去0操作。
			trim();
	}
	public void trim() {
			for(Triple<Integer> i:data)
				if(i.removable())
					data.remove(i);
	}
	/*
	 * 矩阵的快速转置。
	 * 由于排序顺序表无法指定插入位置(不具有随机写能力)，这里采用数组进行临时转储。
	 */
	@SuppressWarnings("unchecked")
	public void fastTranspose() {
		Triple<Integer>[] newdata = new Triple[data.length]; 
		//记录每一列的长度。
		int[] columnlength = new int[columns];
		for(Triple<Integer> i:data)
			columnlength[i.getColumn()]++;
		//记录每一列(新行)的开始位置。
		int[] columnstartpos = new int[columns];
		for(int i=1;i<columns;i++) 
			columnstartpos[i]=columnstartpos[i-1]+columnlength[i-1];
		//开始转换。
		for(Triple<Integer> i:data) {
			newdata[columnstartpos[i.getColumn()]] = i.toSymmetry();
			columnstartpos[i.getColumn()]++;
		}
		
		/*
		 * 将转置完成的矩阵赋值给原数据。
		 * 由于我们十分确定原数组已经经过排序，此时可以采用特殊方法，将排序过的数据直接放回排序线性表。
		 * 此操作较为危险。
		 */
		//首先，我们交换行列长度。
		int tmp = rows;
		rows = columns;
		columns = tmp;
		//然后，我们强行将数据置入。
		data.data = newdata;
	}
	//扩充矩阵大小。
	public void expand(int rows,int columns) {
		this.rows = rows;
		this.columns = columns;
	}
	//直接拿走数据。
	public SeqList<Triple<Integer>> getRawData(){
		return data;
	}
}
