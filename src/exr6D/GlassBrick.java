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
public class GlassBrick extends SpriteModel {

	private final String imageFile = "glassbrick-1.png";

	
	/**
	 * @param x
	 * @param y
	 */
	public GlassBrick(int x, int y) {
		super(x,y);
	}

	@Override
	protected void setAppearance() {
		setView(imageFile, 1);
		setScale(0.3f);
		setCoefficientOfRestitution(0.8f);
		setCollisionBoundsToViewBounds();
	}

	@Override
	public void updateParameters(long elapsedTime) {

	}

}
