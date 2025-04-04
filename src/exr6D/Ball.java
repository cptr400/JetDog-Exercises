/**
 * 
 */
package exr6D;

import role.SpriteModel;

/**
 * 
 * @author Krish Pillai
 *
 */
public class Ball extends SpriteModel {

	private final String imageFile = "dot-6.png";

	private float theta = 0.0f;
	private int dir = 1;
	
	/**
	 * @param x
	 * @param y
	 */
	public Ball(int x, int y) {
		super(x,y);
	}

	@Override
	protected void setAppearance() {
		setView(imageFile, 10);
		setScale(0.15f);
		setCollisionBoundsToViewBounds();
		setShowBounds(true);
	}

	@Override
	public void updateParameters(long elapsedTime) {
		float xvel = getXVelocity();
		dir = xvel < 0 ? -1 : 1;
		theta += dir;
		theta %= 360;
		setRotationAngle(theta);
		float speed = getSpeed();
		if (speed <= 10.0f)
			notifyController("stopped");
	}

}
