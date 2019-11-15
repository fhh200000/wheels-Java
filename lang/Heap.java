package lang;

import java.util.Comparator;

public class Heap<T> {
	private boolean minheap;
	private SeqList<T> heap;
	private Comparator<T> cmp;
	public Heap(boolean minheap,Comparator<T> cmp) {
		this.minheap = minheap;
		heap = new SeqList<T>();
		this.cmp = cmp;
	}
	public Heap(boolean minheap) {
		this(minheap,null);
	}
	public Heap() {
		this(true,null);
	}
	public Heap(boolean minheap,T[] values,Comparator<T> cmp) {
		this(minheap,cmp);
		for(T i:values)
			insert(i);
	}
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	public int size() {
		return heap.size();
	}
	@Override
	public String toString() {
		return heap.toString();
	}
	public void insert(T indata) {
		heap.append(indata);
		for(int i=heap.size()/2-1;i>0;i=(i-1)/2) {
			sift(i);
		}
		sift(0);
	}
	@SuppressWarnings("unchecked")
	private void sift(int parent) {
		int end=size()-1;
		int child=parent*2+1;
		T value=heap.get(parent);
		while(child<=end) {
			int comp=0;
			if(child<end) {
				T left=heap.get(child),right=heap.get(child+1);
				if(cmp==null)
					comp = ((Comparable<T>)left).compareTo(right);
				else
					comp = cmp.compare(left, right);
				if(minheap?comp>0:comp<0)
					child++;
			}
			if(cmp==null)
				comp = ((Comparable<T>)value).compareTo(heap.get(child));
			else
				comp = cmp.compare(value, heap.get(child));
			if(minheap?comp>0:comp<0) {
				heap.set(parent,heap.get(child));
				parent = child;
				child = 2*parent+1;
			}
			else
				break;
		}
		heap.set(parent, value);
	}
	public T removeRoot() {
		if(isEmpty()) {
			return null;
		}
		T x=heap.get(0);
		heap.set(0, heap.get(heap.size()-1));
		heap.remove(heap.size()-1);
		if(heap.size()>1)
			sift(0);
		return x;
	}
}
