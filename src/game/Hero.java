package game;

public class Hero {
	public int x;
	public int y;
	public int width;
	public int height;
	public int life;
	public int doubleFire;

	public void show() {
		System.out.println("x:" + this.x);
		System.out.println("y:" + this.y);
		System.out.println("width:" + this.width);
		System.out.println("height:" + this.height);
		System.out.println("life:" + this.life);
		System.out.println("Fire:" + this.doubleFire);
	}

	public void step() {

	}

}
