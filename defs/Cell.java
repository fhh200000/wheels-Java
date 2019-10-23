package defs;

import java.io.Serializable;

public class Cell implements Serializable{
	public static int width=6;
	public static int currcount=0;
	private boolean isVisited;
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
		return String.format("%c%d",(char)(y+'A'),x+1);
	}
	public static void setWidth(int w) {
		width = w;
	}
	public boolean checkAvail() {
		/*
		 * 不可用情形：
		 * 1.方块已访问。
		 * 2.方块为'*'。
		 */
		boolean result = !(isVisited||value=='*');
		isVisited=true;
		return result;
	}
	public void reset() {
		isVisited = false;
	}
}
