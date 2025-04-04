/**
 * 
 */
package exr6C;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.SwingUtilities;

import common.FSM;
import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import event.State;
import role.EntityModel;
import role.GameController;
import role.TextModel;
import util.Physics;
import util.SoundEffects;

/**
 * 
 */
public class Exercise6C extends GameController implements KeyListener {

	private static final long serialVersionUID = 1L;
	private Paddle paddle;
	private float paddleVelocity = 40.0f;
	private float ballYVelocity = 20.0f;
	private boolean paddleLeftEdge = false, paddleRightEdge = false;

	private int xgap = 80, ygap = 40, xoffset = 40, yoffset = 120;
	private int rows = 3, cols = 10;
	private GlassBrick[][] bricks;
	private Ball ball;
	private int count;
	private Random rand;
	private TextModel message, winLoseMessage, retryMessage;

	private States.S0 s0;
	private States.S1 s1;
	private States.S2 s2;
	private States.S3 s3;
	private States.S4 s4;
	private States.S5 s5;

	private FSM<String> fsm;

	private String currentAction = "";

	/**
	 * 
	 */
	public Exercise6C() {
		setTitle("Exercise 6C");
		rand = new Random();
		addKeyListener(this);
	}

	/**
	 * @param rate
	 */
	public Exercise6C(int rate) {
		super(rate);
		setTitle("Exercise 6C");
		rand = new Random();
		addKeyListener(this);
	}

	/**
	 * @param rate
	 * @param size
	 */
	public Exercise6C(int rate, Dimension size) {
		super(rate, size);
		// TODO Auto-generated constructor stub
	}

	private void setupBricks() {
		bricks = new GlassBrick[rows][cols];
		for (int i = 0; i < bricks.length; i++) {
			for (int j = 0; j < bricks[i].length; j++) {
				int xoff = xoffset + j * xgap;
				int yoff = yoffset + i * ygap;
				bricks[i][j] = new GlassBrick(xoff, yoff);
				addEntity(bricks[i][j]);
				count++;
			}
		}
	}

	public void showStartScreen() {
		if (retryMessage != null)
			removeEntity(retryMessage);
		message = new TextModel(getWidth() / 2, getHeight() / 2) {

			@Override
			protected void updateParameters(long elapsedTime) {
				// TODO Auto-generated method stub

			}

			@Override
			protected void setAppearance() {
				setView("Press any key to start!", Color.GREEN);
			}
		};
		
		setupBricks();
		paddle.setLocation(getWidth() / 2, getHeight() - 50);
		addEntity(message);
	}

	public void deactivateAll() {
		paddle.setActive(false);
		ball.setActive(false);
	}

	public void activateAll() {
		paddle.setActive(true);
		ball.setActive(true);
	}

	public void inPlay() {
		removeEntity(message);
		ball = new Ball(getWidth() / 2, 200);
		ball.setXVelocity(5 + rand.nextInt(5));
		ball.setYVelocity(ballYVelocity);
		addEntity(ball);
		activateAll();
	}

	public void showWinScreen() {
		winLoseMessage = new TextModel(getWidth() / 2, getHeight() / 2) {

			@Override
			protected void updateParameters(long elapsedTime) {
				// TODO Auto-generated method stub

			}

			@Override
			protected void setAppearance() {
				setView("You Won!", Color.GREEN);
				setName("Win");
			}
		};
		deactivateAll();
		winLoseMessage.setTimeToLive(100);
		winLoseMessage.setActive(true);
		addEntity(winLoseMessage);
	}

	public void showLoseScreen() {
		winLoseMessage = new TextModel(getWidth() / 2, getHeight() / 2) {

			@Override
			protected void updateParameters(long elapsedTime) {
				// TODO Auto-generated method stub

			}

			@Override
			protected void setAppearance() {
				setView("You Lost!", Color.GREEN);
				setName("Lose");
			}
		};
		deactivateAll();
		winLoseMessage.setTimeToLive(100);
		winLoseMessage.setActive(true);
		addEntity(winLoseMessage);
	}

	public void showRetryScreen() {
		retryMessage = new TextModel(getWidth() / 2, getHeight() / 2) {

			@Override
			protected void updateParameters(long elapsedTime) {
				// TODO Auto-generated method stub

			}

			@Override
			protected void setAppearance() {
				setView("Would you like to retry? (Y/N)", Color.GREEN);
			}
		};
		deactivateAll();
		addEntity(retryMessage);
	}

	public void showEndScreen() {
		if (retryMessage != null)
			removeEntity(retryMessage);
		message = new TextModel(getWidth() / 2, getHeight() / 2) {

			@Override
			protected void updateParameters(long elapsedTime) {
				// TODO Auto-generated method stub

			}

			@Override
			protected void setAppearance() {
				setView("Game Over! Press Escape to Exit.", Color.GREEN);
			}
		};
		deactivateAll();
		addEntity(message);
	}

