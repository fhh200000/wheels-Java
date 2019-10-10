package lang;


public class Sort {
/*
 * 不同排序算法的实现类。
 * 此处使用静态方法以完成调用的简化。
 * 通过传递不同的数组引用完成排序。
 * 数组的成员必须为“可比较”的(实现Java.lang.Comparable)。
 */
	public static void mergeSort(Object in) {
		mergeSort(in,false);//默认跟随compareTo的规则(降序？)
	}
	@SuppressWarnings("unchecked")
	public static void mergeSort(Object in,boolean reverse) {
		checkComparable(in);
		//将数组转化成可排序的数组
		Comparable<Object>[] data = (Comparable<Object>[])in;
		//定义一个临时数组，防止运算时频繁分配内存
		Comparable<Object>[] temp = new Comparable[data.length];
		//传入排序主方法
		mergeSort(data,0,data.length-1,temp);
		//如果为反序，那么将数组反向。
		if(reverse) {
			for(int i=0;i<data.length;i++)
				temp[i] = data[data.length-i-1];
			System.arraycopy(temp, 0, data, 0, temp.length);
		}
	}
	private static void mergeSort(Comparable<Object>[] data, int begin, int end, Comparable<Object>[] temp) {
		if(begin<end){
            int mid = (begin+end)/2;
            mergeSort(data,begin,mid,temp);//左边归并排序，使得左子序列有序
            mergeSort(data,mid+1,end,temp);//右边归并排序，使得右子序列有序
            merge(data,begin,mid,end,temp);//将两个有序子数组合并操作
        }
	}
	private static void merge(Comparable<Object>[] data,int begin,int mid,int end,Comparable<Object>[] temp){
        int i = begin;//左序列指针
        int j = mid+1;//右序列指针
        int t = 0;//临时数组指针
        while (i<=mid && j<=end){
            if(data[i].compareTo(data[j])>0){
                temp[t++] = data[i++];
            }else {
                temp[t++] = data[j++];
            }
        }
        while(i<=mid){//将左边剩余元素填充进temp中
            temp[t++] = data[i++];
        }
        while(j<=end){//将右序列剩余元素填充进temp中
            temp[t++] = data[j++];
        }
        //将temp中的元素全部拷贝到原数组中
        System.arraycopy(temp,0,data,begin,end-begin+1);
    }
	public static void checkComparable(Object in) {
		//如果不是数组，那么直接报错
		if(!(in instanceof Object[]))
			throw new UnsupportedOperationException("Not sorting an array:"+in.getClass());
		//如果数组的成员不可以被排序，那么依旧报错
		if(!((Object[])in instanceof Comparable[]))
			throw new UnsupportedOperationException("Sorting a not comparable array:"+in.getClass());
	}
}
