package io.github.hingbong.shoot;

import io.github.hingbong.shoot.util.SimpleConcurrentHashSet;
import java.awt.image.BufferedImage;

public class Hero extends FlyObject {

  private static final Hero hero = new Hero();
  private static final BufferedImage[] images;// image type array

  static {
    images = new BufferedImage[2];
    for (int i = 0; i < images.length; i++) {
      images[i] = loadImage("res/hero" + i + ".png");
    }
  }

  private final SimpleConcurrentHashSet<Bullet> bs = new SimpleConcurrentHashSet<>(3);
  private int index;
  private int life;
  private int doubleFire;

  private Hero() {
    super(World.WIDTH / 2 - 97 / 2, World.HEIGHT - 200, 97, 124);
    life = 3;// at the beginning of the game,there are 3 lives
    doubleFire = 0;// at the beginning of the game,there is single bullet
  }

  static Hero getHero() {
    return hero;
  }

  @Override
  public BufferedImage getImage() {
    return images[index++ % images.length];
  }

  public SimpleConcurrentHashSet<Bullet> shoot() {
    bs.clear();
    int x = 16;
    int y = 14;
    if (doubleFire == 0) {
      bs.add(new Bullet(this.x + x * 3 - 3, this.y - y));
      return bs;
    } else {
      bs.add(new Bullet(this.x + x - 3, this.y + y * 2));
      bs.add(new Bullet(this.x + x * 5 - 3, this.y + y * 2));
      doubleFire--;
      return bs;
    }
  }

  void addFire() {
    doubleFire += 20;
  }

  int getLife() {
    return life;
  }

  void addLife() {
    this.life++;
  }

  void minusLife() {
    this.life--;
  }

  void doubleFireClear() {
    doubleFire = 0;
  }

  void initHero() {
    x = World.WIDTH / 2 - 97 / 2;
    y = World.HEIGHT - 200;
    width = 97;
    height = 124;
    life = 3;
    doubleFireClear();
  }
}

