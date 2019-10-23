package apps;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

import defs.Student;
import lang.Sort;

public class StudentSorter {
	Scanner sc;
	public static void main(String[] args) {
		Student[] students=null,origstudents;	
		Scanner sc = new Scanner(System.in);
		//当前版本中，只允许将数据文件保存至程序运行目录。
		File filelist = new File(System.getProperty("java.class.path"));
		String[] currfiles = filelist.list();
		System.out.println("当前存储的文件有：");
		String[] split;
		//列出所有文件
		for(String i:currfiles) {
			split = i.split("\\.");
			if(split[split.length-1].equals("students")) {
					System.out.print(i.substring(0,i.length()-9));
				System.out.println();
			}
		}
		System.out.println("请输入您希望读取的文件名：");
		String filename = sc.next();
		File outfile = new File(System.getProperty("java.class.path")+"/"+filename+".students");
		while(!outfile.exists()) {
			System.out.println("输入的文件不存在！\n请输入您希望读取的文件名：");
			filename = sc.next();
			outfile = new File(System.getProperty("java.class.path")+"/"+filename+".students");
		}
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(outfile));
			students = (Student[])(ois.readObject());
			ois.close();
			System.out.println("读取成功！");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		origstudents = new Student[students.length];
		System.arraycopy(students,0, origstudents, 0, students.length);
		printmenu();
		System.out.print("请输入您的选择：");
		loop:while(true) {
			char action = sc.next().charAt(0);
			switch(action) {
			case 'm':case 'M':{
				printmenu();
				break;
			}
			case 'x':case 'X':{
				break loop;
			}
			case 'r':case 'R':{
				System.arraycopy(origstudents,0, students, 0, students.length);
				System.out.println("恢复完成！");
				break;
			}
			case 's':case 'S':{
				Student.compareCount=0;
				long time = System.currentTimeMillis();
				Sort.shellSort(students);
				time = System.currentTimeMillis() - time;
				System.out.println("希尔排序完成，花费时间"+time+"毫秒，比较"+Student.compareCount+"次。");
				break;
			}
			case 'g':case 'G':{
				Student.compareCount=0;
				long time = System.currentTimeMillis();
				Sort.mergeSort(students);
				time = System.currentTimeMillis() - time;
				System.out.println("归并排序完成，花费时间"+time+"毫秒，比较"+Student.compareCount+"次。");
				break;
			}
			case 'f':case 'F':{
				Student.compareCount=0;
				long time = System.currentTimeMillis();
				Sort.quickSort(students);
				time = System.currentTimeMillis() - time;
				System.out.println("快速排序完成，花费时间"+time+"毫秒，比较"+Student.compareCount+"次。");
				break;
			}
			case 'h':case 'H':{
				Student.compareCount=0;
				long time = System.currentTimeMillis();
				Sort.heapSort(students);
				time = System.currentTimeMillis() - time;
				System.out.println("堆排序完成，花费时间"+time+"毫秒，比较"+Student.compareCount+"次。");
				break;
			}
			case 'p':case 'P':{
				for(int i=0;i<students.length;i++) {
					System.out.println(students[i]);
					if(i%30==0&&i!=0) {
						System.out.printf("正在显示%d～%d条数据，任意键继续，C键退出。\n",i-29,i);
						char ch = sc.next().charAt(0);
						if(ch=='C'||ch=='c')
							break;
					}
				}
				break;
			}
			}
		System.out.println("请输入您的选择：");
		}
		sc.close();
	}
	public static void printmenu() {
		System.out.println(
				  "欢迎使用学生成绩排序系统\n"
				+ "输入功能入口前的序号即可执行对应操作。\n"
				+ "m:显示此菜单。\n"
				+ "s:使用希尔排序统计学生成绩。\n"
				+ "g:使用归并排序统计学生成绩。\n"
				+ "f:使用快速排序统计学生成绩。\n"
				+ "h:使用堆排序统计学生成绩。\n"
				+ "r:恢复未排序的学生成绩。\n"
				+ "p:显示当前的学生成绩。\n"
				+ "x:退出程序。");
	}
}
