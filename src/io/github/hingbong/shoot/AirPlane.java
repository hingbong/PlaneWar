package io.github.hingbong.shoot;

import java.awt.image.BufferedImage;

public class AirPlane extends FlyObject {
  private static BufferedImage[] images;// image type array
  static {
    images = new BufferedImage[5];
    for (int i = 0; i < images.length; i++) {
      images[i] = loadImage("res/airplane" + i + ".png");
    }
  }
  int speed;

  public AirPlane() {
    super(49, 36);
    speed = 2;
  }

  @Override
  public String toString() {
    return "AirPlane [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", speed="
        + speed + "]";
  }

  @Override
  public void step() {}

  int index = 1;

  @Override
  public BufferedImage getImage() {
    if (isLife()) {
      return images[0];
    } else if (isDead()) {
      if (index == images.length - 1) {
        this.state = REMOVE;
      }
      return images[index++];
    } else {
      return null;
    }
  }

}
