package io.github.hingbong.shoot;

import java.awt.image.BufferedImage;

public class AirPlane extends Enemy implements EnemyScore {

  static {
    images = new BufferedImage[5];
    for (int i = 0; i < images.length; i++) {
      images[i] = loadImage("res/airplane" + i + ".png");
    }
  }

  private int speed;

  AirPlane() {
    super(49, 36);
    speed = 2;
  }

  @Override
  public void step() {
    y += speed;
  }


  @Override
  public int getScore() {
    return 1;
  }

}
