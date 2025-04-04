package exr4C;

import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import role.EntityModel;
import role.GameController;
import util.SoundEffects;

public class Exercise4C extends GameController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ship ship;
	private Missile missile;
	private float shipVelocity = 15.0f;
	private float missileVelocity = -20.0f;
	private boolean leftEdge, rightEdge;
	private int firingDelay = 200;
	private long firingInstant;

	public Exercise4C() {
		setTitle("Exercise 4C");
	}

	@Override
	protected void enlistEntities() {
		ship = new Ship(getWidth() / 2, getHeight() - 50);
		ship.setActive(true);
		addEntity(ship);
		firingInstant = System.currentTimeMillis();
	}

	private void fireMissile() {
		// Check if firing delay has elapsed to avoid cannon blasting
		if (System.currentTimeMillis() - firingInstant > firingDelay) {
			// locate missile away from the ship to avoid collision
			missile = new Missile((int) ship.getXLocation(), (int) ship.getYLocation() - (int) ship.getHitboxHeight());
			missile.setActive(true);
			missile.setYVelocity(missileVelocity);
			firingInstant = System.currentTimeMillis();
			SoundEffects.SHOOT.play();
			addEntity(missile);
		}
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {

	}
	
	@Override
	protected void onKeysPolled(int[] keyCodes) {
		boolean kl = false, kr = false;
		for (int code : keyCodes) {
				if (!rightEdge && code == KeyEvent.VK_RIGHT) {
					ship.setXVelocity(shipVelocity);
					leftEdge = false;
					kr = true;
				} else if (!leftEdge && code == KeyEvent.VK_LEFT) {
					ship.setXVelocity(-shipVelocity);
					rightEdge = false;
					kl = true;
				} 
				if (code == KeyEvent.VK_SPACE)
					fireMissile();
			}

		if (!(kl || kr))
			ship.setXVelocity(0);
		if (kl && kr)
			ship.setXVelocity(0);
		
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
				if (edge == Direction.East)
					rightEdge = true;
				if (edge == Direction.West)
					leftEdge = true;
			}
			if (em instanceof Missile)
				em.dispose();
		}
	}

	@Override
	protected void onBlocked(BlockedEvent[] events) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> new Exercise4C());

	}

}
