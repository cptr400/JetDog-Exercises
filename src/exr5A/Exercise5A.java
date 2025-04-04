/**
 * 
 */
package exr5A;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import role.GameController;

/**
 * 
 */
public class Exercise5A extends GameController {

	private static final long serialVersionUID = 1L;
	private float deltaTheta = 1.0f;
	private Ship ship;

	/**
	 * 
	 */
	public Exercise5A() {
		setTitle("Exercise 5A");
	}

	/**
	 * @param rate
	 */
	public Exercise5A(int rate) {
		super(rate);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param rate
	 * @param size
	 */
	public Exercise5A(int rate, Dimension size) {
		super(rate, size);
		// TODO Auto-generated constructor stub
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
				ship.changeDirection(-deltaTheta);
				break;
			case KeyEvent.VK_RIGHT:
				kr = true;
				ship.changeDirection(deltaTheta);
				break;
			case KeyEvent.VK_ESCAPE:
				dispose();
				System.exit(0);
				break;
			default:
				ship.changeDirection(0);
			}

		}

		if (kl && kr) {
			ship.changeDirection(0);
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
		SwingUtilities.invokeLater(()->new Exercise5A());
	}

}
