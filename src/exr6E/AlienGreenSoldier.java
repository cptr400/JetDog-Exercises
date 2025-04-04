/**
 * 
 */
package exr6E;

/**
 * Basic Alien speeds up as it advances south wards.
 * @author Krish Pillai
 *
 */
public class AlienGreenSoldier extends AlienSoldier {

	private final String imageFile = "alienx-7.png";
	private int cost = 5;
	private String message = "fire:A";

	public AlienGreenSoldier(int x, int y) {
		super(x, y);
		setCost(cost);
		setCost(cost);
		setFiringThreshold(20);
		setFiringDelayIncrement(0.1f);
	}

	@Override
	protected void setAppearance() {
		setView(imageFile, 4);
		setScale(0.7f);
		setCollisionBoundsToViewBounds();
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	protected void signal() {
			notifyController(message);	
	}
}
