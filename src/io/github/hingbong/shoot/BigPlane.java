package io.github.hingbong.shoot;

import java.awt.image.BufferedImage;

public class BigPlane extends FlyObject {
  private static BufferedImage[] images;// image type array
  static {
    images = new BufferedImage[5];
    for (int i = 0; i < images.length; i++) {
      images[i] = loadImage("res/bigplane" + i + ".png");
    }
  }
  int speed;

  public BigPlane() {
    super(69, 99);
    speed = 2;
  }

  @Override
  public String toString() {
    return "BigPlane [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", speed="
        + speed + "]";
  }

  @Override
  public void step() {}


}
