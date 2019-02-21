package io.github.hingbong.shoot;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class World extends JPanel {
  // the unit in the world
  Hero hero = Hero.hero;
  Sky sky = Sky.sky;
  FlyObject[] flies = new FlyObject[3];
  Bullet bullet = new Bullet(120, 350);

  public void start() {
    flies[0] = new AirPlane();
    flies[1] = new Bee();
    flies[2] = new BigPlane();
  }

  @Override
  public void paint(Graphics g) {
    sky.paintObject(g);
    hero.paintObject(g);

  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Shoot Game");// initialize the window
    World w = new World();// run start method in main method
    frame.add(w);// add world to window
    frame.setSize(400, 700);// set the window size
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// set the (x)close function
    frame.setLocationRelativeTo(null);// window initialized location
    frame.setVisible(true);// display the window
    w.start();
  }
}
