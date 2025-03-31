/**
 * 
 */
package src.exr1A;

import role.SpriteModel;

/**
 * 
 * @author Krish Pillai
 *
 */
public class Alien extends SpriteModel {

	private final String imageFile = "alien-3.png";

	public Alien(int x, int y) {
		super(x, y);
	}

	@Override
	protected void setAppearance() {
		setView(imageFile, 1.0f, 3);
	}

	@Override
	protected void updateParameters(long elapsedTime) {
		//TODO
	}

}
