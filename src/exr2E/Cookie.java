/**
 * 
 */
package exr2E;

import role.SpriteModel;

/**
 * @author Krish Pillai
 *
 */
public class Cookie extends SpriteModel {

	private String imageFile = "cookie-1.png";
	
	public Cookie(int x, int y, int layer) {
		super(x, y, layer);
	}
	
	@Override
	protected void setAppearance() {
		setView(imageFile);
	}

	@Override
	protected void updateParameters(long elapsedTime) {

	}

}
