package game;

public class BigPlane extends FlyObject {
  int speed;

  public BigPlane() {
    super();
    this.width = 69;
    this.height = 99;
    this.speed = 2;
    this.y = -this.height;
    this.x = (int) (Math.random() * (400 - this.height + 1));
  }

  @Override
  public String toString() {
    return "BigPlane [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", speed="
        + speed + "]";
  }


}
