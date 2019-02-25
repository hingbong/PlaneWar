package io.github.hingbong.shoot;

import java.awt.Graphics;
import java.awt.Point;
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
  private Point mousePoint;
  Sky sky = Sky.sky;
  Hero hero = Hero.hero;
  FlyObject[] enemies = new FlyObject[0];
  Bullet[] bullet = new Bullet[0];

  public World() {
    this.addMouseMotionListener(this);
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

  int enterIndex = 0;

  public void enterAction() {
    enterIndex++;
    if (enterIndex % 40 == 0) {
      FlyObject enemy = newEnemy();
      enemies = Arrays.copyOf(enemies, enemies.length + 1);
      enemies[enemies.length - 1] = enemy;
    }
  }

  int shootIndex = 0;

  public void shootAction() {
    shootIndex++;
    if (shootIndex % 15 == 0) {
      Bullet[] bs = Hero.hero.shoot();
      bullet = Arrays.copyOf(bullet, bullet.length + bs.length);
      System.arraycopy(bs, 0, bullet, bullet.length - bs.length, bs.length);
    }
  }

  public void stepAction() {
    sky.step();
    for (FlyObject f : enemies) {
      f.step();
    }
    for (Bullet b : bullet) {
      b.step();
    }
  }

  @Override
  public void mouseDragged(MouseEvent e) {}

  @Override
  public void mouseMoved(MouseEvent e) {
    mousePoint = e.getPoint();
    hero.x = mousePoint.x - 48;
    hero.y = mousePoint.y - 62;
  }

  public void outOfBoundsAction() {
    int index = 0;// record in window enemies count and their index
    FlyObject[] eList = new FlyObject[enemies.length];
    for (FlyObject e : enemies) {
      if (!e.outOfBounds()) {
        eList[index] = e;
        index++;
      }
    }
    enemies = Arrays.copyOf(eList, index);
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
        outOfBoundsAction();
        repaint();
      }
    };
    timer.schedule(task, 0, interval);
  }

  @Override
  public void paint(Graphics g) {
    sky.paintObject(g);
    for (FlyObject f : enemies) {
      f.paintObject(g);
    }
    for (Bullet b : bullet) {
      b.paintObject(g);
    }
    hero.paintObject(g);
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

}
