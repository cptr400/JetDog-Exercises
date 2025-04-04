package exr4B;

import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import role.EntityModel;
import role.GameController;

public class Exercise4B extends GameController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ship ship;
	private float shipVelocity = 15.0f;
	private boolean leftEdge, rightEdge;

	public Exercise4B() {
		setTitle("Exercise 4B");
	}

	@Override
	protected void enlistEntities() {
		ship = new Ship(getWidth() / 2, getHeight() - 50);
		ship.setActive(true);
		addEntity(ship);
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {

	}

	@Override
	protected void onKeysPolled(int[] keyCodes) {
		boolean kl = false, kr = false;
		for (int code : keyCodes) {

					if (!leftEdge && code == KeyEvent.VK_LEFT) {
						ship.setXVelocity(-shipVelocity);
						rightEdge = false;
						kl = true;
					} 	
					if (!rightEdge && code == KeyEvent.VK_RIGHT) {
						ship.setXVelocity(shipVelocity);
						leftEdge = false;
						kr = true;
					} 

		}
		if (!(kl || kr )){
			ship.setXVelocity(0);
		}
		// avoid punch through by holding down one key and tapping the other
		if (kl && kr) {
			ship.setXVelocity(0);
		}
	}

	@Override
	protected void updateGameState() {

	}

	@Override
	public void onMessagesPolled(MessageEvent[] events) {

	}

	@Override
	protected void onOutOfBounds(OutOfBoundsEvent[] events) {
		for (OutOfBoundsEvent e : events) {
			EntityModel em = e.getEntity();
			Direction edge = e.getEdge();
			if (em instanceof Ship) {
				if (edge == Direction.West)
					leftEdge = true;
				if (edge == Direction.East)
					rightEdge = true;

			}
		}
	}

	@Override
	protected void onBlocked(BlockedEvent[] events) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Exercise4B());
	}

}
