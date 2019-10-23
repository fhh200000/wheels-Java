package defs;

import java.io.Serializable;

public class Student implements Comparable<Student>, Serializable {
	private String name,ID;
	private int score;
	//用于测试排序算法性能
	public static int compareCount;
	private static final long serialVersionUID = 1L;
	
	public Student(String ID,String name, int score) {
		super();
		this.ID = ID;
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public String getID() {
		return ID;
	}

	@Override
	public int compareTo(Student arg0) {
		//用于测试性能
		compareCount++;
		return this.score-arg0.score;
	}
	@Override
	public String toString() {
		return String.format("%s\t%s\t%d", ID,name,score);
	}
}
