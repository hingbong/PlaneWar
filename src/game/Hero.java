package game;

public class Hero {
	public int x;
	public int y;
	public int width;
	public int height;
	public int life;
	public int doubleFire;

	public Hero() {
		super();
		this.width = 97;
		this.height = 124;
		this.x = 200 - this.width / 2;
		this.y = 500;
		this.life = 3;// at the beginning of the game,there is 3 lives
		this.doubleFire = 0;// at the beginning of the game,there is single bullet
	}

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
