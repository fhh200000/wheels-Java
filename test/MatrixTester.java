package test;

import lang.SeqMatrix;
import defs.Triple;

public class MatrixTester {
	public static void main(String[] args) {
		//创建测试数据。
		@SuppressWarnings("unchecked")
		Triple<Integer>[] testdata= new Triple[] {
				new Triple<Integer>(0,0,3),
				new Triple<Integer>(2,3,8),
				new Triple<Integer>(0,2,9),
				new Triple<Integer>(3,1,3),
				new Triple<Integer>(3,0,4)};
		//创建矩阵。
		SeqMatrix matrix = new SeqMatrix(5,4,testdata);
		System.out.println("Created Matrix:");
		System.out.println(matrix);
		//更改元素值。
		matrix.set(0, 2, 4);
		System.out.println("Changing (0,2,9) to (0,2,4):");
		System.out.println(matrix);
		//置0。
		matrix.set(0, 0, 0);
		System.out.println("Clearing (0,0,3):");
		System.out.println(matrix);
		//置数。
		matrix.set(2, 2, 6);
		System.out.println("Adding (2,2,6):");
		System.out.println(matrix);
		//测试插入错误下标数据。
		System.out.print("Trying to insert a wrong-labeled data: ");
		try {
			matrix.set(5,5,6);
		}
		catch(ArrayIndexOutOfBoundsException ex) {
			System.out.print(ex);
		}
		System.out.println();
		System.out.println("Trying to Add a Matrix: ");
		matrix.add(matrix);
		System.out.println(matrix);
		System.out.println("Fast Transposing a matrix: ");
		matrix.fastTranspose();
		System.out.println(matrix);
		System.out.println("Fast Transposing a matrix (Again!): ");
		matrix.fastTranspose();
		System.out.println(matrix);
		System.out.println("Finished test.");
	}

}
