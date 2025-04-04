package exr5F;

import role.SpriteModel;

public class Fireball extends SpriteModel{

	private final String imageFile = "fireball-4.png";
	private float velocity = 10.0f;
	
	public Fireball(int x, int y){
		super(x, y);
		setYVelocity(velocity);
		setCollisionBounds(10, 10);
	}

	/**
	 * @return the velocity
	 */
	public float getVelocity() {
		return velocity;
	}

	/**
	 * @param velocity the velocity to set
	 */
	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

	@Override
	protected void setAppearance() {
		setView(imageFile, 3);
		setScale(0.2f);
		setActive(true);
	}

	@Override
	protected void updateParameters(long elapsedTime) {
		// TODO Auto-generated method stub
		
	}


}
