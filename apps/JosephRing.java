package apps;
import lang.SingleLinkedList;
import lang.RecycledSingleLinkedList;
import lang.Node;
import java.util.Scanner;
public class JosephRing {
/*
 *约瑟夫环问题。
 *使用普通链表和循环链表。
 */
	public static void main(String[] args) {
		SingleLinkedList<Integer> people;
		Scanner sc = new Scanner(System.in);
		boolean isRecycled = false;
		System.out.println("请输入后端格式：\n1.普通链表\n2.循环链表");
		if(sc.nextInt()==1) 
			people = new SingleLinkedList<Integer>();
		else {
			people = new RecycledSingleLinkedList<Integer>();
			isRecycled = true;
		}
		System.out.println("请输入人数：");
		int peoplecount = sc.nextInt();
		int i=0;
		people = new SingleLinkedList<Integer>();
		while(i<peoplecount)
			people.append(++i);
		//开始处理
		System.out.println("请输入间隔：");
		int gap=sc.nextInt(),remaining = peoplecount;
		if(isRecycled){
			//循环单链表需要在此闭环。
			((RecycledSingleLinkedList<Integer>) people).closeCircle();
			int j=0;
			while(remaining>1) {
				j+=gap-1;
				System.out.println("removing:"+people.remove(people.get(remaining)));
				remaining--;
			}
			System.out.println(people.get(0));
		}
		else {
			//普通单链表需要判断是否越界。
			int j=0;
			while(remaining>1) {
				j=(j+gap-1)%remaining;
				System.out.println("removing:"+people.remove(j));
				remaining--;
			}
			System.out.println(people.get(0));
		}	
		sc.close();
	}
	
}
