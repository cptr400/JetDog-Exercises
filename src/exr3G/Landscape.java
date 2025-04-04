/**
 * 
 */
package exr3G;

import role.SceneryModel;

/**
 * 
 */
public class Landscape extends SceneryModel {

	private String imageFile = "ufoscene.png";
	/**
	 * @param width
	 * @param height
	 */
	public Landscape(int width, int height, int layer) {
		super(width, height, layer);
	}


	@Override
	protected void setAppearance() {
		setView(imageFile);
		setGhost(true);
	}

}
