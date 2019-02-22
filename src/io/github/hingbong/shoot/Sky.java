package io.github.hingbong.shoot;

import java.awt.image.BufferedImage;

public class Sky extends FlyObject {
  private static BufferedImage image;
  static {
    image = loadImage("res/background.png");
  }
  private int y1;// two pictures loop
  private int speed;
  static Sky sky = new Sky();

  private Sky() {
    super(0, 0, World.WIDTH, World.HEIGHT);
    y1 = -height;
    speed = 1;
  }

  @Override
  public void step() {
    y += speed;
    y1 += speed;
    if (y >= World.HEIGHT) {
      y = -height;
    }
    if (y1 >= World.HEIGHT) {
      y1 = -height;
    }
  }


  @Override
  public BufferedImage getImage() {
    return image;
  }

}
