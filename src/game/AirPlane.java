package game;

public class AirPlane extends FlyObject {
  int speed;

  public AirPlane() {
    super();
    this.width = 49;
    this.height = 36;
    this.speed = 2;
    this.y = -this.height;
    this.x = (int) (Math.random() * (400 - this.height + 1));
  }

  @Override
  public String toString() {
    return "AirPlane [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", speed="
        + speed + "]";
  }


}
