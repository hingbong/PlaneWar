package game;

public class Hero extends FlyObject {
  public int life;
  public int doubleFire;

  public Hero() {
    super();
    width = 97;
    height = 124;
    x = 200 - this.width / 2;
    y = 500;
    life = 3;// at the beginning of the game,there is 3 lives
    doubleFire = 0;// at the beginning of the game,there is single bullet
  }

  @Override
  public String toString() {
    return "Hero [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", life="
        + life + ", doubleFire=" + doubleFire + "]";
  }



}
