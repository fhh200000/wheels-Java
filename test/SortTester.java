package test;

import defs.Cell;
import defs.Contact;
import lang.Sort;

public class SortTester {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		//测试排序无效的数组
		try {
			System.out.print("Sorting a normal var: ");
			Sort.mergeSort("aaa", false);
		}
		catch (UnsupportedOperationException ex) {
			System.out.println(ex);
		}
		//测试排序未实现Comparable接口的数组
		try {
			System.out.print("Sorting an array that is not comparable: ");
			Sort.mergeSort(new Cell[] {new Cell(1,2,3)}, false);
		}
		catch (UnsupportedOperationException ex) {
			System.out.println(ex);
		}
		System.out.println("Merge sorting a normal array:(1,3,-13,233,12,1-312,13,123,434,2,-1,3123,-132)");
		Integer[] testarray = {1,3,-13,233,12,1-312,13,123,434,2,-1,3123,-132};
		Sort.mergeSort(testarray);
		System.out.println(arrayToString(testarray));
		System.out.println("Merge sorting a normal array(reverse):");
		testarray = new Integer[]{1,3,-13,233,12,1-312,13,123,434,2,-1,3123,-132};
		Sort.mergeSort(testarray,true);
		System.out.println(arrayToString(testarray));
		System.out.println("Merge sorting a class array(Contacts):");
		Contact contacts[] = new Contact[] {
				new Contact("lhb","1231233"),
				new Contact("fhh","123456"),
				new Contact("szh","10086"),
				new Contact("pjh","4008823823"),
				new Contact("wx","10010"),
		};
		Sort.mergeSort(contacts);
		System.out.println(arrayToString(contacts));
		
		
		
		
		
		System.out.println("finished test.");
	}
	//数组转换为字符串的通用方法。
	private static String arrayToString(Object[] in) {
		sb.delete(0, sb.length());
		sb.append('(');
		for(Object i:in)
			sb.append(i+",");
		sb.replace(sb.length()-1,sb.length(),")");
		return sb.toString();
	}
}
