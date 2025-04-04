/**
 * 
 */
package exr6E;

import role.SpriteModel;

/**
 * @author kpillai
 *
 */
public abstract class Alien extends SpriteModel {

	private float alienVelocity = 10.0f;
	private float stepVelocity = 0.7f;
	private int stepForward = 20;
	
	public Alien(int x, int y) {
		super(x, y);
	}

	@Override
	public String toString() {
		return super.toString();
	}


	protected void speedUp() {
		alienVelocity += stepVelocity;
	}
	
	protected void moveLeft() {
		setXVelocity(-alienVelocity);
	}
	
	protected void moveRight() {
		setXVelocity(alienVelocity);
	}
	
	protected void stepForward() {
		setYLocation(getYLocation()+ stepForward);
	}

}
