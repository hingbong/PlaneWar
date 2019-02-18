package game;

public class BigPlane extends FlyObject {
  int speed;

  public BigPlane() {
    super();
    width = 69;
    height = 99;
    speed = 2;
    y = -height;
    x = (int) (Math.random() * (400 - height + 1));
  }

  @Override
  public String toString() {
    return "BigPlane [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", speed="
        + speed + "]";
  }


}
