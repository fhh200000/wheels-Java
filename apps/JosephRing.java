package apps;
import lang.SingleLinkedList;
import lang.Node;

import java.util.Iterator;
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
		people = new SingleLinkedList<Integer>();
		if(sc.nextInt()!=1) 
			isRecycled = true;
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
			Node<Integer> curr = people.head.next;
			while(curr.next!=null)
				curr = curr.next;
			curr.next = people.head.next;
			/*
			 * 使用迭代器的重写版本。
			 * 使用迭代器可预防一些潜在的bug，
			 * 且代码更易于理解。
			 * 注释中的版本为不使用迭代器的普通版本。
			//将访问数据移动到开始。
			curr = people.head.next;
			int count=1;
			while(curr.next!=curr) {
				if(count != gap-1) {
                    count++;
                    curr = curr.next;
                }
				else {
					System.out.println("removing:"+curr.next.data);
					curr.next = curr.next.next;
					curr = curr.next;
					count=1;
				}
			}
			System.out.println(people.get(0));
			*/
			Iterator<Integer> iter = people.iterator();
			int remainingcount=peoplecount;
			int currpeople=1;
			while(remainingcount>1) { //迭代器的hasNext方法对于循环单链表无效
				for(int j=0;j<gap;j++) {
					currpeople=iter.next();
				}
				System.out.println("杀死了:"+currpeople);
				iter.remove();
				remainingcount--;
			}
			System.out.println(String.format("最终存活的人为%s号", people.get(0)));
		}
		else {
			//普通单链表需要判断是否越界。
			int j=0;
			while(remaining>1) {
				j=(j+gap-1)%remaining;
				System.out.println("杀死了"+people.remove(j)+"号");
				remaining--;
			}
			System.out.println(String.format("最终存活的人为%s号", people.get(0)));
		}	
		sc.close();
	}
	
}
