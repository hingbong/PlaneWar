package io.github.hingbong.shoot;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class World extends JPanel implements MouseMotionListener {

  public static final int WIDTH = 480;
  public static final int HEIGHT = 852;
  // the unit in the world
  JFrame frame = new JFrame("Shoot Game");// initialize the window
  FlyObject[] enemies = new FlyObject[0];
  Bullet[] bullet = new Bullet[0];
  int enterIndex = 0;
  int shootIndex = 0;
  private int score = 0;

  public World() {
    this.addMouseMotionListener(this);
  }

  public static void main(String[] args) {
    World w = new World();// run start method in main method
    w.frame.add(w);// add world to window
    w.start();
    w.frame.setSize(WIDTH, HEIGHT);// set the window size
    w.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// set the (x)close function
    w.frame.setLocationRelativeTo(null);// window initialized location
    w.frame.setVisible(true);// display the window
  }

  // generate enemy
  public FlyObject newEnemy() {
    int number = (int) (Math.random() * 100);
    if (number < 20) {
      return new Bee();
    } else if (number < 55) {
      return new AirPlane();
    } else {
      return new BigPlane();
    }
  }

  public void enterAction() {
    enterIndex++;
    if (enterIndex % 40 == 0) {
      FlyObject enemy = newEnemy();
      enemies = Arrays.copyOf(enemies, enemies.length + 1);
      enemies[enemies.length - 1] = enemy;
    }
  }

  public void shootAction() {
    shootIndex++;
    if (shootIndex % 40 == 0) {
      Bullet[] bs = Hero.hero.shoot();
      bullet = Arrays.copyOf(bullet, bullet.length + bs.length);
      System.arraycopy(bs, 0, bullet, bullet.length - bs.length, bs.length);
    }
  }

  public void stepAction() {
    Sky.sky.step();
    for (FlyObject f : enemies) {
      f.step();
    }
    for (Bullet b : bullet) {
      b.step();
    }
  }

  @Override
  public void mouseDragged(MouseEvent e) {
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    Hero.hero.x = e.getX() - Hero.hero.width / 2;
    Hero.hero.y = e.getY() - Hero.hero.height / 2;
  }

  public void clearAction() {
    int index = 0;// record in window enemies count and their index
    FlyObject[] eList = new FlyObject[enemies.length];
    for (FlyObject e : enemies) {
      e.outOfBounds();
      if (!e.isRemove()) {
        eList[index] = e;
        index++;
      }
    }
    enemies = Arrays.copyOf(eList, index);
    index = 0;
    Bullet[] bList = new Bullet[bullet.length];
    for (Bullet b : bullet) {
      b.outOfBounds();
      if (!b.isRemove()) {
        bList[index] = b;
        index++;
      }
    }
    bullet = Arrays.copyOf(bList, index);

  }

  public void hitBulletAction() {
    clearAction();
    for (Bullet b : bullet) {
      for (FlyObject e : enemies) {
        if (e.hit(b) && e.isDead()) {
          if (e instanceof Bee) {
            switch (((Bee) e).getRewardType()) {
              case Bee.LIFE:
                Hero.hero.addLife();
                break;
              case Bee.DOUBLE_FIRE:
                Hero.hero.addFire();
                break;
            }
          } else {
            score += ((EnemyScore) e).getScore();
          }
          break;
        }
      }
    }
  }

  public void hitHeroAction() {
    clearAction();
    for (FlyObject e : enemies) {
      if (e.hit(Hero.hero) && e.index == 4) {
        Hero.hero.doubleFireClear();
        Hero.hero.minusLife();
        break;
      }
    }
  }

  public void start() {
    // set a timer
    Timer timer = new Timer();
    // set interval
    int interval = 10;// the bigger value, the slower speed
    TimerTask task = new TimerTask() {

      @Override
      public void run() {
        enterAction();
        shootAction();
        stepAction();
        clearAction();
        hitBulletAction();
        hitHeroAction();
        repaint();
      }
    };
    timer.schedule(task, 0, interval);
  }

  @Override
  public void paint(Graphics g) {
    Sky.sky.paintObject(g);
    for (FlyObject f : enemies) {
      f.paintObject(g);
    }
    for (Bullet b : bullet) {
      b.paintObject(g);
    }
    Hero.hero.paintObject(g);
    g.drawString("SCORE:" + score, 50, 30);
    g.drawString("LIFE:" + Hero.hero.getLife(), 50, 50);
  }

}
