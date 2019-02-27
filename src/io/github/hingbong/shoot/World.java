package io.github.hingbong.shoot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class World extends JPanel implements MouseMotionListener, MouseListener {

  static final int WIDTH = 480;
  static final int HEIGHT = 852;

  private static final int START = 0;
  private static final int RUNNING = 1;
  private static final int PAUSE = 2;
  private static final int GAME_OVER = 3;
  private static final BufferedImage start;
  private static final BufferedImage pause;
  private static final BufferedImage gameOver;

  static {
    start = FlyObject.loadImage("res/start.png");
    pause = FlyObject.loadImage("res/pause.png");
    gameOver = FlyObject.loadImage("res/gameover.png");
  }

  // the unit in the world
  private final JFrame frame = new JFrame("Shoot Game");// initialize the window
  private FlyObject[] enemies = new FlyObject[0];
  private Bullet[] bullet = new Bullet[0];
  private int enterIndex = 0;
  private int shootIndex = 0;
  private int score = 0;
  private int state = START;

  private World() {
    this.addMouseListener(this);
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
    w.frame.setBackground(Color.white);
  }

  private void checkRunningAction() {
    if (state == RUNNING) {
      if (Hero.hero.getLife() <= 0) {
        state = GAME_OVER;
      }
    }
  }

  // generate enemy
  private FlyObject newEnemy() {
    int number = (int) (Math.random() * 100);
    if (number < 20) {
      return new Bee();
    } else if (number < 55) {
      return new AirPlane();
    } else {
      return new BigPlane();
    }
  }

  private void enterAction() {
    enterIndex++;
    if (enterIndex % 40 == 0) {
      FlyObject enemy = newEnemy();
      enemies = Arrays.copyOf(enemies, enemies.length + 1);
      enemies[enemies.length - 1] = enemy;
    }
  }

  private void shootAction() {
    shootIndex++;
    if (shootIndex % 40 == 0) {
      Bullet[] bs = Hero.hero.shoot();
      bullet = Arrays.copyOf(bullet, bullet.length + bs.length);
      System.arraycopy(bs, 0, bullet, bullet.length - bs.length, bs.length);
    }
  }

  private void stepAction() {
    Sky.sky.step();
    for (FlyObject f : enemies) {
      f.step();
    }
    for (Bullet b : bullet) {
      b.step();
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseDragged(MouseEvent e) {
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    if (state == RUNNING) {
      Hero.hero.x = e.getX() - Hero.hero.width / 2;
      Hero.hero.y = e.getY() - Hero.hero.height / 2;
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    switch (state) {
      case START:
        state = RUNNING;
        break;
      case GAME_OVER:
        score = 0;
        Hero.hero.initHero();
        Hero.hero.doubleFireClear();
        Sky.sky.initSky();
        enemies = new FlyObject[0];
        bullet = new Bullet[0];
        state = START;
        break;
    }

  }

  @Override
  public void mouseExited(MouseEvent e) {
    if (state == RUNNING) {
      state = PAUSE;
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    if (state == PAUSE) {
      state = RUNNING;
    }
  }

  private void clearAction() {
    int index = 0;// record in window enemies count and their index
    FlyObject[] eList = new FlyObject[enemies.length];
    for (FlyObject e : enemies) {
      e.outOfBounds();
      if (e.isNotRemove()) {
        eList[index] = e;
        index++;
      }
    }
    enemies = Arrays.copyOf(eList, index);
    index = 0;
    Bullet[] bList = new Bullet[bullet.length];
    for (Bullet b : bullet) {
      b.outOfBounds();
      if (b.isNotRemove()) {
        bList[index] = b;
        index++;
      }
    }
    bullet = Arrays.copyOf(bList, index);

  }

  private void hitBulletAction() {
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

  private void hitHeroAction() {
    clearAction();
    for (FlyObject e : enemies) {
      if (e.hit(Hero.hero) && e.index == 4) {
        Hero.hero.doubleFireClear();
        Hero.hero.minusLife();
        break;
      }
    }
  }

  private void start() {
    // set a timer
    Timer timer = new Timer();
    // set interval
    int interval = 10;// the bigger value, the slower speed
    TimerTask task = new TimerTask() {

      @Override
      public void run() {
        if (state == RUNNING) {
          enterAction();
          shootAction();
          stepAction();
          clearAction();
          hitBulletAction();
          hitHeroAction();
          checkRunningAction();
        }
        repaint();
      }
    };
    timer.schedule(task, 0, interval);
  }

  @Override
  public void paint(Graphics g) {
    switch (state) {
      case START:
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(start, 40, 99, null);
        break;
      case RUNNING:
        g.clearRect(0, 0, WIDTH, HEIGHT);
        Sky.sky.paintObject(g);
        for (FlyObject f : enemies) {
          f.paintObject(g);
        }
        for (Bullet b : bullet) {
          b.paintObject(g);
        }
        Hero.hero.paintObject(g);
        g.setFont(new Font(null, Font.PLAIN, 15));
        g.drawString("SCORE:" + score, 50, 30);
        g.setFont(new Font(null, Font.PLAIN, 15));
        g.drawString("LIFE:" + Hero.hero.getLife(), 50, 50);
        break;
      case PAUSE:
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(pause, 40, 99, null);
        break;
      case GAME_OVER:
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setFont(new Font(null, Font.BOLD, 30));
        g.drawString("FINAL SCORE:" + score, 140, 300);
        g.drawImage(gameOver, 40, 99, null);
        break;
    }

  }
}
