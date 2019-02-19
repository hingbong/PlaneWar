package io.github.hingbong.shoot;

public abstract class FlyObject {
  public int x;
  public int y;
  public int width;
  public int height;

  public FlyObject() {}

  // for airplane,bigplane and bee
  public FlyObject(int width, int height) {
    super();
    this.width = width;
    this.height = height;
    y = -height;
    x = (int) (Math.random() * (400 - height + 1));
  }


  // for Hero , bullet , sky
  public FlyObject(int x, int y, int width, int height) {
    super();
    this.width = width;
    this.height = height;
    this.x = x;
    this.y = y;
  }

  public abstract void step();

}
