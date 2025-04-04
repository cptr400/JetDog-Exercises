/**
 * 
 */
package exr5B;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;
import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import role.GameController;
import util.SoundEffects;

/**
 * 
 */
public class Exercise5B extends GameController {

	private static final long serialVersionUID = 1L;
	private float theta = 2.0f;
	private int firingDelay = 200;
	private long firingInstant;
	private float missileSpeed = 20.0f;
	private Ship ship;

	/**
	 * 
	 */
	public Exercise5B() {
		setTitle("Exercise 5B");
	}

	/**
	 * @param rate
	 */
	public Exercise5B(int rate) {
		super(rate);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param rate
	 * @param size
	 */
	public Exercise5B(int rate, Dimension size) {
		super(rate, size);
		// TODO Auto-generated constructor stub
	}

	private void fireMissile() {
		// Check if firing delay has elapsed to avoid cannon blasting
		if (System.currentTimeMillis() - firingInstant > firingDelay) {

			double rotation = ship.getRotation() * Math.PI / 180.0;
			float xv = missileSpeed * (float) Math.cos(rotation);
			float yv = missileSpeed * (float) Math.sin(rotation);
			LaserBolt missile = new LaserBolt(ship.getXLocation() + (int) (2 * xv),
					ship.getYLocation() + (int) (2 * yv));
			missile.setActive(true);
			missile.setXVelocity(xv);
			missile.setYVelocity(yv);
			missile.setShowBounds(true);
			firingInstant = System.currentTimeMillis();
			SoundEffects.SHOOT.play();
			addEntity(missile);
		}
	}

	@Override
	protected void enlistEntities() {
		ship = new Ship(getWidth() / 2, getHeight() / 2);
		ship.setActive(true);
		addEntity(ship);
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected void onKeysPolled(int[] keyCodes) {
		boolean kl, kr;
		kl = kr = false;
		for (int code : keyCodes) {
			switch (code) {
			case KeyEvent.VK_LEFT:
				kl = true;
				ship.setTheta(-theta);
				break;
			case KeyEvent.VK_RIGHT:
				kr = true;
				ship.setTheta(theta);
				break;
			case KeyEvent.VK_SPACE:
				if (ship.isActive())
					fireMissile();
				break;
			case KeyEvent.VK_ESCAPE:
				dispose();
				System.exit(0);
				break;
			default:
				ship.setTheta(0);
			}

		}

		if (kl && kr) {
			ship.setTheta(0);
		}

	}
	@Override
	protected void onMessagesPolled(MessageEvent[] events) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateGameState() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onOutOfBounds(OutOfBoundsEvent[] events) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onBlocked(BlockedEvent[] events) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->new Exercise5B());
	}

}
