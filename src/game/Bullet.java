package game;

public class Bullet extends FlyObject {
  public int speed;

  public Bullet() {
    super();
  }

  public Bullet(int x, int y) {
    super(x, y, 8, 14);
    speed = 2;

  }


  @Override
  public String toString() {
    return "Bullet [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", speed="
        + speed + "]";
  }

  @Override
  public void step() {}

}
