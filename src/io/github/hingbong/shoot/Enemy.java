package io.github.hingbong.shoot;

import java.awt.image.BufferedImage;

public class Enemy extends FlyObject {

  static BufferedImage[] images;// image type array

  Enemy(int width, int height) {
    super(width, height);
  }

  @Override
  public void step() {

  }

  @Override
  public BufferedImage getImage() {
    if (isLife()) {
      return images[0];
    } else if (isDead()) {
      if (index == images.length - 1) {
        this.state = getREMOVE();
      }
      return images[index++];
    } else {
      return null;
    }
  }
}
