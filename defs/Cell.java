package defs;

import java.io.Serializable;

public class Cell implements Serializable{
	private static final long serialVersionUID = 1L;
	private int value;
	private boolean isVisited;
	public Cell(int value) {
		super();
		this.value = value;
		isVisited = false;
	}
	public int getValue() {
		return value;
	}
	public boolean isVisited() {
		return isVisited;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	
}
