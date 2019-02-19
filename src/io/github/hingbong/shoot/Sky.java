package io.github.hingbong.shoot;

public class Sky extends FlyObject {
  public int y1;// two pictures loop
  public int speed;
  public static Sky sky = new Sky();

  private Sky() {
    super(0, 0, 400, 700);
    y1 = -height;
    speed = 1;
  }


  @Override
  public String toString() {
    return "Sky [x=" + x + ", y=" + y + ", y1=" + y1 + ", width=" + width + ", height=" + height
        + ", speed=" + speed + "]";
  }


  @Override
  public void step() {}

}
