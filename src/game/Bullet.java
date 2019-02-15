package game;

public class Bullet {
  public int x;
  public int y;
  public int width;
  public int height;
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

  public void step() {

  }

  @Override
  public String toString() {
    return "Bullet [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", speed="
        + speed + "]";
  }
}
