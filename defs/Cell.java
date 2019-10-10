package defs;

public class Cell {
	private int x,y,value;
	private boolean isVisited;
	public Cell(int x, int y, int value) {
		super();
		this.x = x;
		this.y = y;
		this.value = value;
		isVisited = false;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getValue() {
		return value;
	}
	public boolean isVisited() {
		return isVisited;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	
}
