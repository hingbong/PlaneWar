package game;

public class Bee extends FlyObject {
  public int xSpeed;
  public int ySpeed;
  public int rewardType;

  public Bee() {
    super();
    this.width = 60;
    this.height = 50;
    this.y = -this.height;
    this.x = (int) (Math.random() * (400 - this.height + 1));
    this.xSpeed = 1;
    this.ySpeed = 2;
    this.rewardType = (int) (Math.random() * 2);
  }


  @Override
  public String toString() {
    return "Bee [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", xSpeed="
        + xSpeed + ", ySpeed=" + ySpeed + ", rewardType=" + rewardType + "]";
  }



}
