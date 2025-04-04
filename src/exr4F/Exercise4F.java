/**
 * 
 */
package exr4F;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.SwingUtilities;

import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import role.BarrierModel;
import role.EntityModel;
import role.GameController;
import role.SceneryModel;
import role.ScreenBoundary;
import role.SpriteModel;
import util.SoundEffects;

/**
 * 
 */
public class Exercise4F extends GameController {

	private static final long serialVersionUID = 1L;
	private int ufoXOffset = 80;
	private boolean northEdge = false, southEdge = false;
	private UFO ufo;
	private SpriteModel alien;
	private boolean blocked = false;

	private float ufoSpeed = 10.0f;
	private int firingDelay = 300;
	private long firingInstant;
	private float missileVelocity = 15.0f;
	private float alienVelocity = 8.0f;
	private Random rand = new Random();

	/**
	 * 
	 */
	public Exercise4F() {
		setTitle("Exercise 4F");
	}

	/**
	 * @param rate
	 */
	public Exercise4F(int rate) {
		super(rate);
	}

	/**
	 * @param rate
	 * @param size
	 */
	public Exercise4F(int rate, Dimension size) {
		super(rate, size);
		// TODO Auto-generated constructor stub
	}

	private void fire() {
		if (System.currentTimeMillis() - firingInstant > firingDelay) {
			// locate missile away from the ufo to avoid collision
			Missile missile = new Missile((int) ufo.getXLocation() + 50, (int) ufo.getYLocation() - 20);
			missile.setActive(true);
			missile.setXVelocity(missileVelocity);
			firingInstant = System.currentTimeMillis();
			SoundEffects.SHOOT.play();
			addEntity(missile);
		}
	}

	private SpriteModel createAlien(String resource, int y) {
		alien = new SpriteModel(getWidth() - 50, y) {

			@Override
			protected void updateParameters(long elapsedTime) {
				// TODO Auto-generated method stub

			}

			@Override
			protected void setAppearance() {
				setView(resource, 0.5f, 7);
			}
		};
		alien.setXVelocity(-5);
		alien.setActive(true);
		return alien;
	}

	@Override
	protected void enlistEntities() {
		SceneryModel fortress = new SceneryModel(getWidth(), getHeight()) {

			@Override
			protected void setAppearance() {
				setView("fortress.png");
			}
		};

		ufo = new UFO(ufoXOffset, getHeight() / 2);
		ufo.setActive(true);
		alien = createAlien("alien-4.png", 100 + rand.nextInt(400));
		BarrierModel bar1 = new BarrierModel(215, 30, 215, 160);
		BarrierModel bar2 = new BarrierModel(215, 30 + 185, 215, 160 + 185);
		BarrierModel bar3 = new BarrierModel(215, 30 + 185 * 2, 215, 160 + 185 * 2);
//		bar1.setVisible(true);
//		bar2.setVisible(true);
//		bar3.setVisible(true);

		BarrierModel bar4 = new BarrierModel(145, 30, 145, 160);
		BarrierModel bar5 = new BarrierModel(145, 30 + 185, 145, 160 + 185);
		BarrierModel bar6 = new BarrierModel(145, 30 + 185 * 2, 145, 160 + 185 * 2);
//		bar4.setVisible(true);
//		bar5.setVisible(true);
//		bar6.setVisible(true);

		addEntity(fortress, ufo, alien, bar1, bar2, bar3, bar4, bar5, bar6);
		firingInstant = System.currentTimeMillis();
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {
		for (CollisionEvent ev : events) {
			EntityModel a = ev.getA();
			EntityModel b = ev.getB();
			if ((a.getID() == alien.getID() && b instanceof Missile)
					|| (b.getID() == alien.getID() && a instanceof Missile)) {
				SoundEffects.POP.play();
				Missile m = a instanceof Missile ? (Missile) a : (Missile) b;
				removeEntity(m);
				alien.dispose();
				int x = a.getXLocation();
				int y = a.getYLocation();
				Explosion exp = new Explosion(x, y, 0);
				exp.setGhost(true);
				exp.setScale(0.3f);
				exp.setActive(true);
				addEntity(exp);
			} else if (a instanceof UFO || b instanceof UFO) {
				removeEntity(a, b);
				SoundEffects.POP.play();
				int x = a.getXLocation();
				int y = a.getYLocation();
				Explosion exp = new Explosion(x, y, 0);
				exp.setGhost(true);
				exp.setScale(0.3f);
				exp.setActive(true);
				addEntity(exp);
			}
		}
	}

	@Override
	protected void onKeysPolled(int[] keyCodes) {
		boolean up = false, down = false;

		for (int code : keyCodes) {
			switch (code) {
			case KeyEvent.VK_UP:
				if (!northEdge) {
					ufo.setYVelocity(-ufoSpeed);
					southEdge = false;
					up = true;
				}
				break;
			case KeyEvent.VK_DOWN:
				if (!southEdge) {
					ufo.setYVelocity(ufoSpeed);
					northEdge = false;
					down = true;
				}
				break;
			case KeyEvent.VK_SPACE:
				if (ufo.isActive())
					fire();
				break;
			case KeyEvent.VK_ESCAPE:
				dispose();
				System.exit(0);
				break;
			default:
				ufo.setYVelocity(0);
			}
		}
		if (!up && !down)
			ufo.setYVelocity(0);
		if (up && down)
			ufo.setYVelocity(0);

	}

	@Override
	protected void onMessagesPolled(MessageEvent[] events) {
		for (MessageEvent me : events) {
			EntityModel sm = me.getSender();
			if (sm.getID() == alien.getID()) {
				alien = createAlien("alien-4.png", 100 + rand.nextInt(400));
				alien.setActive(true);
				addEntity(alien);
			}
		}
	}

	@Override
	protected void updateGameState() {
		if (blocked) {
			alien.setXVelocity(0);
			alien.setYVelocity(alienVelocity);
		} else {
			alien.setYVelocity(0);
			alien.setXVelocity(-alienVelocity);
		}
		blocked = false;
	}

	@Override
	protected void onOutOfBounds(OutOfBoundsEvent[] events) {
		for (OutOfBoundsEvent ev : events) {
			EntityModel em = ev.getEntity();
			ScreenBoundary sb = ev.getBoundary();

			if (em instanceof UFO) {
				if (sb.getEdge() == Direction.North)
					northEdge = true;
				if (sb.getEdge() == Direction.South)
					southEdge = true;
			}
			if (em.getID() == alien.getID()) // alien will send a "disposed" notification message to the controller
				em.dispose();
		}
	}

	@Override
	protected void onBlocked(BlockedEvent[] events) {
		for (BlockedEvent ev : events) {
			EntityModel em = ev.getEntity();
			if (em.getID() == alien.getID()) {
				blocked = true;
			}
			if (em instanceof Missile) {
				int x = em.getXLocation();
				int y = em.getYLocation();
				Explosion exp = new Explosion(x, y, 0);
				exp.setGhost(true);
				exp.setScale(0.3f);
				exp.setActive(true);
				removeEntity(em);
				addEntity(exp);
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Exercise4F());
	}

}
