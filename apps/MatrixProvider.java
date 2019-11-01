package apps;

import java.util.Scanner;

import defs.Triple;
import lang.SeqList;
import lang.SeqMatrix;

public class MatrixProvider {
	 @SuppressWarnings("unchecked")
	public static void main(String[] args) {
		 	Scanner sc = new Scanner(System.in);
			printmenu();
			Triple<Integer>[] testdata= new Triple[] {
					new Triple<Integer>(0,0,3),
					new Triple<Integer>(2,3,8),
					new Triple<Integer>(0,2,9),
					new Triple<Integer>(3,1,3),
					new Triple<Integer>(3,0,4)};
			//创建矩阵。
			SeqMatrix matrix = new SeqMatrix(5,4,testdata);
			System.out.println("默认矩阵已加载。");
			System.out.print("请输入您的选择：");
			loop:while(true) {
				char action = sc.next().charAt(0);
				switch(action) {
				case 'm':case 'M':{
					printmenu();
					break;
				}
				case 'p':case 'P':{
					System.out.println("当前存储的矩阵为：");
					System.out.println(matrix);
					break;
				}
				case 'n':case 'N':{
					System.out.println("请输入新矩阵的行数：");
					int row = sc.nextInt();
					System.out.println("请输入新矩阵的列数：");
					int column = sc.nextInt();
					SeqList<Triple<Integer>> temp = new SeqList<>();
					System.out.println("请按照\"行 列 数据\"的格式输入数据，-1退出。");
					int rowdata = sc.nextInt();
					while(rowdata!=-1) {
						int columndata = sc.nextInt();
						int rawdata = sc.nextInt();
						if(rowdata>=row||columndata>column) {
							System.out.println("无效输入：下标越界！");
							break;
						}
						temp.append(new Triple<Integer>(rowdata,columndata,rawdata));
						rowdata = sc.nextInt();
					}
					matrix = new SeqMatrix(row,column);
					for(Triple<Integer> i:temp)
						matrix.set(i);
					break;
				}
				case 'z':case 'Z':{
					System.out.println("转置完成的矩阵为：");
					matrix.fastTranspose();
					System.out.println(matrix);
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
					  "欢迎使用矩阵运算系统\n"
					+ "输入功能入口前的序号即可执行对应操作。\n"
					+ "m:显示此菜单。\n"
					+ "p:显示矩阵。\n"
					+ "n:设置新矩阵。\n"
					+ "z:快速转置矩阵。\n"
					+ "x:退出程序。");
		}
}
