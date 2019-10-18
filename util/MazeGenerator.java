package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import defs.Cell;

public class MazeGenerator {
	//迷宫生成器。
	//将用户输入的数据转换成地图。
	static Cell[][] mazearr = 
		{{new Cell(' '),new Cell('*'),new Cell(' '),new Cell(' '),new Cell(' '),new Cell('*')},
         {new Cell(' '),new Cell(' '),new Cell(' '),new Cell('*'),new Cell(' '),new Cell('*')},
         {new Cell('*'),new Cell(' '),new Cell('*'),new Cell(' '),new Cell(' '),new Cell('*')},
         {new Cell(' '),new Cell(' '),new Cell(' '),new Cell('*'),new Cell('*'),new Cell('*')},
         {new Cell(' '),new Cell('*'),new Cell('*'),new Cell(' '),new Cell(' '),new Cell(' ')},
         {new Cell(' '),new Cell(' '),new Cell(' '),new Cell(' '),new Cell('*'),new Cell('*')}};
	public static void main(String args[]) {
		System.out.println("请输入保存的文件名：");
		Scanner sc = new Scanner(System.in);
		String filename = sc.next();
		filename+=".maze";
		try {
			File outfile = new File(System.getProperty("java.class.path")+"/"+filename);
			if(!outfile.exists())
				outfile.createNewFile();
			FileOutputStream fos = new FileOutputStream(outfile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(mazearr);
			oos.flush();
			oos.close();
			System.out.println("Maze file "+outfile.getAbsolutePath()+" created.");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
