package io.github.hingbong.shoot;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AirPlane extends FlyObject {
  private static ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
  static {
    for (int i = 0; i < 5; i++) {
      images.add(loadImage("res/airplane" + i + ".png"));
    }
  }
  private int speed;

  public AirPlane() {
    super(49, 36);
    speed = 2;
  }

  @Override
  public void step() {
    y += speed;
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
