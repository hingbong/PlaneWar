package io.github.hingbong.shoot;

import java.awt.image.BufferedImage;

public class Hero extends FlyObject {
  private static BufferedImage[] images;// image type array
  static {
    images = new BufferedImage[2];
    for (int i = 0; i < images.length; i++) {
      images[i] = loadImage("res/hero" + i + ".png");
    }
  }
  public int life;
  public int doubleFire;

  public Hero() {
    super(200 - 97 / 2, 500, 97, 124);
    life = 3;// at the beginning of the game,there are 3 lives
    doubleFire = 0;// at the beginning of the game,there is single bullet
  }

  @Override
  public String toString() {
    return "Hero [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", life="
        + life + ", doubleFire=" + doubleFire + "]";
  }

  @Override
  public void step() {}

  public boolean isDoubleFire() {
    return doubleFire == 1;
  }

  int index = 0;

  @Override
  public BufferedImage getImage() {
    if (index == 0) {
      return images[index++];
    } else {
      return images[index--];
    }
  }
}
