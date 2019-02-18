package game;

public class Bee extends FlyObject {
  public int xSpeed;
  public int ySpeed;
  public int rewardType;

  public Bee() {
    super(60, 50);
    xSpeed = 1;
    ySpeed = 2;
    rewardType = (int) (Math.random() * 2);
  }


  @Override
  public String toString() {
    return "Bee [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", xSpeed="
        + xSpeed + ", ySpeed=" + ySpeed + ", rewardType=" + rewardType + "]";
  }



}
