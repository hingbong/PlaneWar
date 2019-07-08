package io.github.hingbong.shoot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentHashMap.KeySetView;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

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

  /**
   * the unit in the WORLD
   */
  private static final World WORLD = new World();
  private static final Random RANDOM = new Random();
  private static int state = START;

  static {
    START_IMAGE = BaseFlyObject.loadImage("start.png");
    PAUSE_IMAGE = BaseFlyObject.loadImage("pause.png");
    GAMEOVER_IMAGE = BaseFlyObject.loadImage("gameover.png");
  }

  // initialize the window
  private final JFrame frame = new JFrame("Shoot Game");

  private final KeySetView<AbstractEnemy, Boolean> enemies = ConcurrentHashMap.newKeySet(30);
  private final KeySetView<Bullet, Boolean> bullet = ConcurrentHashMap.newKeySet(30);
  private final Sky sky = Sky.getSky();
  private final Hero hero = Hero.getHero();
  private int enterIndex = 0;
  private int shootIndex = 0;
  private int score = 0;

  private World() {
  }

  public static void main(String[] args) {
    WORLD.frame.add(WORLD); // add WORLD to window
    WORLD.init();
    WORLD.start();
    WORLD.frame.setSize(WIDTH, HEIGHT); // set the window size
    WORLD.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set the (x)close function
    WORLD.frame.setLocationRelativeTo(null); // window initialized location
    WORLD.frame.setVisible(true); // display the window
    WORLD.frame.setBackground(Color.white);
  }

  private void init() {
    MouseInputAdapter mouseListener =
        new MouseInputAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            switch (state) {
              case START:
                state = RUNNING;
                break;
              case GAME_OVER:
                score = 0;
                hero.initHero();
                hero.doubleFireClear();
                sky.initSky();
                enemies.clear();
                bullet.clear();
                state = START;
                break;
              default:
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
    MouseMotionAdapter mouseMotionAdapter =
        new MouseMotionAdapter() {
          @Override
          public void mouseMoved(MouseEvent e) {
            if (state == RUNNING) {
              hero.x = e.getX() - hero.width / 2;
              hero.y = e.getY() - hero.height / 2;
            }
          }
        };
    addMouseListener(mouseListener);
    addMouseMotionListener(mouseMotionAdapter);
  }

  private void checkRunningAction() {
    if (state == RUNNING) {
      if (hero.getLife() <= 0) {
        state = GAME_OVER;
      }
    }
  }

  // generate enemy
  private AbstractEnemy newEnemy() {
    int number = RANDOM.nextInt(100);
    if (number < 20) {
      return new Bee();
    } else {
      return number < 55 ? new AirPlane() : new BigPlane();
    }
  }

  private void enterAction() {
    enterIndex++;
    if (enterIndex % 40 == 0) {
      AbstractEnemy abstractEnemy = newEnemy();
      enemies.add(abstractEnemy);
    }
  }

  private void shootAction() {
    shootIndex++;
    if (shootIndex % 55 == 0) {
      KeySetView<Bullet, Boolean> bs = hero.shoot();
      bullet.addAll(bs);
    }
  }

  private void stepAction() {
    sky.step();
    for (AbstractEnemy abstractEnemy : enemies) {
      abstractEnemy.step();
    }
    for (Bullet value : bullet) {
      value.step();
    }
  }

  private void enemyClearAction() {
    enemies.removeIf(e -> e.outOfBounds() || e.isRemove());
  }

  private void bulletClearAction() {
    bullet.removeIf(b -> b.outOfBounds() || b.isRemove());
  }

  private void hitBulletAction() {
    for (Bullet b : bullet) {
      for (AbstractEnemy e : enemies) {
        if (e.hit(b) && e.isDead() && e.getIndex() == 1) {
          if (e instanceof Bee) {
            switch (((Bee) e).getRewardType()) {
              case Bee.LIFE:
                hero.addLife();
                break;
              case Bee.DOUBLE_FIRE:
                hero.addFire();
                break;
              default:
            }
          } else {
            score += ((EnemyScore) e).getScore();
          }
          b.setRemove();
          break;
        }
      }
    }
  }

  private void hitHeroAction() {
    for (AbstractEnemy e : enemies) {
      if (e.hit(hero) && e.getIndex() == 4) {
        hero.doubleFireClear();
        hero.minusLife();
        break;
      }
    }
  }

  private void start() {
    int initDelay = 0;
    // the bigger value, the slower speed
    int interval = 10;

    ScheduledExecutorService ex = new ScheduledThreadPoolExecutor(1, (ThreadFactory) Thread::new);
    ex.scheduleWithFixedDelay(
        () -> {
          if (state == RUNNING) {
            enterAction();
            shootAction();
            stepAction();
            enemyClearAction();
            hitBulletAction();
            hitHeroAction();
            checkRunningAction();
            bulletClearAction();
          }
          repaint();
        },
        initDelay,
        interval,
        TimeUnit.MILLISECONDS);
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
        sky.paintObject(g);
        enemies.parallelStream().forEach(e -> e.paintObject(g));
        bullet.parallelStream().forEach(b -> b.paintObject(g));
        hero.paintObject(g);
        g.setFont(new Font(null, Font.PLAIN, 15));
        g.drawString("SCORE:" + score, 50, 30);
        g.setFont(new Font(null, Font.PLAIN, 15));
        g.drawString("LIFE:" + hero.getLife(), 50, 50);
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
      default:
    }
  }
}
