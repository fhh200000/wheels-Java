package apps;

import java.util.Scanner;

public class Maze {
	public static void main(String args[]) {
		//创建迷宫数据。
		char [][] mazearr = 
			   {{'*','*','*','*','*','*','*','*','*','*'},
                {'*',' ',' ','*',' ',' ',' ','*',' ','*'},
                {'*',' ',' ','*',' ',' ',' ','*',' ','*'},
                {'*',' ',' ',' ',' ','*','*',' ',' ','*'},
                {'*',' ','*','*','*',' ',' ',' ',' ','*'},
                {'*',' ',' ',' ','*',' ',' ',' ',' ','*'},
                {'*',' ','*',' ',' ',' ','*',' ',' ','*'},
                {'*',' ','*','*','*',' ','*','*',' ','*'},
                {'*','*',' ',' ',' ',' ',' ',' ',' ','*'},
                {'*','*','*','*','*','*','*','*','*','*'}}; 
		printmenu();
		System.out.print("请输入您的选择：");
		Scanner sc = new Scanner(System.in);
		loop:while(true) {
			char action = sc.next().charAt(0);
			switch(action) {
			case 'm':case 'M':{
				printmenu();
				break;
			}
			case 'p':case 'P':{
				System.out.println("当前存储的迷宫为：");
				for(char[] i:mazearr) {
					for(char j:i)
						System.out.print(j);
					System.out.println();
				}
			}
			
			case 'x':case 'X':{
				break loop;
			}
			}
		System.out.print("请输入您的选择：");
		}
		sc.close();
	}
	public static void printmenu() {
		System.out.println(
				  "欢迎使用迷宫求解系统\n"
				+ "输入功能入口前的序号即可执行对应操作。\n"
				+ "m:显示此菜单。\n"
				+ "c:开始路径运算。\n"
				+ "p:显示当前迷宫状态。\n"
				+ "n:输入新的迷宫信息。\n"
				+ "i:导入迷宫存储文件。\n"
				+ "s:保存迷宫为本地文件。\n"
				+ "x:退出程序。\n"
				+ "默认迷宫数据已加载。");
	}
}
