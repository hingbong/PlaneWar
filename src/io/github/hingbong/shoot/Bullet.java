package io.github.hingbong.shoot;

import java.awt.image.BufferedImage;

public class Bullet extends FlyObject {
  private static BufferedImage image;
  static {
    image = loadImage("res/bullet.png");
  }
  private int speed;

  public Bullet() {
    super();
  }

  public Bullet(int x, int y) {
    super(x, y, 8, 14);
    speed = 2;
  }

  @Override
  public void step() {}

  @Override
  public BufferedImage getImage() {
    return image;
  }

}
