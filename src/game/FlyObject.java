package game;

public abstract class FlyObject {
  public int x;
  public int y;
  public int width;
  public int height;
  public int speed;

  public FlyObject() {
    super();
    this.speed = 2;
  }

  public void step() {

  }

}
