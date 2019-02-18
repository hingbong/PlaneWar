package game;

public class Bullet extends FlyObject {
  public int speed;

  public Bullet() {
    super();
  }

  public Bullet(int x, int y) {
    super();
    width = 8;
    height = 14;
    speed = 2;
    this.x = x;
    this.y = y;

  }


  @Override
  public String toString() {
    return "Bullet [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", speed="
        + speed + "]";
  }

}
