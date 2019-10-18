package test;

import lang.LinkedStack;
import lang.SeqStack;
import lang.Stack;

public class StackTester {
	public static void main(String args[]) {
		Stack<Integer> teststack = new LinkedStack<Integer>();
		teststack.push(1);
		teststack.push(2);
		teststack.push(3);
		System.out.println("Created test stack:"+teststack);
		System.out.println("The peek of test stack is:"+teststack.peek());
		teststack.push(4);
		System.out.println("Push test:"+teststack);
		System.out.println("The peek of test stack is:"+teststack.peek());
		System.out.print("Pop test:");
		int size = teststack.size();
		for(int i=0;i<size;i++)
			System.out.print(teststack.pop()+",");
		System.out.println();
		try {
			System.out.print("Empty stack test: ");
			teststack.pop();
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
		try {
			System.out.print("Overflow test: ");
			teststack = new SeqStack<Integer>(Integer.MAX_VALUE-1);
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
		System.out.println("Finished test.");
	}
}
