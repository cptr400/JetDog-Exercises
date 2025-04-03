/**
 * 
 */
package exr2E;

import role.SpriteModel;

/**
 * @author kpillai
 *
 */
public class Alien extends SpriteModel {

	private final String imageFile = "alien-4.png";

	public Alien(int x, int y, int layer) {
		super(x, y, layer);
	}

	@Override
	protected void setAppearance() {
		setView(imageFile, 1.0f, 3);
	}


	@Override
	protected void updateParameters(long elapsedTime) {
		
	}

}
