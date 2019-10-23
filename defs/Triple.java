package defs;
import lang.Addible;
/*
 * 对书本上的三元组的扩展写法。
 * 书本上的三元组仅支持标准类型（int）。
 * 此处将三元组扩展。 
 */
public class Triple<T> implements Comparable<Triple<T>>,Addible<Triple<T>>{
	int row,column;
	T value;
	@Override
	public boolean add(Triple<T> x) {
		
		return false;
	}
	public Triple(int row,int column,T value) {
		if(row<0||column<0)
			throw new ArrayIndexOutOfBoundsException(
					String.format("Indicators cannot be negative:(%d,%d)",row,column));
		this.row = row;
		this.column = column;
		this.value = value;
	}
	public Triple(Triple<T> in) {
		this(in.row,in.column,in.value);
	}
	@Override
	public String toString() {
		return String.format("(%d,%d,%d)",row,column,value);
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object in) {
		if(!(in instanceof Triple))
			return false;
		Triple<T> indata = (Triple<T>)in;
			return indata.column==column&&indata.row==row&&indata.value.equals(value);
	}
	@Override
	public boolean removable() {
		if(value instanceof Number) {
			if(value instanceof Integer||
					value instanceof Long||
					value instanceof Byte||
					value instanceof Short) 
				return ((Number)value).longValue()==0;
			else //java.lang.Float/Double
				return Math.abs(((Number)value).doubleValue())<0.0000001;
		}
		return value==null;
	}

	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
	public T getValue() {
		return value;
	}
	@Override
	public int compareTo(Triple<T> arg0) {
		if(this.row==arg0.row&&this.column==arg0.column)
			return 0;
		return (this.row<arg0.row||this.row==arg0.row&&this.column<arg0.column)?1:-1;
	}
	public Triple<T> toSymmetry() {
		return new Triple<T>(this.column,this.row,this.value);
	}
}
