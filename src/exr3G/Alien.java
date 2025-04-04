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
public class Alien extends SpriteModel {

	/**
	 * @param x
	 * @param y
	 */
	public Alien(int x, int y, int layer) {
		super(x,y, layer);
	}

	@Override
	protected void setAppearance() {
		setView(sprite_greenmanB, 8);
	}

	@Override
	public void updateParameters(long elapsedTime) {

	}
	

}
