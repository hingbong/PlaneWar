package io.github.hingbong.shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

abstract class FlyObject {

  // three state
  private static final int LIFE = 0;
  private static final int DEAD = 1;
  private static final int REMOVE = 2;

  // current object state
  int state = LIFE;
  int x;
  int y;
  int width;
  int height;

  // for airplane,bigplane and bee
  FlyObject(int width, int height) {
    super();
    this.width = width;
    this.height = height;
    y = -height;
    x = (int) (Math.random() * (World.WIDTH - width + 1));
  }


  // for Hero , bullet , sky
  FlyObject(int x, int y, int width, int height) {
    super();
    this.width = width;
    this.height = height;
    this.x = x;
    this.y = y;
  }

  static BufferedImage loadImage(String fileName) {// the method to load pictures
    BufferedImage img;
    try {
      img = ImageIO.read(FlyObject.class.getResource(fileName)); // load files
      return img;
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  static int getREMOVE() {
    return REMOVE;
  }

  // get state
  boolean isLife() {
    return state == LIFE;
  }

  boolean isDead() {
    return state == DEAD;
  }

  boolean isRemove() {
    return state == REMOVE;
  }

  void setRemove() {
    state = REMOVE;
  }

  // add object picture to panel
  public void paintObject(Graphics g) {
    g.drawImage(getImage(), x, y, null);
  }

  boolean outOfBounds() {
    return y > World.HEIGHT || y < -height;
  }

  protected abstract BufferedImage getImage();

  boolean hit(FlyObject other) {
    int x1 = this.x - other.width;
    int x2 = this.x + this.width;
    int y1 = this.y - other.height;
    int y2 = this.y + this.height;
    if (other.x < x2 && other.x > x1 && other.y < y2 && other.y > y1) {
      this.state = other.state = DEAD;
      return true;
    } else {
      return false;
    }
  }

}
