package game;

public class World {
	Plane plane = new AirPlane();
	Plane bigplane = new BigPlane();
	Bee bee = new Bee();
	Bullet bullet = new Bullet(10, 20);
	Sky sky = new Sky();
	Hero hero = new Hero();

	// the unit in the world
	public void start() {
		// set attribution for unit,and output
		this.plane.show();
		this.bigplane.show();
		this.bee.show();
		this.sky.show();
		this.hero.show();
		this.bullet.show();

	}

	public static void main(String[] args) {
		World w = new World();// run start method in main method
		w.start();

	}

}
