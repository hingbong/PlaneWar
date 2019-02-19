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


}
