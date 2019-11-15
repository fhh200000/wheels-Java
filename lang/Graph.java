package lang;

import defs.Triple;

public abstract class Graph<T> {
	static final int MAX_WEIGHT=0xffff;
	SeqList<T> vertexlist;
	public Graph(int length) {
		vertexlist = new SeqList<T>(10);
	}
	public Graph() {
		this(10);
	}
	public int vertexCount() {
		return vertexlist.size();
	}
	@Override
	public String toString() {
		return "顶点集合："+vertexlist;
	}
	public T getVertex(int i) {
		return vertexlist.get(i);
	}
	public void setVertex(int i,T x) {
		vertexlist.set(i, x);
	}
	public void addVertex(T x) {
		vertexlist.append(x);
	}
	public abstract int insertVertex(T x);
	public abstract void removeVertex(int i);
	public abstract void insertEdge(int i,int j,int weight);
	public abstract void insertEdge(Triple<Integer> edge);
	public abstract int weight(int i,int j);
	public abstract SeqList<Triple<Integer>> getRawData();
	protected abstract int next(int i,int j);
}
