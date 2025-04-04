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
public class Paddle extends SpriteModel {

	private final String imageFile = "paddleA-1.png";

	
	/**
	 * @param x
	 * @param y
	 */
	public Paddle(int x, int y) {
		super(x,y);
	}

	@Override
	protected void setAppearance() {
		setView(imageFile, 1);
		setScale(0.25f);
		setCollisionBoundsToViewBounds();
		//setShowBounds(true);
	}

	@Override
	public void updateParameters(long elapsedTime) {

	}

}
