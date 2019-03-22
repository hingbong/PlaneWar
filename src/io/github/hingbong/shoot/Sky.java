package io.github.hingbong.shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sky extends FlyObject {

  private static final BufferedImage image;
  private static final Sky sky = new Sky();

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

  static Sky getSky() {
    return sky;
  }

  void step() {
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
    x = 0;
    y = 0;
    width = World.WIDTH;
    height = World.HEIGHT;
    y1 = -height;
    speed = 1;
  }
}
