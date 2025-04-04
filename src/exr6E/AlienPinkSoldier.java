/**
 * 
 */
package exr6E;

/**
 * Basic Alien speeds up as it advances south wards.
 * 
 * @author Krish Pillai
 *
 */
public class AlienPinkSoldier extends AlienSoldier {

	private final String imageFile = "alien-4.png";
	private int cost = 10;
	private String message = "fire:B";

	public AlienPinkSoldier(int x, int y) {
		super(x, y);
		setCost(cost);
		setFiringThreshold(30);
		setFiringDelayIncrement(0.3f);
	}

	@Override
	protected void setAppearance() {
		setView(imageFile, 10);
		setScale(0.3f);
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
