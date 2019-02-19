package io.github.hingbong.shoot;

import java.awt.image.BufferedImage;

public class Bee extends FlyObject {
  private static BufferedImage[] images;// image type array
  static {
    images = new BufferedImage[5];
    for (int i = 0; i < images.length; i++) {
      images[i] = loadImage("res/bee" + i + ".png");
    }
  }
  public int xSpeed;
  public int ySpeed;
  public int rewardType;

  public Bee() {
    super(60, 50);
    xSpeed = 1;
    ySpeed = 2;
    rewardType = (int) (Math.random() * 2);
  }


  @Override
  public String toString() {
    return "Bee [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", xSpeed="
        + xSpeed + ", ySpeed=" + ySpeed + ", rewardType=" + rewardType + "]";
  }


  @Override
  public void step() {}



}
