package game;

public class AirPlane extends FlyObject {
  int speed;

  public AirPlane() {
    super();
    width = 49;
    height = 36;
    speed = 2;
    y = -height;
    x = (int) (Math.random() * (400 - height + 1));
  }

  @Override
  public String toString() {
    return "AirPlane [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", speed="
        + speed + "]";
  }


}
