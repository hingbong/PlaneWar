package io.github.hingbong.shoot;

import java.awt.image.BufferedImage;

public abstract class Enemy extends FlyObject {

  BufferedImage[] images;// image type array
  private int index = 0;

  Enemy(int width, int height) {
    super(width, height);
  }

  int getIndex() {
    return index;
  }

  abstract void step();

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
