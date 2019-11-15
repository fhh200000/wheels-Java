package test;

import defs.Triple;
import lang.Graph;
import lang.MatrixGraph;
import util.MinSpanTree;

public class GraphTester {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Graph<String> test;
		//测试新建。
		String[] vertices= {"A","B","C","D","E"};
		Triple<Integer>[] edges = (Triple<Integer>[])(new Triple[]{
							new Triple<Integer>(0,1,45),new Triple<Integer>(0,2,28),new Triple<Integer>(0,3,10),
							new Triple<Integer>(1,0,45),new Triple<Integer>(1,2,12),new Triple<Integer>(1,4,21),
							new Triple<Integer>(2,0,28),new Triple<Integer>(2,1,45),new Triple<Integer>(2,3,17),
							new Triple<Integer>(3,0,10),new Triple<Integer>(3,2,17),new Triple<Integer>(3,4,15),
							new Triple<Integer>(4,1,21),new Triple<Integer>(4,2,26),new Triple<Integer>(4,3,15)				
		});
		test = new MatrixGraph<>(vertices,edges);
		System.out.println("Direct out:"+test);
		//测试插入。
		int i=test.insertVertex("F");
		test.insertEdge(3,i,13);
		test.insertEdge(new Triple<>(i,3,13));
		test.insertEdge(new Triple<>(4,i,11));
		test.insertEdge(new Triple<>(i,4,11));
		System.out.println("Insert test:"+test);
		
		//测试最小生成数。
		vertices= new String[]{"A","B","C","D","E","F"};
		edges = new Triple[] {
							new Triple<Integer>(0,1,26),new Triple<Integer>(0,2,28),new Triple<Integer>(0,3,15),
							new Triple<Integer>(1,2,17),new Triple<Integer>(1,4,13),
							new Triple<Integer>(2,3,25),new Triple<Integer>(2,4,21),new Triple<Integer>(2,5,14),
							new Triple<Integer>(3,5,18),
							new Triple<Integer>(4,5,16)
		};
		MinSpanTree mstree = new MinSpanTree(vertices.length,edges,MinSpanTree.getIterator());
		System.out.println("Result:"+mstree);
	}

}
