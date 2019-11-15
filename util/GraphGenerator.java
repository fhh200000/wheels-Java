package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import lang.Graph;
import lang.MatrixGraph;

public class GraphGenerator {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char ch;
		System.out.println("请输入顶点个数：");
		int count=sc.nextInt();
		Graph<Character> graph = new MatrixGraph<Character>(count);
		//生成顶点标记。
		for(int i=0;i<count;i++) {
			graph.addVertex((char)(65+(i/26)*32+i%26));
		}
		printmenu();
		while(true) {
			ch = sc.next().charAt(0);
			switch(ch) {
			case 'x':case'X':{
				sc.close();
				return;
				}
			case 'm':case 'M':{
				printmenu();
				break;
			}
			case 'p':case 'P':{
				System.out.println("当前存储的图为：\n"+graph);
				break;
			}
			case 'a':case 'A':{
				System.out.println("请依次输入起始顶点，目标顶点，权重（从0开始）计算：");
				graph.insertEdge(sc.nextInt(),sc.nextInt(), sc.nextInt());
				System.out.println("添加成功！");
				break;
			}
			case 'e':case 'E':{
				System.out.println("请输入缩放的目标大小");
				int expandcount = sc.nextInt();
				for(int i=0;i<expandcount;i++) {
					graph.insertVertex((char)(graph.vertexCount()+i+65));
				}
				System.out.println("成功将图扩展为"+graph.vertexCount()+"个顶点。");
			}
			case 's':case 'S':{
				System.out.println("请输入保存的文件名：");
				String filename = sc.next();
				filename+=".graph";
				try {
					File outfile = new File(System.getProperty("java.class.path")+"/"+filename);
					if(!outfile.exists())
						outfile.createNewFile();
					FileOutputStream fos = new FileOutputStream(outfile);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(graph);
					oos.flush();
					oos.close();
					System.out.println("图"+outfile.getAbsolutePath()+"已保存。");
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				break;
			}
			}
		}
	}
	public static void printmenu() {
		System.out.println(
				  "欢迎使用图生成系统\n"
				+ "输入功能入口前的序号即可执行对应操作。\n"
				+ "m:显示此菜单。\n"
				+ "p:显示当前图中的数据。\n"
				+ "a:修改/添加图中的数据。\n"
				+ "e:扩展/收缩图的顶点数。\n"
				+ "s:保存图为本地文件。\n"
				+ "x:退出程序。");
	}
}
