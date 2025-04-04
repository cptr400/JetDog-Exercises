/**
 * 
 */
package exr5E;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.SwingUtilities;
import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import role.EntityModel;
import role.GameController;
import util.SoundEffects;

/**
 * @author Krish Pillai
 */
public class Exercise5E extends GameController {

	private static final long serialVersionUID = 1L;
	private Ship ship;
	private float shipVelocity = 15.0f;
	private boolean shipLeftEdge = false, shipRightEdge = false;

	private AlienArmy alienArmy;
	private final int rank = 4, file = 5; // defines rank & file
	int firingDelay = 520;
	long firingInstant = System.currentTimeMillis();
	private float armyVelocity = 8.0f;

	private float missileVelocity = -30.0f;

	private boolean gameOver = false;

	/**
	 * 
	 */
	public Exercise5E() {
		setTitle("Exercise 5E");
	}

	/**
	 * @param rate
	 */
	public Exercise5E(int rate) {
		super(rate);
	}

	/**
	 * @param rate
	 * @param size
	 */
	public Exercise5E(int rate, Dimension size) {
		super(rate, size);
		// TODO Auto-generated constructor stub
	}

	private void generateExplosion(EntityModel projectile, EntityModel entity) {
		removeEntity(projectile, entity);
		Explosion e = new Explosion(entity.getXLocation(), entity.getYLocation());
		addEntity(e);
	}

	private void steerShip(int key) {
		// to avoid both keys or tapping
		if (!shipRightEdge && key == KeyEvent.VK_RIGHT) {
			ship.setXVelocity(shipVelocity);
			shipLeftEdge = false;
		} else if (!shipLeftEdge && key == KeyEvent.VK_LEFT) {
			ship.setXVelocity(-shipVelocity);
			shipRightEdge = false;
		} else {
			ship.setXVelocity(0);
		}
	}

	private void fireMissile() {
		// Check if firing delay has elapsed to avoid cannon blasting
		if (System.currentTimeMillis() - firingInstant > firingDelay) {
			// locate missile away from the ship to avoid collision
			Missile missile = new Missile(ship.getXLocation(), ship.getYLocation() - (int) ship.getHitboxWidth());
			// Uncomment next line to see bounds
			// missile.setShowBounds(true);
			addEntity(missile);
			missile.setActive(true);
			missile.setYVelocity(missileVelocity);
			firingInstant = System.currentTimeMillis();
			SoundEffects.SHOOT.play();
		}

	}

	@Override
	protected void enlistEntities() {
		alienArmy = new AlienArmy(rank, file);
		addEntity(alienArmy.getAliens());
		alienArmy.setVelocity(armyVelocity);
		alienArmy.setActive(true);
		ship = new Ship(getWidth() / 2, getHeight() - 75);
		ship.setXVelocity(20);
		ship.setActive(true);
		addEntity(ship);
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {
		for (CollisionEvent ce : events) {

			EntityModel a = ce.getA();
			EntityModel b = ce.getB();

			if (a instanceof AlienSoldier || b instanceof AlienSoldier) {
				AlienSoldier alien = a instanceof AlienSoldier ? (AlienSoldier) a : (AlienSoldier) b;
				alienArmy.delete(alien);
				SoundEffects.COLLIDE.play();
				if (alienArmy.isEmpty())
					alienArmy.setActive(false);
			}

			if (a instanceof Ship || b instanceof Ship) {
				ship.setActive(false);
				alienArmy.setActive(false);
				gameOver = true;
			}
			removeEntity(a, b);
			generateExplosion(a, b);
		}
	}

	@Override
	protected void onKeysPolled(int[] keyCodes) {
		// prevent tapping on left/right keys
		boolean kl = false, kr = false;

		for (int key : keyCodes) {

			switch (key) {
			case KeyEvent.VK_SPACE:
				if (!gameOver)
					fireMissile();
				break;
			case KeyEvent.VK_LEFT:
				kl = true;
				if (!gameOver)
					steerShip(key);
				break;
			case KeyEvent.VK_RIGHT:
				kr = true;
				if (!gameOver)
					steerShip(key);
				break;
			case KeyEvent.VK_ESCAPE:
				dispose();
				System.exit(0);
			}

		}
		if (kl && kr)
			ship.setXVelocity(0);
		// DeMorgan's law - negation of disjunction (below) is conjunction of negations (needed)
		// fewer boolean operations on the CPU
		if (!(kl || kr))
			ship.setXVelocity(0);
	}

	@Override
	protected void onMessagesPolled(MessageEvent[] events) {

	}

	@Override
	protected void updateGameState() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onOutOfBounds(OutOfBoundsEvent[] events) {
		boolean oobDetected = false;
		for (OutOfBoundsEvent event : events) {
			EntityModel em = event.getEntity();
			Direction edge = event.getEdge();

			if (em instanceof Ship) {
				if (edge == Direction.East)
					shipRightEdge = true;
				if (edge == Direction.West)
					shipLeftEdge = true;
			}

			if (!oobDetected && em instanceof Alien) {
				if (edge == Direction.East || edge == Direction.West) {
					alienArmy.advance(edge);
					oobDetected = true;
				}
			}
		}
	}

	@Override
	protected void onBlocked(BlockedEvent[] events) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Exercise5E());
	}

}
