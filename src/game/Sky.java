package game;

public class Sky {
	public int x;
	public int y;
	public int y1;// two pictures loop
	public int width;
	public int height;
	public int speed;

	public Sky() {
		super();
		this.width = 400;
		this.height = 700;
		this.x = 0;
		this.y = 0;
		this.y1 = -this.height;
		this.speed = 1;
	}

	public void show() {
		System.out.println("x:" + this.x);
		System.out.println("y:" + this.y);
		System.out.println("y1:" + this.y1);
		System.out.println("width:" + this.width);
		System.out.println("height:" + this.height);
		System.out.println("speed:" + this.speed);
	}

	public void step() {

	}
}
