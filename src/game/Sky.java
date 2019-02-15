package game;

public class Sky {
  public int x;
  public int y;
  public int y1;// two pictures loop
  public int width;
  public int height;
  public int speed;

  public Sky() {
    super();
    this.width = 400;
    this.height = 700;
    this.x = 0;
    this.y = 0;
    this.y1 = -this.height;
    this.speed = 1;
  }

  public void step() {

  }

  @Override
  public String toString() {
    return "Sky [x=" + x + ", y=" + y + ", y1=" + y1 + ", width=" + width + ", height=" + height
        + ", speed=" + speed + "]";
  }
}
