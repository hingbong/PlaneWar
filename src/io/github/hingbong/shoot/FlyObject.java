package io.github.hingbong.shoot;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class FlyObject {
  public int x;
  public int y;
  public int width;
  public int height;

  public FlyObject() {}

  // for airplane,bigplane and bee
  public FlyObject(int width, int height) {
    super();
    this.width = width;
    this.height = height;
    y = -height;
    x = (int) (Math.random() * (400 - height + 1));
  }


  // for Hero , bullet , sky
  public FlyObject(int x, int y, int width, int height) {
    super();
    this.width = width;
    this.height = height;
    this.x = x;
    this.y = y;
  }

  public abstract void step();

  public static BufferedImage loadImage(String fileName) {// the method to load pictures
    BufferedImage img;
    try {
      img = ImageIO.read(FlyObject.class.getResource(fileName)); // load files
      return img;
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }
}
