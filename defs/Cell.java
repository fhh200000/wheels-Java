package defs;

import java.io.Serializable;

public class Cell implements Serializable{
	public static int width=6;
	public static int currcount=0;
	private static final long serialVersionUID = 1L;
	private char value;
	private int x,y;
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Cell(char value) {
		super();
		this.value = value;
		this.x = currcount%width;
		this.y = currcount/6;
		currcount++;
	}
	public char getValue() {
		return value;
	}
	public void setValue(char value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return new String(new char[]{(char)value});
	}
	public static void setWidth(int w) {
		width = w;
	}
}
