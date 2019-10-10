package test;
import defs.TermX;
import util.Polynomial;
public class PolynomialTester {
	public static void main(String[] args) {
		Polynomial a = new Polynomial(
								new TermX[] {
										new TermX("-99999X^8"),
										new TermX("-2X^54"),
										new TermX("3"),
										});
		System.out.println("output test:"+a);
		Polynomial b = new Polynomial(
				new TermX[] {
						new TermX("-2X^20"),
						new TermX("-2X^54"),
						new TermX("2X^33"),
						new TermX("2X^8"),
						new TermX("2x"),
						});
		a.add(b);
		System.out.println("add test:"+a);
		Polynomial c = new Polynomial(
				new TermX[] {
						new TermX("X^20"),
						new TermX("4X^54"),
						new TermX("-2X^33"),
						new TermX("-3"),
						});
		a.add(c);
		System.out.println("add negative(minus) test:"+a);
		Polynomial d = new Polynomial("X^20+4X^54-2X^33-3");
		System.out.println("String constructor(X^20+4X^54-2X^33-3) test:"+d);
		//完成测试：
		System.out.println("Finished test.");
	}
}
