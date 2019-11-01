package apps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import defs.Cell;
import lang.LinkedStack;
import lang.Stack;

public class Maze {
	public static Stack<Cell> steps = new LinkedStack<Cell>();
	static Cell[][] mazearr;

	public static void main(String args[]) {
		int startx = -1, starty = -1, endx = -1, endy = -1;
		// 创建迷宫数据。
		Cell.setWidth(6);
		mazearr = new Cell[][] {
				{ new Cell(' '), new Cell('*'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('*') },
				{ new Cell(' '), new Cell(' '), new Cell(' '), new Cell('*'), new Cell(' '), new Cell('*') },
				{ new Cell('*'), new Cell(' '), new Cell('*'), new Cell(' '), new Cell(' '), new Cell('*') },
				{ new Cell(' '), new Cell(' '), new Cell(' '), new Cell('*'), new Cell('*'), new Cell('*') },
				{ new Cell(' '), new Cell('*'), new Cell('*'), new Cell(' '), new Cell(' '), new Cell(' ') },
				{ new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('*'), new Cell('*') } };
		printmenu();
		System.out.print("请输入您的选择：");
		Scanner sc = new Scanner(System.in);
		loop: while (true) {
			char action = sc.next().charAt(0);
			switch (action) {
			case 'm':
			case 'M': {
				printmenu();
				break;
			}
			case 'p':
			case 'P': {
				System.out.println("当前存储的迷宫为：");
				// 打印标题栏
				System.out.print(" ");
				for (int i = 1; i <= mazearr[0].length; i++)
					System.out.print(" " + i);
				System.out.println();
				int currrow = 65;
				for (Cell[] i : mazearr) {
					System.out.print((char) (currrow++) + " ");
					if (currrow > 91)
						currrow = 97;
					for (Cell j : i)
						System.out.print(j.getValue() + " ");
					System.out.println();
				}
				break;
			}
			case 'r':
			case 'R': {
				clear();
				break;
			}
			case 'x':
			case 'X': {
				break loop;
			}
			case 'f':
			case 'F': {
				System.out.println("请输入起点(格式例如A1):");
				String oper = sc.next();
				starty = (oper.charAt(0) - 65);// 从0开始
				startx = (oper.charAt(1) - 49);// 从0开始
				if (startx < 0 || starty < 0 || starty > mazearr.length - 1 || startx > mazearr[0].length - 1) {
					System.out.println("输入无效！");
					break;
				}
				System.out.println("请输入终点(格式例如A1):");
				oper = sc.next();
				endy = (oper.charAt(0) - 65);// 从0开始
				endx = (oper.charAt(1) - 49);// 从0开始
				if (endx < 0 || endy < 0 || endy > mazearr.length - 1 || endx > mazearr[0].length - 1) {
					System.out.println("输入无效！");
					break;
				}
				if (mazearr[starty][startx].getValue() == '*' || mazearr[endy][endx].getValue() == '*') {
					System.out.println("起点/终点无效：不能从墙开始！");
					break;
				}
				System.out.printf("起点/终点已被设置为%c%d/%c%d。%n", (char) (starty + 65), startx + 1, (char) (endy + 65),
						endx + 1);
				break;
			}
			case 'c':
			case 'C': {
				if (startx == -1 || endy == -1) {
					System.out.println("尚未设置起点/终点，不能计算！");
					break;
				}
				// 如果有上一次的计算结果，清空。
				clear();
				// 传入主方法。
				if (checkStep(mazearr[starty][startx], mazearr[endy][endx])) {
					System.out.print("求解成功！\n步骤为:");
					System.out.println(steps);
				} else
					System.out.println("求解失败！");
				break;
			}
			case 'I':
			case 'i': {
				// 当前版本中，只允许将数据文件保存至程序运行目录。
				File filelist = new File(System.getProperty("java.class.path"));
				String[] currfiles = filelist.list();
				System.out.println("当前存储的文件有：");
				String[] split;
				// 列出所有文件
				for (String i : currfiles) {
					split = i.split("\\.");
					if (split[split.length - 1].equals("maze")) {
						System.out.print(i.substring(0, i.length() - 5));
						System.out.println();
					}
				}
				System.out.println("请输入您希望读取的文件名：");
				String filename = sc.next();
				File outfile = new File(System.getProperty("java.class.path") + "/" + filename + ".maze");
				while (!outfile.exists()) {
					System.out.println("输入的文件不存在！\n请输入您希望读取的文件名：");
					filename = sc.next();
					outfile = new File(System.getProperty("java.class.path") + "/" + filename + ".maze");
				}

				try {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(outfile));
					mazearr = (Cell[][]) (ois.readObject());
					ois.close();
					System.out.println("读取成功！");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				break;
			}
			case 'S':
			case 's': {
				// 当前版本中，只允许将数据文件保存至程序运行目录。
				File filelist = new File(System.getProperty("java.class.path"));
				String[] currfiles = filelist.list();
				System.out.println("当前存储的文件有：");
				String[] split;
				// 列出所有文件
				for (String i : currfiles) {
					split = i.split("\\.");
					if (split[split.length - 1].equals("maze")) {
						System.out.print(i.substring(0, i.length() - 5));
						System.out.println();
					}
				}
				System.out.println("请输入您希望保存的文件名：");
				String filename = sc.next();
				ObjectOutputStream oos;
				try {
					oos = new ObjectOutputStream(
							new FileOutputStream(System.getProperty("java.class.path") + "/" + filename + ".maze"));
					oos.writeObject(mazearr);
					oos.flush();
					oos.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				System.out.println("保存成功！");
				break;
			}
			}
			System.out.print("请输入您的选择：");
		}
		sc.close();
	}

	/*
	 * 使用递归的实现版本。
	 * 
	 * 
	 * public static boolean checkStep(Cell curr,Cell end,int offset) { boolean
	 * result=false; //如果到达了终点，那么返回true。 if(curr==end) return true;
	 * //如果自己是墙，那么返回false。 if(curr.getValue()=='*') return false;
	 * //如果自己不是墙，那么验证其他方向上的通路是否为墙。 /* offset参数： 0000XXXX xxxxWSAD 为前一方块相对于这一方块的偏移。
	 *
	 * steps.push(curr); if((offset&4)==0&&curr.getY()>0)
	 * result=checkStep(mazearr[curr.getY()-1][curr.getX()],end,8);
	 * if(!result&(offset&8)==0&&curr.getY()<mazearr.length-1)
	 * result=checkStep(mazearr[curr.getY()+1][curr.getX()],end,4);
	 * if(!result&(offset&1)==0&&curr.getX()>0)
	 * result=checkStep(mazearr[curr.getY()][curr.getX()-1],end,2);
	 * if(!result&(offset&2)==0&&curr.getX()<mazearr[0].length-1)
	 * result=checkStep(mazearr[curr.getY()][curr.getX()+1],end,1); if(!result)
	 * steps.pop(); return result; }
	 */
	// 现在，我们将它改变为使用栈的版本。
	public static boolean checkStep(Cell start, Cell end) {
		// 将起始方块设为“已访问”。
		start.checkAvail();
		// 将首个元素入栈。
		steps.push(start);
		Cell peek;
		while (steps.peek() != end && steps.size() != 0) {
			peek = steps.peek();
			if (peek.getX() > 0 && mazearr[peek.getY()][peek.getX() - 1].checkAvail()) {
				steps.push(mazearr[peek.getY()][peek.getX() - 1]);
				continue;
			}
			if (peek.getX() < mazearr[0].length - 1 && mazearr[peek.getY()][peek.getX() + 1].checkAvail()) {
				steps.push(mazearr[peek.getY()][peek.getX() + 1]);
				continue;
			}
			if (peek.getY() > 0 && mazearr[peek.getY() - 1][peek.getX()].checkAvail()) {
				steps.push(mazearr[peek.getY() - 1][peek.getX()]);
				continue;
			}
			if (peek.getY() < mazearr.length - 1 && mazearr[peek.getY() + 1][peek.getX()].checkAvail()) {
				steps.push(mazearr[peek.getY() + 1][peek.getX()]);
				continue;
			}
			// 如果都不满足，那么弹出自身。
			steps.pop();
		}
		return steps.size() != 0;
	}

	public static void printmenu() {
		System.out.println("欢迎使用迷宫求解系统\n" + "输入功能入口前的序号即可执行对应操作。\n" + "m:显示此菜单。\n" + "f:标识起点和终点。\n" + "c:开始路径运算。\n"
				+ "r:重置运算结果。\n" + "p:显示当前迷宫状态。\n" + "i:导入迷宫存储文件。\n" + "s:保存迷宫为本地文件。\n" + "x:退出程序。\n" + "默认迷宫数据已加载。");
	}

	public static void clear() {
		if (steps.size() > 0)
			steps.clear();
		for (Cell[] i : mazearr)
			for (Cell j : i)
				j.reset();
	}
}
