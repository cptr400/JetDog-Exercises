package exr5A;

import role.SpriteModel;

public class Ship extends SpriteModel{
	
	private static String imageFile = "ship-1.png";
	private float theta = 0.0f;
	
	public Ship(int x, int y){
		super(x, y);
	}
	
	/**
	 * @return the theta
	 */
	public float getTheta() {
		return theta;
	}

	/**
	 * @param theta the theta to set
	 */
	public void changeDirection(float theta) {
		this.theta = theta;
	}

	@Override
	public void updateParameters(long elapsedTime){
		if (theta != 0) {
			setRotationAngle(getRotation()+theta);
		}
	}

	@Override
	protected void setAppearance() {
		setView(imageFile);
		setScale(2.0f);
	}
		
}
