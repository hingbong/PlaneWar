package game;

public class BigPlane extends FlyObject {
  int speed;

  public BigPlane() {
    super(69, 99);
    speed = 2;
  }

  @Override
  public String toString() {
    return "BigPlane [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", speed="
        + speed + "]";
  }


}
