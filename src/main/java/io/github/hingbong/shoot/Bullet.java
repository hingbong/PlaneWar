package io.github.hingbong.shoot;

import java.awt.image.BufferedImage;

public class Bullet extends BaseFlyObject {

  private static final BufferedImage image;

  static {
    image = loadImage("bullet.png");
  }

  private int speed;

  Bullet(int x, int y) {
    super(x, y, 8, 14);
    speed = 2;
  }

  void step() {
    y -= speed;
  }

  @Override
  public BufferedImage getImage() {
    if (isLife()) {
      return image;
    } else {
      state = getREMOVE();
      return null;
    }
  }

  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
