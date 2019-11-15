package util;

import java.util.Comparator;

import defs.Triple;
import lang.Heap;
import lang.UnionFindSet;

public class MinSpanTree {
	private Object[] mst;
	private int cost=0;
	public MinSpanTree(int n,Triple<Integer>[] edges,Comparator<Triple<Integer>> cmp) {
		mst = new Object[n-1];
		Heap<Triple<Integer>> minheap = new Heap<Triple<Integer>>(true,edges,cmp);
		UnionFindSet ufs = new UnionFindSet(n);
		System.out.println("Using UnionFindSet:"+ufs+",Min heap:"+minheap);
		int i=0;
		for(int j=0;j<n;j++) {
			Triple<Integer> minedge = minheap.removeRoot();
			System.out.println("Processing Min Edge:"+minedge);
			if(ufs.union(minedge.getRow(),minedge.getColumn())) {
				mst[i++] = minedge;
				cost += minedge.getValue();
				System.out.println("Inserting edge:"+minedge+",Union Find Set:"+ufs);
			}
		}
	}
	public static Comparator<Triple<Integer>> getIterator() {
		return new TripleComparator();
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Edges of Min Spawn Tree:");
		for(Object i:mst)
			sb.append(i+",");
		sb.delete(sb.length()-1, sb.length()+1);
		sb.append(",Minimum cost=");
		sb.append(cost);
		return sb.toString();
	}
}
class TripleComparator implements Comparator<Triple<Integer>>{
	@SuppressWarnings("rawtypes")
	@Override
	public int compare(Triple o1, Triple o2) {
		return (int)o1.getValue() - (int)o2.getValue();
	}
	
}
