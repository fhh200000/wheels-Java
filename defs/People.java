package defs;

public class People {
	private int number;
	private boolean isDead;
	public People(int number) {
		this.number = number;
		isDead = false;
	}
	public void dead() {
		this.isDead = true;
	}
	public int getNumber() {
		return number;
	}
	public boolean isDead() {
		return isDead;
	}
	@Override
	public String toString() {
		return String.format("(Number:%d,Dead:%b)",this.number,this.isDead);
	}
}
