package defs;

import lang.Addible;

public class TermX implements Comparable<TermX>, Addible<TermX> {
	public static final String expdigits="⁰¹²³⁴⁵⁶⁷⁸⁹";
	public static final char expnegative='⁻';
	protected int coef,xexp;
	public TermX(int coef,int xexp) {
		this.coef = coef;
		this.xexp = xexp;
	}
	public TermX(TermX in) {
		this.coef = in.coef;
		this.xexp = in.xexp;
	}
	public TermX(String termstr) {
		//形式诸如ax^b,aX^b
		//若b=0，则直接对常数项赋值。
		if(termstr.indexOf('x')==-1&&termstr.indexOf('X')==-1) {
			coef = Integer.valueOf(termstr);
			xexp = 0;
			return;
		}
		//若用户未输入次数,则不读取次数
		if(!Character.isDigit(termstr.charAt(termstr.length()-1))) {
			coef = 1;
			xexp = 1;
			return;
		}
		//若b不等于0且b不等于1,则按照表达式"x^"/"X^"切分。
		String[] result;
		if(termstr.indexOf('x')==-1) //大写X
			result = termstr.split("X\\^");
		else //小写X
			result = termstr.split("x\\^");
		//如果未输入常数，则默认为1.
		if(!result[0].equals(""))
			coef = Integer.valueOf(result[0]);
		else
			coef = 1;
		xexp = Integer.valueOf(result[1]);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(coef>0)
			sb.append('+');
		//如果系数为1次，那么不显示系数。
		if(Math.abs(coef)!=1)
			sb.append(coef);
		else 
			if(coef<0)
				sb.append('-');
		//如果次数为0次，那么不显示未知数x。
		if(xexp!=0) {
			sb.append('x');
		}
		else {
			return sb.toString();
		}
		//将次数转化成上标。
		//如果次数为1,那么无需显示上标。
		if(xexp==1)
			return sb.toString();
		//如果次数为负，那么加入上标-。
		if(xexp<0)
			sb.append(expnegative);
		int absxexp = Math.abs(xexp);
		//如果数字小于10,那么直接输出。
		if(absxexp/10==0) {
			sb.append(expdigits.charAt(absxexp));
		}
		else {
			//方式：通过字符数组变换。O(n)
			char[] bits = Integer.toString(absxexp).toCharArray();
			for(char i:bits) {
				sb.append(expdigits.charAt(i-'0'));
			}
		}
		return sb.toString();
	}
	@Override
	public int compareTo(TermX term) {
		return term.xexp - this.xexp;
	}
	@Override
	public boolean add(TermX x) {
		/*
		 * 对教材示例代码的微小改动。
		 * 在添加成功后返回一个boolean类型的变量以通知“添加成功”。
		 * 如果次数不一致，则返回“添加失败”。
		 */
		boolean result=false;
		if(x.xexp==this.xexp) {
			this.coef+=x.coef;
			result = true;
		}
		return result;
	}
	@Override
	public boolean removable() {
		return this.coef==0;
	}
}
