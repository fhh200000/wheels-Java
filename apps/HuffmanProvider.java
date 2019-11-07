package apps;

import java.util.Scanner;

import lang.HuffmanTree;

public class HuffmanProvider {
	public static void main(String[] args) {
		HuffmanTree huffman=null;
		String text;
		Scanner sc = new Scanner(System.in);
		printmenu();
		System.out.print("请输入您的选择：");
		loop:while(true) {
			char action = sc.next().charAt(0);
			switch(action) {
			case 'm':case 'M':{
				printmenu();
				break;
			}
			case 'e':case 'E':{
				System.out.println("请输入字符串:");
				text = sc.next();
				huffman = new HuffmanTree(HuffmanTree.getKeywordFrequency(text));
				String result=huffman.encode(text);
				System.out.println("编码后的字符串为："+result+",共"+result.length()+"位。");
				break;
			}
			case 'd':case 'D':{
				if(huffman==null) {
					System.out.println("错误：未设置字符频率！");
					break;
				}
				System.out.println("请输入字符串:");
				text = sc.next();
				String result=huffman.decode(text);
				System.out.println("解码后的字符串为："+result+",共"+result.length()+"字节 。");
				break;
			}
			case 'x':case 'X':{
				break loop;
			}
			}
		System.out.println("请输入您的选择：");
		}
		sc.close();
	}
	public static void printmenu() {
		System.out.println(
				  "欢迎使用Huffman编解码系统\n"
				+ "输入功能入口前的序号即可执行对应操作。\n"
				+ "m:显示此菜单。\n"
				+ "e:统计频率并编码字符串。\n"
				+ "d:以当前频率解码字符串。\n"
				+ "x:退出程序。");
	}
}
