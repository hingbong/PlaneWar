package game;

public class AirPlane extends Plane {

    public AirPlane() {
	super();
	this.width = 49;
	this.height = 36;
	this.y = -this.height;
	this.x = (int) (Math.random() * (400 - this.height + 1));
    }

}
