/**
 * 
 */
package exr3G;

import role.SpriteModel;

/**
 * 
 * @author Krish Pillai
 *
 */
public class Bee extends SpriteModel {

	private final String imageFile = "bee-4.png";
	
	/**
	 * @param x
	 * @param y
	 */
	public Bee(int x, int y, int layer) {
		super(x, y, layer);
	}

	@Override
	protected void setAppearance() {
		setView(imageFile, 4);
		setFlipped(true);
		setScale(0.3f);
	}

	@Override
	public void updateParameters(long elapsedTime) {

	}

}
