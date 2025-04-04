/**
 * 
 */
package exr3F;

import role.SpriteModel;

/**
 * 
 */
public class Airplane extends SpriteModel {


	public Airplane(int x, int y) {
		super(x, y);
	}

	@Override
	protected void setAppearance() {
		setView(sprite_airlinerB, 8);
	}

	@Override
	protected void updateParameters(long elapsedTime) {
		// TODO Auto-generated method stub
		
	}

}
