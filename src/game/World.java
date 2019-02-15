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
    System.out.println(sky);
    System.out.println(plane);
    System.out.println(bigplane);
    System.out.println(bee);
    System.out.println(bullet);
    System.out.println(hero);

  }

  public static void main(String[] args) {
    World w = new World();// run start method in main method
    w.start();

  }

}
