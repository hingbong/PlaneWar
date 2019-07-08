package io.github.hingbong.shoot;

import java.awt.image.BufferedImage;

public abstract class AbstractEnemy extends BaseFlyObject {

  // image type array
  BufferedImage[] images;
  private int index = 0;

  AbstractEnemy(int width, int height) {
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

  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
