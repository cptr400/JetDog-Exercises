package exr2E;

public class SeekingAlien extends Alien {

	private int destX = 400, destY = 300;
	private float speed;
	
	public SeekingAlien(int x, int y, int layer) {
		super(x, y, layer);
	}

	private void updateVelocity() {
		int x = getXLocation();
		int y = getYLocation();

		float deltaX = destX - x;
		float deltaY = destY - y;

		double theta = Math.atan2(deltaY, deltaX);;

		float xv = (float) (speed * Math.cos(theta));
		float yv = (float) (speed * Math.sin(theta));
		setXVelocity(xv);
		setYVelocity(yv);
	}
	
	
	/**
	 * @return the destX
	 */
	public int getDestX() {
		return destX;
	}

	/**
	 * @param x the destX to set
	 */
	public void setDestX(int x) {
		this.destX = x;
	}

	/**
	 * @return the destY
	 */
	public int getDestY() {
		return destY;
	}

	/**
	 * @param y the destY to set
	 */
	public void setDestY(int y) {

		this.destY = y;
	}


	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	@Override
	protected void updateParameters(long elapsedTime) {
			updateVelocity();
	}

}
