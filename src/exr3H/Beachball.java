/**
 * 
 */
package exr3H;

import role.SpriteModel;

/**
 * 
 * @author Krish Pillai
 *
 */
public class Beachball extends SpriteModel {

	private final String imageFile = "beachball-4.png";

	/**
	 * @param x
	 * @param y
	 */
	public Beachball(int x, int y) {
		// on layer 1
		super(x, y, 1);
	}

	@Override
	protected void setAppearance() {
		setView(imageFile, 20);
		setScale(0.2f);
	}

	@Override
	public void updateParameters(long elapsedTime) {

	}

}
