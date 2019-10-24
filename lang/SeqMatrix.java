package lang;

import java.io.Serializable;
import java.util.Iterator;

import defs.Triple;

public class SeqMatrix implements Serializable{
	private static final long serialVersionUID = 1L;
	private int rows,columns;
	private SeqList<SortedSeqList<Triple<Integer>>> rowlist;
	public SeqMatrix(int rows,int columns) {
		if(rows<=0||columns<=0)
			throw new UnsupportedOperationException("Zero or negative size");
		this.rows = rows;
		this.columns = columns;
		this.rowlist = new SeqList<>(rows);
		for(int i=0;i<rows;i++)
			rowlist.append(new SortedSeqList<>());
	}
	public SeqMatrix(int rows) {
		this(rows,rows);
	}
	public SeqMatrix(int rows,int columns,Triple<Integer>[] data) {
		/*
		 */
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
		 * 如果数字为“0”，则直接不插入。
		 * 如果查找结果存在，则覆盖。
		 * 如果查找结果不存在，则尾部插入。
		 */
		if(in.getRow()<0||in.getRow()>=rows||in.getColumn()<0||in.getColumn()>=columns)
			throw new ArrayIndexOutOfBoundsException("("+in.getRow()+","+in.getColumn()+")");
		//先将虚拟下标转换为真实下标。
		int realcolumn = getRealPos(in.getRow(),in.getColumn());
		if(in.removable()) { //插入数值无效
			if(realcolumn!=-1) //设置位置有效
				rowlist.get(in.getRow()).remove(realcolumn);
			return;
		}
		else { //插入数值有效
			if(realcolumn!=-1)
				rowlist.get(in.getRow()).set(realcolumn, in);
			else
				rowlist.get(in.getRow()).append(in);
		}
	}
	public int get(int row,int column) {
		if(row<0||row>=rows|column<0||column>=columns)
			throw new ArrayIndexOutOfBoundsException("("+row+","+column+")");
		SortedSeqList<Triple<Integer>> currrow = rowlist.get(row);
		int result=getRealPos(row,column);
		if(result==-1)
			result=0;
		else
			result=currrow.get(result).getValue();
		return result;
	}
	public int getRealPos(int row,int column) {
		SortedSeqList<Triple<Integer>> currrow = rowlist.get(row);
		int i=0;
		while(i<currrow.size()&&currrow.get(i).getColumn()!=column)
			i++;
		if(i==currrow.size())
			i = -1;
		return i;
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
	/*
	 * 矩阵的AddAll实现。
	 * 由于没有实现书本上的相关接口，此处需要重新实现AddAll方法。
	 * 为了偷懒，此处使用迭代器简化代码量。
	 */
	public void add(SeqMatrix in) {
		//对两个多项式对象进行遍历。O(n²)
		//这里使用迭代器进行操作。
		for(int i=0;i<rowlist.length;i++) {
			SortedSeqList<Triple<Integer>> thisrow = rowlist.get(i);
			SortedSeqList<Triple<Integer>> inrow = in.rowlist.get(i);
			Iterator<Triple<Integer>> inIter = inrow.iterator();
		out:while(inIter.hasNext()) {
				Triple<Integer> inData = inIter.next();
				Iterator<Triple<Integer>> outIter = thisrow.iterator();
				while(outIter.hasNext()) {
					if(outIter.next().add(inData)) 
						continue out;
				}
				//没有匹配的项目，将该项目添加到主链。
				thisrow.append(inData);
			}
		}
		//完成后，对多项式进行去0操作。
		trim();
	}
	public void trim() {
		for(SortedSeqList<Triple<Integer>> row:rowlist)
			for(Triple<Integer> i:row)
				if(i.removable())
					row.remove(i);
	}
}
