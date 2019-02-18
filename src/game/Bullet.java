package game;

public class Bullet extends FlyObject {
  public int speed;

  public Bullet() {
    super();
  }

  public Bullet(int x, int y) {
    super();
    this.width = 8;
    this.height = 14;
    this.speed = 2;
    this.x = x;
    this.y = y;

  }


  @Override
  public String toString() {
    return "Bullet [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", speed="
        + speed + "]";
  }

}
