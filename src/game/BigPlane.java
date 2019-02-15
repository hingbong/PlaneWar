package game;

public class BigPlane extends Plane {

    public BigPlane() {
	super();
	this.width = 69;
	this.height = 99;
	this.y = -this.height;
	this.x = (int) (Math.random() * (400 - this.height + 1));
    }

}
