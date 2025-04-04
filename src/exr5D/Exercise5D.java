/**
 * 
 */
package exr5D;

import java.awt.Dimension;
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

/**
 * 
 */
public class Exercise5D extends GameController {

	private static final long serialVersionUID = 1L;
	private float theta = 2.0f;
	private int firingDelay = 200;
	private long firingInstant;
	private float missileSpeed = 20.0f;
	private Ship ship;
	private Cluster cluster;
	private Random rand;

	/**
	 * 
	 */
	public Exercise5D() {
		setTitle("Exercise 5D");
		rand = new Random();
	}

	/**
	 * @param rate
	 */
	public Exercise5D(int rate) {
		super(rate);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param rate
	 * @param size
	 */
	public Exercise5D(int rate, Dimension size) {
		super(rate, size);
		// TODO Auto-generated constructor stub
	}

	private void fireMissile() {
		// Check if firing delay has elapsed to avoid cannon blasting
		if (System.currentTimeMillis() - firingInstant > firingDelay) {

			double rotation = ship.getRotation() * (float) (Math.PI / 180.0);
			float xv = missileSpeed * (float) Math.cos(rotation);
			float yv = missileSpeed * (float) Math.sin(rotation);
			LaserBolt laser = new LaserBolt(ship.getXLocation() + (int) (2 * xv), ship.getYLocation() + (int) (2 * yv));
			laser.setActive(true);
			laser.setXVelocity(xv);
			laser.setYVelocity(yv);
			laser.setShowBounds(true);
			SoundEffects.SHOOT.play();
			firingInstant = System.currentTimeMillis();
			addEntity(laser);
		}
	}

	private void addSmoke(int x, int y) {
		SpriteModel smoke = new SpriteModel(x, y) {

			@Override
			protected void updateParameters(long elapsedTime) {
				// TODO Auto-generated method stub

			}

			@Override
			protected void setAppearance() {
				setView("smoke-4.png", 2);
				setTimeToLive(10);
			}
		};
		smoke.setActive(true);
		addEntity(smoke);
	}

	@Override
	protected void enlistEntities() {
		ship = new Ship(getWidth() / 2, getHeight() / 2);
		ship.setActive(true);
		cluster = new Cluster(getWidth() / 4, 3 * getHeight() / 4);
		cluster.setXVelocity(-5.0f);
		cluster.setYVelocity(5.0f);
		cluster.setActive(true);

		SpaceRock rock1 = new SpaceRock(getWidth() / 16, getHeight() / 16);
		SpaceRock rock2 = new SpaceRock(3 * getWidth() / 4, 3 * getHeight() / 4);
		rock1.setWrappedMode(true);
		rock2.setWrappedMode(true);
		rock1.setActive(true);
		rock2.setActive(true);

		rock1.setXVelocity(rand.nextInt(10));
		rock1.setYVelocity(rand.nextInt(10));
		rock2.setXVelocity(rand.nextInt(10));
		rock2.setYVelocity(rand.nextInt(10));

		addEntity(ship, rock1, rock2);
		addEntity(cluster.getRocks());
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {

		for (CollisionEvent ev : events) {

			EntityModel a = ev.getA();
			EntityModel b = ev.getB();

			int x = (a.getXLocation() + b.getXLocation()) / 2;
			int y = (a.getYLocation() + b.getYLocation()) / 2;

			if (a instanceof LaserBolt || b instanceof LaserBolt) {
				removeEntity(a, b);
				addSmoke(x, y);
				if (cluster.isMember(a) || cluster.isMember(b))
					cluster.highLightNearest(ship);
			}

			if (a instanceof Ship || b instanceof Ship) {
				removeEntity(a, b);
				addSmoke(x, y);
			}
			if (a instanceof SpaceRock && b instanceof SpaceRock) {
				if (cluster.isMember(a)) {
					b.setXVelocity(-b.getXVelocity());
					b.setYVelocity(-b.getYVelocity());
					cluster.setXVelocity(-cluster.getXVelocity());
					cluster.setYVelocity(-cluster.getYVelocity());
				} else if (cluster.isMember(b)) {
					a.setXVelocity(-a.getXVelocity());
					a.setYVelocity(-a.getYVelocity());
					cluster.setXVelocity(-cluster.getXVelocity());
					cluster.setYVelocity(-cluster.getYVelocity());
				} else {
					a.setXVelocity(-a.getXVelocity());
					a.setYVelocity(-a.getYVelocity());
					b.setXVelocity(-b.getXVelocity());
					b.setYVelocity(-b.getYVelocity());
				}

			}
		}
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
		if (ship.isActive())
			cluster.highLightNearest(ship);
	}

	@Override
	protected void onOutOfBounds(OutOfBoundsEvent[] events) {

		// only cluster members respond to o-o-b
		// the other rocks are set to wrap around
		// flag to indicate o-o-b on cluster member
		boolean oobDetected = false;
		
		for (OutOfBoundsEvent ev : events) {

			EntityModel em = ev.getEntity();
			Direction dir = ev.getEdge();
			if (em instanceof LaserBolt) {
				removeEntity(em);
				continue;
			} else if (!oobDetected && em instanceof SpaceRock) {
				switch (dir) {
				case North:
				case South:
						cluster.setYVelocity(-em.getYVelocity());
					break;
				case East:
				case West:
					cluster.setXVelocity(-em.getXVelocity());
					break;
				default:
				}
				oobDetected = true;
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
		SwingUtilities.invokeLater(() -> new Exercise5D());
	}

}
