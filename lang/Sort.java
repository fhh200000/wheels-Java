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
	public static void quickSort(Object in) {
		quickSort(in,false);
	}
	@SuppressWarnings("unchecked")
	public static void quickSort(Object in,boolean reverse) {
		checkComparable(in);
		Comparable<Object>[] data = (Comparable<Object>[])in;
		//调用快速排序主方法
		quickSort(data,0,data.length-1);
		if(reverse) {
			Comparable<Object>[] temp = new Comparable[data.length];
			for(int i=0;i<data.length;i++)
				temp[i] = data[data.length-i-1];
			System.arraycopy(temp, 0, data, 0, temp.length);
		}
	}
	public static void quickSort(Comparable<Object>[] in,int left,int right) {
		//
		if(right<left)
			return;
		int i = left, j = right;
		//取最左的数据作为中值。
		Comparable<Object> tempdata = in[left];
		while(i<j) {
			while(i<j&&in[j].compareTo(tempdata)<0)
				j--;
			//将数据插入空缺（最左）。
			if(i < j) {
				in[i] = in[j];
				i++;
			}
			while(i<j&&in[i].compareTo(tempdata)>=0)
				i++;
			if(i < j) {
				in[j] = in[i];
				j--;
			}
		}
		//将tempdata填入中间。
		in[i] = tempdata;
		//对中值两边的数组分别排序。
		quickSort(in,left,i-1);
		quickSort(in,i+1,right);
	}
	public static void checkComparable(Object in) {
		//如果不是数组，那么直接报错
		if(!(in instanceof Object[]))
			throw new UnsupportedOperationException("Not sorting an array:"+in.getClass());
		//如果数组的成员不可以被排序，那么依旧报错
		if(!((Object[])in instanceof Comparable[]))
			throw new UnsupportedOperationException("Sorting a not comparable array:"+in.getClass());
	}
	public static void shellSort(Object in) {
		shellSort(in,false);
	}
	@SuppressWarnings("unchecked")
	public static void shellSort(Object in,boolean reverse) {
		checkComparable(in);
		Comparable<Object>[] data = (Comparable<Object>[])in;
		for(int range=data.length/2;range>0;range/=2) {
			for(int i=range;i<data.length;i++) {
				Comparable<Object> temp = data[i];
				int j;
				for(j=i-range;j>=0&&temp.compareTo(data[j])*(reverse?-1:1)>0;j-=range)
					data[j+range] = data[j];
				data[j+range] = temp;
			}
		}
	}
	public static void heapSort(Object in) {
		heapSort(in,false);
	}
	@SuppressWarnings("unchecked")
	public static void heapSort(Object in,boolean reverse) {
		checkComparable(in);
		Comparable<Object>[] data = (Comparable<Object>[])in;
		for(int i=data.length/2-1;i>=0;i--)
			sift(data,i,data.length-1,reverse);
		for(int i=data.length-1;i>0;i--) {
			Comparable<Object> temp = data[i];
			data[i] = data[0];
			data[0] = temp;
			sift(data,0,i-1,reverse);
		}
	}
	public static void sift(Comparable<Object>[] keys,int parent,int end,boolean reverse) {
		int child=2*parent+1;
		Comparable<Object> value=keys[parent];
		while(child<=end) {
			if(child<end&&keys[child].compareTo(keys[child+1])*(reverse?-1:1)>0)
				child++;
			if(value.compareTo(keys[child])*(reverse?-1:1)>0) {
				keys[parent] = keys[child];
				parent = child;
				child = 2*parent+1;
			}
			else
				break;
		}
		keys[parent] = value;
	}
}
