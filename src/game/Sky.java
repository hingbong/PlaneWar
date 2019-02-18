package game;

public class Sky extends FlyObject {
  public int y1;// two pictures loop
  public int speed;
  public static Sky sky = new Sky();

  private Sky() {
    super();
    this.width = 400;
    this.height = 700;
    this.x = 0;
    this.y = 0;
    this.y1 = -this.height;
    this.speed = 1;
  }


  @Override
  public String toString() {
    return "Sky [x=" + x + ", y=" + y + ", y1=" + y1 + ", width=" + width + ", height=" + height
        + ", speed=" + speed + "]";
  }

}
