/**
 * 
 */
package exr3B;

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
		setView(imageFile, 1);
		setScale(0.5f);
		// since scale has changed, the following method readjusts the hit box to match
		// the dimensions of the scaled view
		setCollisionBoundsToViewBounds();
		//setShowBounds(true);
		setCoefficientOfRestitution(0.99f);		
	}

	@Override
	public void updateParameters(long elapsedTime) {
		float xvel = getXVelocity();
		float abs = Math.abs(xvel);
		
		if ( abs < 5.0) {
			setYAcceleration(0);
			setYVelocity(0);
			return;
		}
		dir = xvel < 0 ? -1 : 1;
		theta += dir * abs / 2.0f;
		theta %= 360;
		setRotationAngle(theta);
	}

}
