package apps;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import defs.TermX;
import lang.SortedSeqList;
import util.Polynomial;
public class PolynomialProvider {
	public static void main(String[] args) {
		Polynomial data=new Polynomial();
		printmenu();
		Scanner sc = new Scanner(System.in);
		char action;
		while(true) {
			System.out.println("请输入您的选择：");
			action = sc.next().charAt(0);
			switch(action) {
			case 'm':case 'M':{
				printmenu();
				break;
			}
			case 'n':case 'N':{
				System.out.println("请输入多项式字符串：");
				data = new Polynomial(sc.next());
				System.out.println("创建多项式"+data+"成功！");
				break;
			}
			case 'i':case 'I':{
				File filelist = new File(System.getProperty("java.class.path"));
				String[] currfiles = filelist.list();
				System.out.println("当前存储的文件有：");
				String[] split;
				//列出所有文件
				for(String i:currfiles) {
					split = i.split("\\.");
					if(split[split.length-1].equals("polynomial")) {
							System.out.print(i.substring(0,i.length()-11));
						System.out.println();
					}
				}
				System.out.println("请输入您希望读取的文件名：");
				String filename = sc.next();
				File outfile = new File(System.getProperty("java.class.path")+"/"+filename+".polynomial");
				while(!outfile.exists()) {
					System.out.println("输入的文件不存在！\n请输入您希望读取的文件名：");
					filename = sc.next();
					outfile = new File(System.getProperty("java.class.path")+"/"+filename+".polynomial");
				}
				try {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(outfile));
					@SuppressWarnings("unchecked")
					SortedSeqList<TermX> readObject = (SortedSeqList<TermX>)ois.readObject();
					data = new Polynomial(readObject);
					ois.close();
					System.out.println("读取成功！");
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				break;
			}
			case 's':case 'S':{
				//当前版本中，只允许将数据文件保存至程序运行目录。
				File filelist = new File(System.getProperty("java.class.path"));
				String[] currfiles = filelist.list();
				System.out.println("当前存储的文件有：");
				String[] split;
				//列出所有文件
				for(String i:currfiles) {
					split = i.split("\\.");
					if(split[split.length-1].equals("polynomial")) {
							System.out.print(i.substring(0,i.length()-11));
						System.out.println();
					}
				}
				System.out.println("请输入您希望保存的文件名：");
				String filename = sc.next();
				ObjectOutputStream oos;
				try {
					oos = new ObjectOutputStream(new FileOutputStream(System.getProperty("java.class.path")+"/"+filename+".polynomial"));
					oos.writeObject(data.getData().toSeqList());
					oos.flush();
					oos.close();
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
				System.out.println("保存成功！");
				break;
			}
			case 'a':case 'A':{
				System.out.println("请输入需要运算的多项式字符串：");
				Polynomial indata = new Polynomial(sc.next());
				data.add(indata);
				System.out.println("多项式运算成功！");
				System.out.println("结果为："+data);
				break;
			}
			case 'p':case 'P':{
				System.out.print("当前的多项式数据为：");
				String result = data.toString();
				if(result.length()>0)
					System.out.println(result);
				else
					System.out.println("(空)");
				break;
			}
			case 'x':case 'X':{
				sc.close();
				return;
			}
			default:{
				System.out.println("输入错误！");
				continue;
			}
			}
		}
	}
	static void printmenu() {
		System.out.println("欢迎使用多项式管理系统。\n"
						 + "输入功能入口前的序号即可执行对应操作。\n"
						 + "m:显示本菜单；\n"
						 + "n:按照aX^b的格式新建多项式;\n"
						 + "i:从文件中导入多项式数据；\n"
						 + "s:将多项式数据保存为文件；\n"
						 + "a:进行多项式的运算；\n"
						 + "p:输出当前多项式的数据；\n"
						 + "x:退出程序。");
	}
}
