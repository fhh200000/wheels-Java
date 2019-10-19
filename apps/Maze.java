package apps;

import java.util.Scanner;

import defs.Cell;
import lang.LinkedStack;
import lang.Stack;

public class Maze {
	public static Stack<Cell> steps = new LinkedStack<Cell>();
	static Cell[][] mazearr;
	public static void main(String args[]) {
		int startx=-1,starty=-1,endx=-1,endy=-1;
		//创建迷宫数据。
		Cell.setWidth(6);
		mazearr = new Cell[][]
			   {{new Cell(' '),new Cell('*'),new Cell(' '),new Cell(' '),new Cell(' '),new Cell('*')},
                {new Cell(' '),new Cell(' '),new Cell(' '),new Cell('*'),new Cell(' '),new Cell('*')},
                {new Cell('*'),new Cell(' '),new Cell('*'),new Cell(' '),new Cell(' '),new Cell('*')},
                {new Cell(' '),new Cell(' '),new Cell(' '),new Cell('*'),new Cell('*'),new Cell('*')},
                {new Cell(' '),new Cell('*'),new Cell('*'),new Cell(' '),new Cell(' '),new Cell(' ')},
                {new Cell(' '),new Cell(' '),new Cell(' '),new Cell(' '),new Cell('*'),new Cell('*')}};
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
				//打印标题栏
				System.out.print(" ");
				for(int i=1;i<=mazearr[0].length;i++)
					System.out.print(" "+i);
				System.out.println();
				int currrow=65;
				for(Cell[] i:mazearr) {
					System.out.print((char)(currrow++)+" ");
					if(currrow>91)
						currrow = 97;
					for(Cell j:i)
						System.out.print(j+" ");
					System.out.println();
				}
				break;
			}	
			case 'x':case 'X':{
				break loop;
			}
			case 'f':case 'F':{
				System.out.println("请输入起点(格式例如A1):");
				String oper = sc.next();
				starty = (oper.charAt(0)-65);//从0开始
				startx = (oper.charAt(1)-49);//从0开始
				if(startx<0||starty<0||starty>mazearr.length-1||startx>mazearr[0].length-1) {
					System.out.println("输入无效！");
					break;
				}
				System.out.println("请输入终点(格式例如A1):");
				oper = sc.next();
				endy = (oper.charAt(0)-65);//从0开始
				endx = (oper.charAt(1)-49);//从0开始
				if(endx<0||endy<0||endy>mazearr.length-1||endx>mazearr[0].length-1) {
					System.out.println("输入无效！");
					break;
				}
				if(mazearr[starty][startx].getValue()=='*'||mazearr[endy][endx].getValue()=='*') {
					System.out.println("起点/终点无效：不能从墙开始！");
					break;
				}
				System.out.printf("起点/终点已被设置为%c%d/%c%d。%n",(char)(starty+65),startx+1,(char)(endy+65),endx+1);
				break;
			}
			case 'c':case 'C':{
				if(startx==-1||endy==-1) {
					System.out.println("尚未设置起点/终点，不能计算！");
					break;
				}
				//传入主方法。
				if(checkStep(mazearr[starty][startx],mazearr[endy][endx],0)) {
					System.out.println("求解成功！");
					while(steps.size()>0)
						steps.pop().setValue('+');
				}
				else
					System.out.println("求解失败！");
			}
			}
		System.out.print("请输入您的选择：");
		}
		sc.close();
	}
	public static boolean checkStep(Cell curr,Cell end,int offset) {
		boolean result=false;
		//如果到达了终点，那么返回true。
		if(curr==end)
			return true;
		//如果自己是墙，那么返回false。
		if(curr.getValue()=='*')
			return false;
		//如果自己不是墙，那么验证其他方向上的通路是否为墙。
		/*
		 * offset参数：
		 * 0000XXXX
		 * xxxxWSAD
		 * 为前一方块相对于这一方块的偏移。
		 */
		Cell prev = steps.peek();
		if(prev==null)
			prev = curr;
		steps.push(curr);
		if((offset&4)==0&&curr.getY()>0)
			result=checkStep(mazearr[curr.getY()-1][curr.getX()],end,8);
		if(!result&(offset&8)==0&&curr.getY()<mazearr.length-1)
			result=checkStep(mazearr[curr.getY()+1][curr.getX()],end,4);
		if(!result&(offset&1)==0&&curr.getX()>0)
			result=checkStep(mazearr[curr.getY()][curr.getX()-1],end,2);
		if(!result&(offset&2)==0&&curr.getX()<mazearr[0].length-1)
			result=checkStep(mazearr[curr.getY()][curr.getX()+1],end,1);
		if(!result)
			steps.pop();
		return result;
	}
	public static void printmenu() {
		System.out.println(
				  "欢迎使用迷宫求解系统\n"
				+ "输入功能入口前的序号即可执行对应操作。\n"
				+ "m:显示此菜单。\n"
				+ "f:标识起点和终点。\n"
				+ "c:开始路径运算。\n"
				+ "p:显示当前迷宫状态。\n"
				+ "i:导入迷宫存储文件。\n"
				+ "s:保存迷宫为本地文件。\n"
				+ "x:退出程序。\n"
				+ "默认迷宫数据已加载。");
	}
}
