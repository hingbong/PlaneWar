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
    return images[index++ % images.length];
  }

  public Bullet[] shoot() {
    int x = 16;
    int y = 14;
    if (doubleFire == 0) {
      Bullet[] bs = new Bullet[1];
      bs[0] = new Bullet(this.x + x * 3 - 3, this.y - y);
      return bs;
    } else {
      Bullet[] bs = new Bullet[2];
      bs[0] = new Bullet(this.x + x - 3, this.y + y * 2);
      bs[1] = new Bullet(this.x + x * 5 - 3, this.y + y * 2);
      doubleFire--;
      return bs;
    }
  }

  public void addLife() {
    life++;
  }

  public void addFire() {
    doubleFire += 20;
  }

  public int getLife() {
    return life;
  }

}

