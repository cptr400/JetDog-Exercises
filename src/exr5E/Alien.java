/**
 * 
 */
package exr5E;

import role.SpriteModel;

/**
 * @author Krish Pillai
 */
public class Alien extends SpriteModel {

	private final String imageFile = "alien-3.png";
	
	public Alien(int x, int y) {
		super(x, y);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	protected void setAppearance() {
		setView(imageFile, 3);
	}


	@Override
	protected void updateParameters(long elapsedTime) {
		// TODO Auto-generated method stub
		
	}


}