	@Override
	protected void enlistEntities() {
		paddle = new Paddle(0,0);
		addEntity(paddle);
		
		// As the last step here, set up the FSM
		// The FSM requires everything for the startup screen to be available
		s0 = new States.S0(this);
		s1 = new States.S1(this);
		s2 = new States.S2(this);
		s3 = new States.S3(this);
		s4 = new States.S4(this);
		s5 = new States.S5(this);

		fsm = new FSM<String>(new State[] { s0, s1, s2, s3, s4, s5 }, // States
				new String[] { "A", "B", "C", "D", "E", "F", "G" }, // Actions
				new State[][] { // delta functions
						{ s1, s0, s0, s0, s0, s0, s0 }, // s0
						{ s1, s2, s3, s1, s1, s1, s1 }, // s1
						{ s2, s2, s2, s4, s2, s2, s2 }, // s2
						{ s3, s3, s3, s3, s4, s3, s3 }, // s3
						{ s4, s4, s4, s4, s4, s0, s5 }, // s4
						{ s5, s5, s5, s5, s5, s5, s5 }  // s5
				});
		fsm.setInitialState(s0);
		// final state
		fsm.setFinalStates(s5);
		fsm.start();
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {

		for (CollisionEvent ev : events) {
			EntityModel a = ev.getA();
			EntityModel b = ev.getB();
			GlassBrick brick = null;
			Ball ball = null;

			if (a instanceof Ball || b instanceof Ball) {
				ball = a instanceof Ball ? (Ball) a : (Ball) b;
				if (a instanceof Paddle || b instanceof Paddle) {
					ball.setXVelocity(-ball.getXVelocity() + paddle.getXVelocity()/2);
					ball.setYVelocity(-ball.getYVelocity());
				} else if (a instanceof GlassBrick || b instanceof GlassBrick) {
					brick = a instanceof GlassBrick ? (GlassBrick) a : (GlassBrick) b;
					ball.setYVelocity(-ball.getYVelocity());
					removeEntity(brick);
					count--;
					SoundEffects.SMASH_GLASS.play();
					if (count == 0) {
						currentAction = "B";
					}
				}
			}
		}
	}

	@Override
	protected void onKeysPolled(int[] keyCodes) {
		
		// prevent tapping on left/right keys
		
		boolean kl = false, kr = false;
		for (int key : keyCodes) {

			if (!paddleRightEdge && (key == KeyEvent.VK_RIGHT)) {
				paddle.setXVelocity(paddleVelocity);
				paddleLeftEdge = false;
				kr = true;
			} else if (!paddleLeftEdge && (key == KeyEvent.VK_LEFT)) {
				paddle.setXVelocity(-paddleVelocity);
				paddleRightEdge = false;
				kl = true;
			}
		}

		// DeMorgan's law - negation of disjunction (below) is conjunction of negations
		// (needed)
		// fewer boolean operations on the CPU
		if (!(kl || kr))
			paddle.setXVelocity(0);
		if (kl && kr)
			paddle.setXVelocity(0);
	}

	@Override
	protected void onMessagesPolled(MessageEvent[] events) {
		for (MessageEvent ev : events) {
			String descr = ev.getDescription();
			EntityModel em = ev.getSender();
			if (em.equals(winLoseMessage) && descr.contains("disposed")) {
				if (em.getName().equals("Win"))	
					currentAction = "D";
				else 
					currentAction = "E";
			}
		}
	}

	@Override
	protected void onOutOfBounds(OutOfBoundsEvent[] events) {
		for (OutOfBoundsEvent event : events) {
			EntityModel em = event.getEntity();
			Direction edge = event.getEdge();

			if (em instanceof Paddle) {
				if (edge == Direction.East)
					paddleRightEdge = true;
				if (edge == Direction.West)
					paddleLeftEdge = true;
			}

			if (em instanceof Ball) {
				if (edge == Direction.South) {
					em.dispose();
					currentAction = "C";
				} else if (edge == Direction.North) {
					em.setYVelocity(ballYVelocity);
				} else 
					Physics.rebound(em, event.getBoundary());
			}

		}
	}

	@Override
	protected void onBlocked(BlockedEvent[] events) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateGameState() {
		// check if empty string
		if (currentAction.length() != 0) {
			fsm.transition(currentAction);
		}
		currentAction = "";
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		switch(e.getExtendedKeyCode()) {

		case KeyEvent.VK_Y:
			currentAction = "F";
			break;
		case KeyEvent.VK_N:
			currentAction = "G";
			break;
		case KeyEvent.VK_ESCAPE:
			dispose();
			System.exit(0);
			break;
		default:
			currentAction = "A";
			break;		
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
		SwingUtilities.invokeLater(() -> new Exercise6C());
	}

}
