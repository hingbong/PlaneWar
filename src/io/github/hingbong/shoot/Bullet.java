package io.github.hingbong.shoot;

import java.awt.image.BufferedImage;

public class Bullet extends FlyObject {
  private static BufferedImage images;// image type array
  static {
    images = loadImage("res/bullet.png");
  }
  public int speed;

  public Bullet() {
    super();
  }

  public Bullet(int x, int y) {
    super(x, y, 8, 14);
    speed = 2;

  }


  @Override
  public String toString() {
    return "Bullet [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", speed="
        + speed + "]";
  }

  @Override
  public void step() {}

}
