package io.github.hingbong.shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class FlyObject {
  // three state
  public static final int LIFE = 0;
  public static final int DEAD = 1;
  public static final int REMOVE = 2;

  // current object state
  protected int state = LIFE;
  protected int x;
  protected int y;
  protected int width;
  protected int height;

  public FlyObject() {}

  // for airplane,bigplane and bee
  public FlyObject(int width, int height) {
    super();
    this.width = width;
    this.height = height;
    y = -height;
    x = (int) (Math.random() * (World.WIDTH - width + 1));
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

  // get state
  public boolean isLife() {
    return state == LIFE;
  }

  public boolean isDead() {
    return state == DEAD;
  }

  public boolean isRemove() {
    return state == REMOVE;
  }

  // add object picture to panel
  public void paintObject(Graphics g) {
    g.drawImage(getImage(), x, y, null);
  }

  public void outOfBounds() {
    // if it's true ,that is out of the bounds
    if (y > World.HEIGHT || y < -height)
      state = REMOVE;
  }

  public abstract BufferedImage getImage();

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

  public void hit(FlyObject other) {
    int x1 = this.x - other.width;
    int x2 = this.x + this.width;
    int y1 = this.y - other.height;
    int y2 = this.y + this.height;
    if (other.x < x2 && other.x > x1 && other.y < y2 && other.y > y1) {
      if (other instanceof Bullet)
        other.state = REMOVE;
      this.state = DEAD;
    }
  }

}
