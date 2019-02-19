package io.github.hingbong.shoot;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class World extends JPanel {
  // the unit in the world
  Hero hero;
  Sky sky = Sky.sky;
  FlyObject[] flies = new FlyObject[30];

  public void start() {
    flies[0] = new AirPlane();
    flies[1] = new Bee();
    flies[2] = new BigPlane();
    flies[3] = new AirPlane();
    flies[4] = new AirPlane();
    flies[5] = new BigPlane();
    for (FlyObject f : flies) {
      if (f == null) {
        break;
      }
      System.out.println(f);
    }
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Shoot Game");// initialize the window
    World w = new World();// run start method in main method
    frame.setSize(400, 700);// set the window size
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// set the (x)close function
    frame.setLocationRelativeTo(null);// window initialized location
    frame.setVisible(true);// display the window
    w.start();
  }
}
