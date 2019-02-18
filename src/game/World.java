package game;

public class World { // the unit in the world
  Hero hero;
  Sky sky = Sky.sky;
  FlyObject[] flies = new FlyObject[30];

  public void start() {
    flies[0] = new AirPlane();
    flies[1] = new Bee();
    flies[2] = new BigPlane();
    flies[3] = new AirPlane();
    flies[4] = new AirPlane();
    flies[5] = new BigPlane();
    for (FlyObject f : flies) {
      if (f == null) {
        break;
      }
      System.out.println(f);
    }
  }

  public static void main(String[] args) {
    World w = new World();// run start method in main method
    w.start();
  }
}
