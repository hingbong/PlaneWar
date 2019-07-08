package io.github.hingbong.shoot;

import java.awt.image.BufferedImage;

class Bee extends AbstractEnemy {

  static final int LIFE = 0;
  static final int DOUBLE_FIRE = 1;
  private int xSpeed;
  private int ySpeed;
  private int rewardType;

  {
    images = new BufferedImage[5];
    for (int i = 0; i < images.length; i++) {
      images[i] = loadImage("bee" + i + ".png");
    }
  }

  Bee() {
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


  int getRewardType() {
    return rewardType;
  }
}
