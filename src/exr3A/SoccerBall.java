/**
 * 
 */
package exr3A;

import role.SpriteModel;

/**
 * 
 * @author Krish Pillai
 *
 */
public class SoccerBall extends SpriteModel {

	private final String imageFile = "soccerball-1.png";
	private int theta = 0;
	
	/**
	 * @param x
	 * @param y
	 */
	public SoccerBall(int x, int y) {
		super(x,y);
	}

	@Override
	protected void setAppearance() {
		setView(sprite_soccerballA, 0.45f, 1);
	}

	@Override
	public void updateParameters(long elapsedTime) {
		setRotationAngle(theta++);
		theta %= 360;
	}

}
