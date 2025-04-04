/**
 * 
 */
package exr3C;

import role.SpriteModel;

/**
 * @author kpillai
 *
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
		// You can call it with a scale factor
		setView(imageFile, 0.5f, 1);
		setShowBounds(true);
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
		float speed = getSpeed();
		if (speed <= 10.0f)
			notifyController("stopped");
	}

}
