package game;

public class Hero extends FlyObject {
  public int life;
  public int doubleFire;

  public Hero() {
    super();
    this.width = 97;
    this.height = 124;
    this.x = 200 - this.width / 2;
    this.y = 500;
    this.life = 3;// at the beginning of the game,there is 3 lives
    this.doubleFire = 0;// at the beginning of the game,there is single bullet
  }

  @Override
  public String toString() {
    return "Hero [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", life="
        + life + ", doubleFire=" + doubleFire + "]";
  }



}
