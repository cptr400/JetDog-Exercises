/**
 * 
 */
package exr3H;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import role.SpriteModel;
import util.Physics;
import util.SoundEffects;

/**
 * 
 * @author Krish Pillai
 *
 */
public class BeachPailGame extends GameController implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Pail bucket;
	private final int finalScore = 11;
	private Random rand;
	private boolean firstChance = true;
	
	private float speed = 15.0f;
	private boolean leftEdge = false, rightEdge = false, noball = true;
	private Scoreboard scoreboard;

	public BeachPailGame() {
		setTitle("Bucket Game");
		setBackground(new Color(0x3498DB));
		addKeyListener(this);
		rand = new Random();
	}

	@Override
	protected void enlistEntities() {
		scoreboard = new Scoreboard(getWidth() / 2, 50);
		scoreboard.setActive(true);
		bucket = new Pail(getWidth() / 2, getHeight() - 40);
		bucket.setCollisionBounds(40, 30);
		// bucket.setShowBounds(true);
		bucket.setActive(true);

		addEntity(bucket, scoreboard);
		SceneryModel sm = new SceneryModel(800, 600, 0) {

			@Override
			protected void setAppearance() {
				setView("beachscene.png");
			}
		};
		addEntity(sm);
	}

	private void addBall() {
		noball = false;
		Beachball ball = null;
		if (firstChance) {
			firstChance = false;
			ball = new Beachball(getWidth()/2, 50);
			ball.setXVelocity(0);
			ball.setYVelocity(3);
			ball.setYAcceleration(0.2f);
		} else {
		ball = new Beachball(50 + rand.nextInt(500), 50);
		ball.setXVelocity(1 + rand.nextInt(100) / 10);
		ball.setYVelocity(3);
		ball.setYAcceleration(0.2f);
		}
		ball.setActive(true);

		addEntity(ball);
	}

	private void removeBall(EntityModel em) {
		noball = true;
		int x = em.getXLocation();
		int y = em.getYLocation();
		SoundEffects.BOUNCE.play();
		removeEntity(em);
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {
		for (CollisionEvent ce : events) {
			EntityModel a = ce.getA();
			EntityModel b = ce.getB();
			if (a instanceof Beachball) {
				removeBall(a);
			} else if (b instanceof Beachball) {
				removeBall(b);
			}
			scoreboard.increment();
		}
	}

	@Override
	protected void updateGameState() {

		if (scoreboard.isActive()) {
			if (noball && rand.nextInt(1000) <= 50)
				addBall();
			if (scoreboard.getScore() == finalScore) {
				bucket.setActive(false);
				scoreboard.setActive(false);
				TheEnd scr = new TheEnd(getWidth() / 2, getHeight() / 2, true);
				addEntity(scr);
			} else if (scoreboard.gameOver()) {
				bucket.setActive(false);
				scoreboard.setActive(false);
				TheEnd scr = new TheEnd(getWidth() / 2, getHeight() / 2, false);
				addEntity(scr);
			}
		}
	}

	@Override
	protected void onMessagesPolled(MessageEvent[] events) {

	}

	@Override
	protected void onKeysPolled(int[] keyCodes) {
		boolean kl = false, kr = false;
		for (int code : keyCodes) {
			switch (code) {

			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				if (leftEdge) {
					bucket.setXVelocity(0);
				} else {
					bucket.setXVelocity(-speed);
					rightEdge = false;
				}
				kl = true;
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				if (rightEdge) {
					bucket.setXVelocity(0);
				} else {
					bucket.setXVelocity(speed);
					leftEdge = false;
				}
				kr = true;
				break;
			case KeyEvent.VK_ESCAPE:
				dispose();
				System.exit(0);
			default:
				bucket.setXVelocity(0);
			}

		}
		// avoid key tapping and punch-through
		// gamer holds down left-key and taps the right key or vice-versa
		// causing punch-through
		if (kl && kr) {
			bucket.setXVelocity(0);
		}
	}

	@Override
	protected void onOutOfBounds(OutOfBoundsEvent[] events) {
		for (OutOfBoundsEvent oe : events) {
			Direction dir = oe.getBoundary().getEdge();
			EntityModel em = oe.getEntity();
			if (em instanceof Pail) {
				if (dir == Direction.East)
					rightEdge = true;

				if (dir == Direction.West)
					leftEdge = true;

			}
			if (em instanceof Beachball) {
				em.dispose();
				scoreboard.decrement();
				noball = true;
			}
		}
	}

	@Override
	protected void onBlocked(BlockedEvent[] events) {
		for (BlockedEvent e : events) {
			EntityModel a = e.getEntity();
			a.setYAcceleration(0);
			a.setYVelocity(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		int code = e.getExtendedKeyCode();
		if (code == KeyEvent.VK_ESCAPE) {
			dispose();
			System.exit(0);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new BeachPailGame());
	}
}
