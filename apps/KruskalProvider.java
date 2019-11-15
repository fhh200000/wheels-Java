package apps;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

import defs.Triple;
import lang.Graph;
import util.MinSpanTree;

public class KruskalProvider {
	static Scanner sc = new Scanner(System.in);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		Graph<Character> graph=null;
		printmenu();
		System.out.print("请输入您的选择：");
		loop:while(true) {
			char action = sc.next().charAt(0);
			switch(action) {
			case 'm':case 'M':{
				printmenu();
				break;
			}
			case 'p':case 'P':{
				if(graph!=null) {
					System.out.println("当前加载的图为：");
					System.out.println(graph);
				}
				else
					System.out.println("尚未加载图！");
				break;
			}
			case 'o':case 'O':{
				File filelist = new File(System.getProperty("java.class.path"));
				String[] currfiles = filelist.list();
				System.out.println("当前存储的文件有：");
				String[] split;
				//列出所有文件
				for(String i:currfiles) {
					split = i.split("\\.");
					if(split[split.length-1].equals("graph")) {
							System.out.print(i.substring(0,i.length()-6));
						System.out.println();
					}
				}
				System.out.println("请输入您希望读取的文件名：");
				String filename = sc.next();
				File outfile = new File(System.getProperty("java.class.path")+"/"+filename+".graph");
				while(!outfile.exists()) {
					System.out.println("输入的文件不存在！\n请输入您希望读取的文件名：");
					filename = sc.next();
					outfile = new File(System.getProperty("java.class.path")+"/"+filename+".graph");
				}
				try {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(outfile));
					Graph<Character> readObject = (Graph<Character>)ois.readObject();
					graph = readObject;
					ois.close();
					//看起来顶点没有被保存。
					//在这里，我们重建顶点。
					//首先，我们获取顶点的最大值。
					int maxvertex=0;
					for(Triple<?> i:graph.getRawData()) {
						if(i.getRow()>maxvertex)
							maxvertex = i.getRow();
						if(i.getColumn()>maxvertex)
							maxvertex = i.getColumn();
					}
					for(int i=0;i<=maxvertex;i++) {
						graph.addVertex((char)(65+i));
					}
					System.out.println("读取成功！");
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				break;
			}
			case 's':case 'S':{
				System.out.println("开始求解最小生成树。");
				//将数据提取。
				Triple data[] = new Triple[graph.getRawData().size()];
				int curr=0;
				for(Triple i:graph.getRawData())
					data[curr++] = i;
				MinSpanTree mstree = new MinSpanTree(graph.vertexCount(),data,MinSpanTree.getIterator());
				System.out.println("Result:"+mstree);
				System.out.println("求解成功！");
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
				  "欢迎使用Kruskal最小生成树运算系统\n"
				+ "输入功能入口前的序号即可执行对应操作。\n"
				+ "m:显示此菜单。\n"
				+ "o:打开保存的图。\n"
				+ "s:求解最小生成树。\n"
				+ "p:显示当前加载的图。\n"
				+ "x:退出程序。");
	}
}