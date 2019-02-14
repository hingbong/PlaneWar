package game;

public class Bullet {
	public int x;
	public int y;
	public int width;
	public int height;
	public int speed;

	public Bullet() {
		super();
	}

	public Bullet(int x, int y) {
		super();
		this.width = 8;
		this.height = 14;
		this.speed = 2;
		this.x = x;
		this.y = y;

	}

	public void show() {
		System.out.println("x:" + this.x);
		System.out.println("y:" + this.y);
		System.out.println("width:" + this.width);
		System.out.println("height:" + this.height);
		System.out.println("speed:" + this.speed);
	}

	public void step() {

	}
}
