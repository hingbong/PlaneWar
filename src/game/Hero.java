package game;

public class Hero extends FlyObject {
  public int life;
  public int doubleFire;

  public Hero() {
    super(200 - 97 / 2, 500, 97, 124);
    life = 3;// at the beginning of the game,there are 3 lives
    doubleFire = 0;// at the beginning of the game,there is single bullet
  }

  @Override
  public String toString() {
    return "Hero [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", life="
        + life + ", doubleFire=" + doubleFire + "]";
  }

  @Override
  public void step() {}



}
