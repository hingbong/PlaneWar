package io.github.hingbong.shoot;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class World extends JPanel {
  public static final int WIDTH = 400;
  public static final int HEIGHT = 700;

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

  public void start() {
    // set a timer
    Timer timer = new Timer();
    // set interval
    int interval = 10;// the bigger value, the slower speed
    TimerTask task = new TimerTask() {

      @Override
      public void run() {

      }
    };
    timer.schedule(task, interval, interval);
  }

  @Override
  public void paint(Graphics g) {
    sky.paintObject(g);
    hero.paintObject(g);
    for (FlyObject f : enemies) {
      f.paintObject(g);
    }
    for (Bullet b : bullet) {
      b.paintObject(g);
    }
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
