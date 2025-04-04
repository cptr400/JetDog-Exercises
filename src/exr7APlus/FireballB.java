/**
 * 
 */
package exr7APlus;

/**
 * @author Krish Pillai
 *
 */
public class FireballB extends Fireball{
	
	private final String missileImage = "fireball-6.png";
	private float fireballVelocity = 40.0f;


	public FireballB(int x, int y) {
		super(x,y);
		setYVelocity(fireballVelocity);
		setCost(2);
	}


	@Override
	protected void setAppearance() {
		setView(missileImage, 3);
		setScale(0.4f);
	}


	@Override
	protected void updateParameters(long elapsedTime) {
		// TODO Auto-generated method stub
		
	}


}
