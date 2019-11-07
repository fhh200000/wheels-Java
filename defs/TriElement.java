package defs;

public class TriElement<T extends Comparable<T>> {
	T data;
	int parent,left,right;
	public T getData() {
		return data;
	}
	public int getParent() {
		return parent;
	}
	public int getLeft() {
		return left;
	}
	public int getRight() {
		return right;
	}
	public void setData(T data) {
		this.data = data;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public void setRight(int right) {
		this.right = right;
	}
	public TriElement(T data,int parent,int left,int right) {
		this.data = data;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
	public TriElement(T data) {
		this(data,-1,-1,-1);
	}
	@Override
	public String toString() {
		return String.format("(%s,%d,%d,%d)",data,parent,left,right);
	}
	public boolean isLeaf() {
		return this.left==-1&&this.right==-1;
	}
}

