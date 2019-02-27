package io.github.hingbong.shoot;

import java.awt.image.BufferedImage;

public class BigPlane extends Enemy implements EnemyScore {

  static {
    images = new BufferedImage[5];
    for (int i = 0; i < images.length; i++) {
      images[i] = loadImage("res/bigplane" + i + ".png");
    }
  }
  private int speed;

  BigPlane() {
    super(69, 99);
    speed = 2;
  }

  @Override
  public void step() {
    y += speed;
  }


  @Override
  public int getScore() {
    return 3;
  }

}
