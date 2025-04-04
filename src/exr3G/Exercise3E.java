/**
 * 
 */
package exr3G;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.SwingUtilities;

import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import role.BarrierModel;
import role.EntityModel;
import role.GameController;
import role.SceneryModel;
import util.Physics;
import util.SoundEffects;

/**
 * 
 * @author Krish Pillai
 *
 */
public class Exercise3E extends GameController implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Alien alien;
	private Scoreboard scoreBoard;
	private Bee bee;
	private BarrierModel bar;
	private boolean gameOver = false;
	private boolean justLost = true;

	private final int finalScore = 3;

	private float speed = 20.0f;
	private boolean leftEdge = false, rightEdge = false, topEdge = false;

	public Exercise3E() {
		setTitle("Exercise 3E");
		setBackground(new Color(0x3498DB));
	}

	@Override
	protected void enlistEntities() {
		bee = new Bee(750, 550, 2);
		bee.setXVelocity(-5.0f);
		bee.setActive(true);
		bee.setWrappedMode(true);
		// bee.setShowBounds(true);
		bar = new BarrierModel(0, getHeight(), getWidth(), getHeight());
		bar.setVisible(true);
		
		scoreBoard = new Scoreboard(100, 100);
		scoreBoard.setActive(true);

		alien = new Alien(100, getHeight() / 2, 2);
		alien.setActive(true);
		//alien.setYAcceleration(2.0f);
		alien.setShowBounds(true);
		alien.setCollisionBounds(50, 120);
		addKeyListener(this);
		Landscape scene = new Landscape(getWidth(), getHeight(), 0);
		addEntity(alien, bee, bar, scoreBoard, scene);
		requestFocus();
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {
		for (CollisionEvent ce : events) {
			gameOverActions(ce.getA(), ce.getB());
		}
	}

	private void gameOverActions(EntityModel a, EntityModel b) {
		gameOver = true;
		GAMELOGGER.info("Game Over!");
		scoreBoard.setActive(false);
		bee.setWrappedMode(false);
		bee.setYAcceleration(0.1f);
		alien.setYAcceleration(0.1f);
		alien.setXVelocity(-10);
		alien.setYVelocity(-10);
		bee.setXVelocity(-8);
		bee.setYVelocity(8);
		scoreBoard.modifyMessage("Game Over!");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Exercise3E();
			}
		});
	}

	@Override
	protected void onMessagesPolled(MessageEvent[] events) {

	}

	@Override
	protected void onKeysPolled(int[] keyCodes) {
		for (int code : keyCodes) {
			switch (code) {

			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				if (!gameOver) {
					if (leftEdge) {
						alien.setXVelocity(0);
					} else {
						alien.setXVelocity(-speed);
						rightEdge = false;
					}
					alien.setFlipped(true);
				}
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				if (!gameOver) {
					if (rightEdge) {
						alien.setXVelocity(0);
					} else {
						alien.setXVelocity(speed);
						leftEdge = false;
					}
					alien.setFlipped(false);
				}
				break;
			case KeyEvent.VK_ESCAPE:
				dispose();
				System.exit(0);
			default:
				if (!gameOver)
					alien.setXVelocity(0);
			}

		}

	}

	@Override
	protected void onOutOfBounds(OutOfBoundsEvent[] events) {
		for (OutOfBoundsEvent oe : events) {
			Direction dir = oe.getBoundary().getEdge();
			EntityModel em = oe.getEntity();
			if (em instanceof Alien) {
				if (dir == Direction.East)
					rightEdge = true;

				if (dir == Direction.West) {
					leftEdge = true;
				}

			}

			if (gameOver)
				Physics.rebound(em, oe.getBoundary());
		}
	}

	@Override
	protected void onBlocked(BlockedEvent[] events) {
		System.out.println("blocked!");
		for (BlockedEvent be : events) {
			EntityModel em = be.getEntity();
			BarrierModel bm = be.getBarrier();
			System.out.println(em);
			if (gameOver)
				Physics.rebound(em, bm);
			else {
				em.setYAcceleration(0);
				em.setYVelocity(0);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		int code = e.getExtendedKeyCode();
		if (code == KeyEvent.VK_SPACE && !topEdge) {
			alien.setYVelocity(-4 * speed);
			alien.setYAcceleration(3.0f);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	protected void updateGameState() {

		if (alien.getYLocation() < 10)
			alien.setYVelocity(0);
		
		if (alien.getYLocation() < 500)
			topEdge = true;
		else
			topEdge = false;
			
		if (bee.getXLocation() < 10)
			scoreBoard.increment();
		if (scoreBoard.getScore() >= finalScore) {
			scoreBoard.modifyMessage("You won!");
			scoreBoard.setActive(false);
			bee.setLocation(100, bee.getYLocation());
			bee.setActive(false);
			alien.setActive(false);
		}
		if (gameOver) {
			bee.setRotationAngle(bee.getRotation() - 4);
			alien.setRotationAngle(alien.getRotation() - 4);

			if (justLost) {
				justLost = false;
				SoundEffects.LOSE.play();
			}
		}
	}
}
