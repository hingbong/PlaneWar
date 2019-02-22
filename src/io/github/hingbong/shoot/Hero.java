package io.github.hingbong.shoot;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Hero extends FlyObject {
  private static ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
  static {
    for (int i = 0; i < 2; i++) {
      images.add(loadImage("res/hero" + i + ".png"));
    }
  }
  private int life;
  private int doubleFire;
  static Hero hero = new Hero();

  private Hero() {
    super(World.WIDTH / 2 - 97 / 2, World.HEIGHT - 200, 97, 124);
    life = 3;// at the beginning of the game,there are 3 lives
    doubleFire = 0;// at the beginning of the game,there is single bullet
  }

  @Override
  public void step() {}

  int index = 0;

  @Override
  public BufferedImage getImage() {
    return images.get(index++%2);
  }

  public ArrayList<Bullet> shoot() {
    int x = 16;
    int y = 14;
    if (doubleFire == 0) {
      ArrayList<Bullet> bs = new ArrayList<Bullet>();
      bs.add(new Bullet(this.x + x * 3 - 3, this.y - y));
      return bs;
    } else {
      ArrayList<Bullet> bs = new ArrayList<Bullet>();
      bs.add(new Bullet(this.x + x - 3, this.y + y * 2));
      bs.add(new Bullet(this.x + x * 5 - 3, this.y + y * 2));
      doubleFire--;
      return bs;
    }
  }
}
