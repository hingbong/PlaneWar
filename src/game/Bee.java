package game;

public class Bee {
	public int x;
	public int y;
	public int width;
	public int height;
	public int xSpeed;
	public int ySpeed;
	public int rewardType;

	public Bee() {
		super();
	}

	public Bee(int x, int y, int width, int height, int xSpeed, int ySpeed, int rewardType) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.rewardType = rewardType;
	}

	public void show() {
		System.out.println("x:" + this.x);
		System.out.println("y:" + this.y);
		System.out.println("width:" + this.width);
		System.out.println("height:" + this.height);
		System.out.println("xSpeed:" + this.xSpeed);
		System.out.println("ySpeed:" + this.ySpeed);
		System.out.println("RewardType:" + this.rewardType);
	}

	public void step() {

	}
}
