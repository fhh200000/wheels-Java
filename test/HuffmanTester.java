package test;

import lang.HuffmanTree;

public class HuffmanTester {
	public static void main(String[] args) {
		String text = "AAAABBBCDDBBAAA";
		int weight[] = HuffmanTree.getKeywordFrequency(text);
		HuffmanTree huffman = new HuffmanTree(weight);
		System.out.println(huffman);
		String compressed = huffman.encode(text);
		System.out.println("Compressing "+text+" to "+compressed+" with "+compressed.length()+" bits.");
		System.out.println("Decompressing "+compressed+" to "+huffman.decode(compressed)+".");
		System.out.println();
	}
}
