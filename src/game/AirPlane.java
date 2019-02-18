package game;

public class AirPlane extends FlyObject {
  int speed;

  public AirPlane() {
    super(49, 36);
    speed = 2;
  }

  @Override
  public String toString() {
    return "AirPlane [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", speed="
        + speed + "]";
  }


}
