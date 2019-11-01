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
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public boolean add(Triple<T> x) {
		//如果不能相加，那么直接抛出异常
		if(!(x.value instanceof Number)&&!(x.value instanceof Addible))
			throw new UnsupportedOperationException("Not an addible Type.");
		if(x.column!=column||x.row!=row)
			return false;
		if(value instanceof Number) {
			if(value instanceof Double) {
				value = (T) new Double((Double)value+(Double)x.value);
		    } else if(value instanceof Float) {
		    	value = (T) new Float((Float)value+(Float)x.value);
		    } else if(value instanceof Long) {
		    	value = (T) new Long((Long)value+(Long)x.value);
		    } else if(value instanceof Integer) {
		    	value = (T) new Integer((Integer)value+(Integer)x.value);
		    }
		    else if(value instanceof Byte) {
		    	value = (T) new Byte((byte) ((Byte)value+(Byte)x.value));
		    }
		    else if(value instanceof Short) {
		    	value = (T) new Short((short) ((Short)value+(Short)x.value));
		    }
		}
		else { //Addible
			((Addible<T>)value).add(x.value);
		}
		return true;
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
		if(value instanceof Addible)
			return ((Addible<?>) value).removable();
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
		return (this.row<arg0.row||this.row==arg0.row&&this.column<arg0.column)?-1:1;
	}
	public Triple<T> toSymmetry() {
		return new Triple<T>(this.column,this.row,this.value);
	}
}
