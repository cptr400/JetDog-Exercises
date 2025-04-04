/**
 * 
 */
package exr5C;

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
public class Exercise5C extends GameController {

	private static final long serialVersionUID = 1L;
	private Ship ship;
	private Cluster cluster;
	private Random rand;
	

	/**
	 * 
	 */
	public Exercise5C() {
		setTitle("Exercise 5C");
		rand = new Random();
	}

	/**
	 * @param rate
	 */
	public Exercise5C(int rate) {
		super(rate);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param rate
	 * @param size
	 */
	public Exercise5C(int rate, Dimension size) {
		super(rate, size);
		// TODO Auto-generated constructor stub
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
		
		SpaceRock rock1 = new SpaceRock(getWidth()/16, getHeight()/16);
		SpaceRock rock2 = new SpaceRock(3 * getWidth()/4, 3 * getHeight()/4);
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

		for (int code : keyCodes) {
			switch (code) {
			case KeyEvent.VK_ESCAPE:
				dispose();
				System.exit(0);
				break;
			}
		}
	}

	@Override
	protected void onMessagesPolled(MessageEvent[] events) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateGameState() {
			cluster.highLightNearest(ship);
	}

	@Override
	protected void onOutOfBounds(OutOfBoundsEvent[] events) {

		boolean obDetected = false;
		for (OutOfBoundsEvent ev : events) {
			EntityModel em = ev.getEntity();
			Direction dir = ev.getEdge();
			switch (dir) {
			case North:
			case South:
				if (!obDetected && em instanceof SpaceRock) {
					cluster.setYVelocity(-em.getYVelocity());
					obDetected = true;
				}
				break;
			case East:
			case West:
				if (!obDetected && em instanceof SpaceRock) {
					cluster.setXVelocity(-em.getXVelocity());
					obDetected = true;
				}
				break;
			default:
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
		SwingUtilities.invokeLater(() -> new Exercise5C());
	}

}
