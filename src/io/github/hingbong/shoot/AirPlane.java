package io.github.hingbong.shoot;

import java.awt.image.BufferedImage;

public class AirPlane extends FlyObject implements EnemyScore {

  private static BufferedImage[] images;// image type array

  static {
    images = new BufferedImage[5];
    for (int i = 0; i < images.length; i++) {
      images[i] = loadImage("res/airplane" + i + ".png");
    }
  }

  private int speed;

  AirPlane() {
    super(49, 36);
    speed = 2;
  }

  @Override
  public void step() {
    y += speed;
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

  @Override
  public int getScore() {
    return 1;
  }

}
