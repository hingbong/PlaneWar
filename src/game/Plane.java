package game;

public abstract class Plane {
	public int x;
	public int y;
	public int width;
	public int height;
	public int speed;

	public Plane() {
		super();
		this.speed = 2;
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
