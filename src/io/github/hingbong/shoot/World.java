package io.github.hingbong.shoot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedDeque;
import javax.swing.JFrame;
import javax.swing.JPanel;

class World extends JPanel {

  static final int WIDTH = 480;
  static final int HEIGHT = 852;

  private static final int START = 0;
  private static final int RUNNING = 1;
  private static final int PAUSE = 2;
  private static final int GAME_OVER = 3;
  private static final BufferedImage START_IMAGE;
  private static final BufferedImage PAUSE_IMAGE;
  private static final BufferedImage GAMEOVER_IMAGE;
  // the unit in the WORLD
  private final static World WORLD = new World();
  private static int state = START;

  static {
    START_IMAGE = FlyObject.loadImage("res/start.png");
    PAUSE_IMAGE = FlyObject.loadImage("res/pause.png");
    GAMEOVER_IMAGE = FlyObject.loadImage("res/gameover.png");
  }

  private final JFrame frame = new JFrame("Shoot Game");// initialize the window
  private final ConcurrentLinkedDeque<Enemy> enemies = new ConcurrentLinkedDeque<>();
  private final ConcurrentLinkedDeque<Bullet> bullet = new ConcurrentLinkedDeque<>();
  private int enterIndex = 0;
  private int shootIndex = 0;
  private int score = 0;

  private World() {
    MouseListener mouseListener = new MouseListener() {
      @Override
      public void mousePressed(MouseEvent e) {

      }

      @Override
      public void mouseReleased(MouseEvent e) {

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
            enemies.clear();
            bullet.clear();
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
    };
    MouseMotionAdapter mouseMotionAdapter = new MouseMotionAdapter() {
      @Override
      public void mouseMoved(MouseEvent e) {
        if (state == RUNNING) {
          Hero.hero.x = e.getX() - Hero.hero.width / 2;
          Hero.hero.y = e.getY() - Hero.hero.height / 2;
        }
      }
    };
    addMouseListener(mouseListener);
    addMouseMotionListener(mouseMotionAdapter);
  }

  public static void main(String[] args) {
    WORLD.frame.add(WORLD);// add WORLD to window
    WORLD.start();
    WORLD.frame.setSize(WIDTH, HEIGHT);// set the window size
    WORLD.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// set the (x)close function
    WORLD.frame.setLocationRelativeTo(null);// window initialized location
    WORLD.frame.setVisible(true);// display the window
    WORLD.frame.setBackground(Color.white);
  }

  private void checkRunningAction() {
    if (state == RUNNING) {
      if (Hero.hero.getLife() <= 0) {
        state = GAME_OVER;
      }
    }
  }

  // generate enemy
  private Enemy newEnemy() {
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
      Enemy enemy = newEnemy();
      enemies.offer(enemy);
    }
  }

  private void shootAction() {
    shootIndex++;
    if (shootIndex % 55 == 0) {
      ConcurrentLinkedDeque<Bullet> bs = Hero.hero.shoot();
      bullet.addAll(bs);
    }
  }

  private void stepAction() {
    Sky.sky.step();
    for (Enemy enemy : enemies) {
      enemy.step();
    }
    for (Bullet value : bullet) {
      value.step();
    }
  }


  private void clearAction() {
    Iterator<Enemy> enemyIterator = enemies.iterator();
    while (enemyIterator.hasNext()) {
      Enemy e = enemyIterator.next();
      e.outOfBounds();
      if (e.isRemove()) {
        enemyIterator.remove();
      }
    }

    Iterator<Bullet> bulletIterator = bullet.iterator();
    while (enemyIterator.hasNext()) {
      Bullet b = bulletIterator.next();
      b.outOfBounds();
      if (b.isRemove()) {
        bulletIterator.remove();
      }
    }
  }

  private void hitBulletAction() {
    clearAction();
    for (Bullet b : bullet) {
      for (Enemy e : enemies) {
        if (e.hit(b) && e.isDead() && e.getIndex() == 1) {
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
    for (Enemy e : enemies) {
      if (e.hit(Hero.hero) && e.getIndex() == 4) {
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
        g.drawImage(START_IMAGE, 40, 99, null);
        break;
      case RUNNING:
        g.clearRect(0, 0, WIDTH, HEIGHT);
        Sky.sky.paintObject(g);
        for (Enemy enemy : enemies) {
          enemy.paintObject(g);
        }
        for (Bullet value : bullet) {
          value.paintObject(g);
        }
        Hero.hero.paintObject(g);
        g.setFont(new Font(null, Font.PLAIN, 15));
        g.drawString("SCORE:" + score, 50, 30);
        g.setFont(new Font(null, Font.PLAIN, 15));
        g.drawString("LIFE:" + Hero.hero.getLife(), 50, 50);
        break;
      case PAUSE:
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(PAUSE_IMAGE, 40, 99, null);
        break;
      case GAME_OVER:
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setFont(new Font(null, Font.BOLD, 30));
        g.drawString("FINAL SCORE:" + score, 140, 300);
        g.drawImage(GAMEOVER_IMAGE, 40, 99, null);
        break;
    }
  }
}
