package io.github.hingbong.shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sky extends FlyObject {

  static Sky sky = new Sky();
  private static final BufferedImage image;

  static {
    image = loadImage("res/background.png");
  }

  private int y1;// two pictures loop
  private int speed;

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
  public void paintObject(Graphics g) {
    super.paintObject(g);
    g.drawImage(getImage(), x, y1, null);
  }

  @Override
  protected BufferedImage getImage() {
    return image;
  }

  void initSky() {
    sky = new Sky();
  }
}
