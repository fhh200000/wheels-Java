package lang;

import java.io.Serializable;

import defs.Triple;

public class MatrixGraph<T> extends Graph<T> implements Serializable{
	private static final long serialVersionUID = 9177678514180529835L;
	protected SeqMatrix matrix;
	public MatrixGraph(int count) {
		super(count);
		matrix = new SeqMatrix(count);
		/*
		 * 在图中，未被赋值的变量赋值以Infinity。
		 * 我们在这里设置了“没有赋值的变量”的默认值，从而减少了不必要的赋值次数。	
		 */
		matrix.defaultValue=MAX_WEIGHT;
	}
	public MatrixGraph() {
		this(10);
	}
	public MatrixGraph(T[] vertices) {
		this(vertices.length);
		for(T i:vertices)
			insertVertex(i);
	}
	public MatrixGraph(T[] vertices,Triple<Integer>[] edges) {
		this(vertices);
		for(Triple<Integer> i:edges)
			insertEdge(i);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("\n ");
		int n=vertexCount();
		for(T i:vertexlist)
			sb.append(String.format("%6s",i));
		sb.append('\n');
		for(int i=0;i<n;i++) {
			sb.append(vertexlist.get(i));
			for(int j=0;j<n;j++) {
				if(matrix.get(i,j)==MAX_WEIGHT)
					sb.append("     \u221E");
				else
					sb.append(String.format("%6d",matrix.get(i,j)));
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	public void insertEdge(int i,int j,int weight) {
		if(i!=j) {
			if(weight>=0||weight<MAX_WEIGHT) 
				matrix.set(i,j,weight);
		}
		else
			throw new UnsupportedOperationException("不能插入自身环。");
	}
	public void insertEdge(Triple<Integer> edge) {
		this.insertEdge(edge.getRow(),edge.getColumn(),edge.getValue());
	}
	@Override
	public int insertVertex(T x) {
		int i=vertexlist.append(x);
		if(i>=matrix.getRows()) {
			matrix.expand(i+1,i+1);
		}
		return i;
	}
	public void removeEdge(int i,int j) {
		if(i!=j)
			matrix.set(i,j,MAX_WEIGHT);
	}
	public void removeEdge(Triple<Integer> edge) {
		removeEdge(edge.getRow(),edge.getColumn());
	}
	@Override
	public void removeVertex(int i) {
		int n=vertexCount();
		if(i>0&&i<n) {
			vertexlist.remove(i);
			//TODO:稀疏矩阵的性能优化
			for(int j=i+1;j<n;j++)
				for(int k=0;k<n;k++)
					matrix.set(j-1,k,matrix.get(j,k));
			for(int j=0;j<n;j++)
				for(int k=i+1;k<n;k++)
					matrix.set(j,k-1,matrix.get(j,k));
			matrix.expand(n-1, n-1);
		}
		else
			throw new ArrayIndexOutOfBoundsException("i="+i);
	}

	@Override
	public int weight(int i, int j) {
		return matrix.get(i,j);
	}

	@Override
	protected int next(int i, int j) {
		//TODO:针对稀疏矩阵进行性能优化。
		int n = vertexCount();
		if(i>=0&&i<n&&j>-1&&j<n&&i!=j)
			for(int k=j+1;k<n;k++)
				if(matrix.get(i, k)>0&&matrix.get(i, k)<MAX_WEIGHT)
					return k;
		return -1;
	}
	public SeqList<Triple<Integer>> getRawData(){
		return matrix.getRawData();
	}
}
