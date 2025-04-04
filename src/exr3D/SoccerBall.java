/**
 * 
 */
package exr3D;

import role.SpriteModel;

/**
 * @author Krish Pillai
 */
public class SoccerBall extends SpriteModel {

	private final String imageFile = "soccerball-1.png";
	private float theta = 0.0f;
	private int dir = 1;
	
	
	/**
	 * @param x
	 * @param y
	 */
	public SoccerBall(int x, int y) {
		super(x,y);
	}

	@Override
	protected void setAppearance() {
		setView(imageFile, 0.5f, 1);
		setCoefficientOfRestitution(0.99f);		
	}

	@Override
	public void updateParameters(long elapsedTime) {
		// TODO Auto-generated method stub
		float xvel = getXVelocity();
		dir = xvel < 0 ? -1 : 1;
		theta += dir * 5.0f;
		theta %= 360;
		setRotationAngle(theta);
	}

}
