package game;

public class Sky extends FlyObject {
  public int y1;// two pictures loop
  public int speed;
  public static Sky sky = new Sky();

  private Sky() {
    super();
    width = 400;
    height = 700;
    x = 0;
    y = 0;
    y1 = -height;
    speed = 1;
  }


  @Override
  public String toString() {
    return "Sky [x=" + x + ", y=" + y + ", y1=" + y1 + ", width=" + width + ", height=" + height
        + ", speed=" + speed + "]";
  }

}
