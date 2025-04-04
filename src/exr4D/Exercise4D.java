package exr4D;

import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.SwingUtilities;

import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import role.EntityModel;
import role.GameController;
import role.SpriteModel;
import util.SoundEffects;

public class Exercise4D extends GameController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ship ship;
	private Missile missile;
	private SpriteModel alien1, alien2;
	private float shipVelocity = 15.0f;
	private float missileVelocity = -20.0f;
	private boolean leftEdge, rightEdge;
	private int firingDelay = 200;
	private long firingInstant;
	private Random rand;

	public Exercise4D() {
		setTitle("Exercise 4D");
		rand = new Random();
	}

	@Override
	protected void enlistEntities() {
		ship = new Ship(getWidth() / 2, getHeight() - 50);
		ship.setActive(true);
		firingInstant = System.currentTimeMillis();
		alien1 = createAlien("alien-3.png", 250);
		alien1.setXVelocity(10.0f);
		alien1.setActive(true);	
		alien1.setWrappedMode(true);
		alien2 = createAlien("alien-4.png", 100 + rand.nextInt(400));
		alien2.setXVelocity(5.0f);
		alien2.setActive(true);	
		addEntity(ship, alien1, alien2);
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
	
	private SpriteModel createAlien(String resource, int y) {
		SpriteModel alien = new SpriteModel(50, y) {
			
			@Override
			protected void updateParameters(long elapsedTime) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			protected void setAppearance() {
				setView(resource, 3);
			}
		};

		return alien;
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
		for (MessageEvent me : events) {
			String msg = me.getDescription();
			EntityModel sm = me.getSender();
			if (sm.getID() == alien2.getID()) {
				alien2 = createAlien("alien-4.png", 100 + rand.nextInt(400));
				alien2.setXVelocity(5.0f);
				alien2.setActive(true);	
				addEntity(alien2);
			}
		}
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
			if (em == alien2)
				em.dispose();
		}
	}

	@Override
	protected void onBlocked(BlockedEvent[] events) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> new Exercise4D());

	}

}
