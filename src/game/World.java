package game;

public class World {
  // the unit in the world
  Hero hero;
  Sky sky;
  FlyObject[] airPlanes = new AirPlane[30];
  FlyObject[] bigPlanes = new BigPlane[30];
  Bee[] bees = new Bee[30];
  Bullet[] bullets = new Bullet[30];

  public void start() {
    airPlanes[0] = new AirPlane();
    airPlanes[1] = new AirPlane();
    airPlanes[2] = new AirPlane();
    for (FlyObject air : airPlanes) {
      if (air == null) {
        break;
      }
      System.out.println(air);
      System.out.println("--------------------");
    }
    bigPlanes[0] = new BigPlane();
    bigPlanes[1] = new BigPlane();
    bigPlanes[2] = new BigPlane();
    for (FlyObject big : bigPlanes) {
      if (big == null) {
        break;
      }
      System.out.println(big);
      System.out.println("--------------------");
    }

    bullets[0] = new Bullet(30, 70);
    bullets[1] = new Bullet(130, 170);
    bullets[2] = new Bullet(230, 270);
    for (Bullet bullet : bullets) {
      if (bullet == null) {
        break;
      }
      System.out.println(bullet);
      System.out.println("--------------------");
    }

    bees[0] = new Bee();
    bees[1] = new Bee();
    bees[2] = new Bee();
    for (Bee bee : bees) {
      if (bee == null) {
        break;
      }
      System.out.println(bee);
      System.out.println("--------------------");
    }


  }

  public static void main(String[] args) {
    World w = new World();// run start method in main method
    w.start();

  }

}
