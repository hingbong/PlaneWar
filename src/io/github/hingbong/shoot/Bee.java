package io.github.hingbong.shoot;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Bee extends FlyObject {
  private static ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
  static {
    for (int i = 0; i < 5; i++) {
      images.add(loadImage("res/bee" + i + ".png"));
    }
  }
  private int xSpeed;
  private int ySpeed;
  private int rewardType;

  public Bee() {
    super(60, 50);
    xSpeed = 1;
    ySpeed = 2;
    rewardType = (int) (Math.random() * 2);
  }

  @Override
  public void step() {
    y += ySpeed;
    x += xSpeed;
    if (x <= 0 || x >= World.WIDTH - width + 1) {
      xSpeed = -xSpeed;
    }
  }

  int index = 1;

  @Override
  public BufferedImage getImage() {
    if (isLife()) {
      return images.get(0);
    } else if (isDead()) {
      if (index == images.size() - 1) {
        this.state = REMOVE;
      }
      return images.get(index++);
    } else {
      return null;
    }
  }
}
