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
	}

	public Sky(int x, int y, int y1, int width, int height, int speed) {
		super();
		this.x = x;
		this.y = y;
		this.y1 = y1;
		this.width = width;
		this.height = height;
		this.speed = speed;
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
