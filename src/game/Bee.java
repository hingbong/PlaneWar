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
		this.width = 60;
		this.height = 50;
		this.y = -this.height;
		this.x = (int) (Math.random() * (400 - this.height + 1));
		this.xSpeed = 1;
		this.ySpeed = 2;
		this.rewardType = (int) (Math.random() * 2);
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
