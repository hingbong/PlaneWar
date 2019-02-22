package io.github.hingbong.shoot;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class World extends JPanel {
  public static final int WIDTH = 480;
  public static final int HEIGHT = 852;

  // the unit in the world
  Hero hero = Hero.hero;
  Sky sky = Sky.sky;
  FlyObject[] enemies = new FlyObject[0];
  Bullet[] bullet = new Bullet[0];

  // generate enemy
  public FlyObject newEnemy() {
    int number = (int) (Math.random() * 100);
    if (number < 20) {
      return new Bee();
    } else if (number < 55) {
      return new AirPlane();
    } else
      return new BigPlane();
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
    if (shootIndex % 20 == 0) {
      Bullet[] bs = hero.shoot();
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
    JFrame frame = new JFrame("Shoot Game");// initialize the window
    World w = new World();// run start method in main method
    frame.add(w);// add world to window
    w.start();
    frame.setSize(WIDTH, HEIGHT);// set the window size
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// set the (x)close function
    frame.setLocationRelativeTo(null);// window initialized location
    frame.setVisible(true);// display the window

  }
}
