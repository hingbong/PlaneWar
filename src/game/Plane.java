package game;

public abstract class Plane {
  public int x;
  public int y;
  public int width;
  public int height;
  public int speed;

  public Plane() {
    super();
    this.speed = 2;
  }

  public void step() {

  }

  @Override
  public String toString() {
    return "Plane [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", speed="
        + speed + "]";
  }
}
