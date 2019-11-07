package lang;

import defs.TriElement;

public class HuffmanTree {
	private String charset;
	private TriElement<Integer>[] huftree;
	@SuppressWarnings("unchecked")
	public HuffmanTree(int [] weights) {
		this.charset="";
		//与书上的写法不同，此处采用StringBuilder拼接字符串。
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<weights.length;i++)
			sb.append((char)('A'+i));
		charset = sb.toString();
		int n=weights.length;
		this.huftree = new TriElement[2*n-1];
		for(int i=0;i<n;i++)
			huftree[i] = new TriElement<Integer>(weights[i]);
		for(int i=n;i<2*n-1;i++) {
			int min1=Integer.MAX_VALUE,min2=min1;
			int x2=-1,x1=-1;
			for(int j=0;j<i;j++)
				if(huftree[j].getParent()==-1)
					if(huftree[j].getData()<min1) {
						min2=min1;
						x2=x1;
						min1=huftree[j].getData();
						x1 = j;
					}
					else if(huftree[j].getData()<min2) {
						min2 = huftree[j].getData();
						x2 = j;
					}
			huftree[x1].setParent(i);
			huftree[x2].setParent(i);
			huftree[i] = new TriElement<Integer>(min1+min2,-1,x1,x2);
		}
	}
	private String getCode(int i) {
		int n=8;
		char hufcode[] = new char[n];
		int child = i,parent =huftree[child].getParent();
		for(i=n-1;parent!=-1;i--) {
			hufcode[i] = (huftree[parent].getLeft()==child?'0':'1');
			child = parent;
			parent = huftree[child].getParent();
		}
		return new String(hufcode,i+1,n-1-i);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Huffman树的节点数组：");
		for(TriElement<Integer> i:huftree) {
			sb.append(i);
			sb.append(',');
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("\nHuffman编码：");
		for(int i=0;i<charset.length();i++) {
			sb.append(charset.charAt(i));
			sb.append(":");
			sb.append(getCode(i));
			sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	public String encode(String text) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<text.length();i++)
			sb.append(getCode(text.charAt(i)-'A'));
		return sb.toString();
	}
	public String decode(String compressed) {
		StringBuilder sb = new StringBuilder();
		int node=this.huftree.length-1;
		for(int i=0;i<compressed.length();i++) {
			if(compressed.charAt(i)=='0')
				node = huftree[node].getLeft();
			else
				node = huftree[node].getRight();
			if(huftree[node].isLeaf()) {
				sb.append(charset.charAt(node));
				node = huftree.length-1;
			}
		}
		return sb.toString();
	}
	public static int[] getKeywordFrequency(String in) {
		/*
		 * 对书本程序的改进。
		 * 通过扫描整个字符串来获取各字母的频率。
		 */
		int temp[] = new int[64];//127-65
		int maxlen=0;//最尾部的字符位置
		char source[] = in.toCharArray();
		for(char ch:source) {
			temp[ch-'A']++;
			maxlen=(maxlen>ch-'A')?maxlen:ch-'A';
		}
		int[] result=new int[maxlen+1];
		System.arraycopy(temp,0,result,0,maxlen+1);
		return result;
	}
}
