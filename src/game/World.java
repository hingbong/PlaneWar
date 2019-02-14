package game;

public class World {
	Plane plane = new AirPlane(20,20,40,35,3);
	Plane bigplane = new BigPlane(60,70,4,60,70);

	// the unit in the world
	public void start() {
		// set attribution for unit,and output
		this.plane.show();
		this.bigplane.show();
	}

	public static void main(String[] args) {
		World w = new World();// run start method in main method
		w.start();

	}

}
