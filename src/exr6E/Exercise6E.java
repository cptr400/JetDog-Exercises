/**
 * 
 */
package exr6E;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.SwingUtilities;
import event.*;
import role.*;
import common.*;
import util.*;

/**
 * 
 */
public class Exercise6E extends GameController implements KeyListener, StateChangeListener {

	private static final long serialVersionUID = 1L;
	private Ship ship;
	private AlienArmy alienArmy;
	private TextModel message, winLoseMessage, retryMessage;
	private StartScreen startScreen;

	private final int rank = 4, file = 5; // defines rank & file

	private float armyVelocity = 8.0f;
	private boolean shipLeftEdge = false, shipRightEdge = false;

	private float shipVelocity = 15.0f;
	private float missileVelocity = -30.0f;
	private int firingDelay = 200;
	private long firingInstant;
	private ScoreBoard scoreBoard;

	private States.S0 s0;
	private States.S1 s1;
	private States.S2 s2;
	private States.S3 s3;
	private States.S4 s4;
	private States.S5 s5;
	private States.S6 s6;

	private FSM<String> fsm;

	private String currentAction = "";
	//private boolean setUpComplete = false;
	/**
	 * 
	 */
	public Exercise6E() {
		setTitle("Exercise 6E");
		addKeyListener(this);
	}

	/**
	 * @param rate
	 */
	public Exercise6E(int rate) {
		super(rate);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param rate
	 * @param size
	 */
	public Exercise6E(int rate, Dimension size) {
		super(rate, size);
		// TODO Auto-generated constructor stub
	}

	private void generateExplosion(int x, int y) {
		Explosion e = new Explosion(x, y);
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

	public void deactivateAll() {
		ship.setActive(false);
		ship.setGhost(true);
		alienArmy.setActive(false);
		scoreBoard.setActive(false);
		}

	public void activateAll() {
		ship.setActive(true);
		ship.setGhost(false);
		alienArmy.setActive(true);
		scoreBoard.setActive(true);
	}


	public void setCurrentAction(String action) {
		currentAction = action;
	}
	
	@Override
	protected void enlistEntities() {
		SceneryModel bg = new SceneryModel(getWidth(), getHeight(), 0) {

			@Override
			protected void setAppearance() {
				setView("citadels.png");
			}
		};
		addEntity(bg);
		BarrierModel barr1 = new BarrierModel(120, 480, 200, 480);
		BarrierModel barr2 = new BarrierModel(120, 520, 200, 520);

		BarrierModel barr3 = new BarrierModel(358, 480, 440, 480);
		BarrierModel barr4 = new BarrierModel(358, 520, 440, 520);

		BarrierModel barr5 = new BarrierModel(602, 480, 680, 480);
		BarrierModel barr6 = new BarrierModel(602, 520, 680, 520);

		addEntity(barr1, barr2, barr3, barr4, barr5, barr6);
		scoreBoard = new ScoreBoard(this);
		addEntity(scoreBoard.getComponents());
//		barr1.setVisible(true);
//		barr2.setVisible(true);
//		barr3.setVisible(true);
//		barr4.setVisible(true);
//		barr5.setVisible(true);
//		barr6.setVisible(true);


		// As the last step here, set up the FSM
		// The FSM requires everything for the startup screen to be available
		s0 = new States.S0(this);
		s1 = new States.S1(this);
		s2 = new States.S2(this);
		s3 = new States.S3(this);
		s4 = new States.S4(this);
		s5 = new States.S5(this);
		s6 = new States.S6(this);

		fsm = new FSM<String>(new State[] { s0, s1, s2, s3, s4, s5, s6 }, // States
				new String[] { "A", "B", "C", "D", "E", "F", "G", "H" }, // Actions
				new State[][] { // delta functions
				// A, B, C, D, E, F, G, H
				{ s1, null, null, null, null, null, null, null }, // s0
				{ null, s2, null, null, null, null, null, null }, // s1
				{ null, null, s3, s4, null, null, null, null }, // s2
				{ null, null, null, null, s5, null, null, null }, // s3
				{ null, null, null, null, null, s5, null, null }, // s4
				{ null, null, null, null, null, null, s1, s6 }, // s5
				{ null, null, null, null, null, null, null, null } // s6
			});
		fsm.setInitialState(s0);
		fsm.setFinalStates(s6);
		fsm.addStateChangeListener(this);
		fsm.start();
		firingInstant = System.currentTimeMillis();
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {
		for (CollisionEvent ce : events) {

			EntityModel a = ce.getA();
			EntityModel b = ce.getB();

			// avoid fireballs colliding
			if (a instanceof Fireball && b instanceof Fireball) {
				continue;
			}

			if (a instanceof Fireball || b instanceof Fireball) {
				if (a instanceof Missile || b instanceof Missile) {
					Fireball fb = a instanceof Fireball ? (Fireball) a : (Fireball) b;
					scoreBoard.addScore(fb.getCost());

				}
			}
			if (a instanceof AlienSoldier || b instanceof AlienSoldier) {

				// Avoid friendly fire - alien stepping into line of fire
				if (a instanceof Fireball || b instanceof Fireball)
					continue;

				AlienSoldier alien = a instanceof AlienSoldier ? (AlienSoldier) a : (AlienSoldier) b;

				alienArmy.delete(alien);
				scoreBoard.addScore(alien.getCost());

				SoundEffects.COLLIDE.play();
				if (alienArmy.isEmpty()) {
					currentAction = "C";
				}
			}

			if (a instanceof Ship || b instanceof Ship) {
				ship.setActive(false);
				generateExplosion(ship.getXLocation(), ship.getYLocation());
				currentAction = "D";
				alienArmy.setActive(false);
			}
			removeEntity(a, b);
			generateExplosion(a.getXLocation(), a.getYLocation());
		}
	}

	@Override
	protected void onKeysPolled(int[] keyCodes) {

		// Detect keys only for in-play state
		if (fsm.getCurrentState() != s2)
			return;
		// prevent tapping on left/right keys
		boolean kl = false, kr = false;

		for (int key : keyCodes) {

			switch (key) {
			case KeyEvent.VK_SPACE:
				if (ship.isActive())
					fireMissile();
				break;
			case KeyEvent.VK_LEFT:
				kl = true;
				if (ship.isActive())
					steerShip(key);
				break;
			case KeyEvent.VK_RIGHT:
				kr = true;
				if (ship.isActive())
					steerShip(key);
				break;
			case KeyEvent.VK_ESCAPE:
				dispose();
				System.exit(0);
			}

		}
		if (kl && kr)
			ship.setXVelocity(0);
		// DeMorgan's law - negation of disjunction (below) is conjunction of negations
		// (needed)
		// fewer boolean operations on the CPU
		if (!(kl || kr))
			ship.setXVelocity(0);
	}

	@Override
	protected void onMessagesPolled(MessageEvent[] events) {
		for (MessageEvent event : events) {
			EntityModel em = event.getSender();
			String message = event.getDescription();

			if (em instanceof AlienSoldier) {
				Fireball fireball = null;
				if (message.contains("fire:A")) {
					fireball = new FireballA(em.getXLocation(), em.getYLocation() + 30);
				} else if (message.contains("fire:B")) {
					fireball = new FireballB(em.getXLocation(), em.getYLocation() + 30);

				}
				addEntity(fireball);
				fireball.setActive(true);
			} else if (message.contains("disposed")) {
				switch (em.getName()) {
				case "Win":
					currentAction = "E";
					break;
				case "Lose":
					currentAction = "F";
					break;
				case "Retry":
					removeEntity(alienArmy.getAliens());
					break;
				default:
				}
			}
		}
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
		for (BlockedEvent ev : events) {
			EntityModel em = ev.getEntity();

			if (em instanceof Alien) {
				currentAction = "D";
			} else {
				generateExplosion(em.getXLocation(), em.getYLocation());
				em.dispose();
			}
		}
	}

	public void showStartScreen() {
		startScreen = new StartScreen(this);
		startScreen.addComponents();
		addEntity(startScreen.getComponents());
	}

	public void doSetup() {
		removeEntity(startScreen.getComponents());
		setupForPlay();
	}

	private void setupForPlay() {
		alienArmy = new AlienArmy(rank, file);
		addEntity(alienArmy.getAliens());
		alienArmy.setVelocity(armyVelocity);

		ship = new Ship(getWidth() / 2, getHeight() - 20);
		addEntity(ship);
		ship.setLocation(getWidth() / 2, getHeight() - 20);
	}

	public void inPlay() {
		if (retryMessage != null)
			removeEntity(retryMessage);
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
				setName("Retry");
			}
		};
		retryMessage.setGhost(true);
		
		if (alienArmy.getAliens() != null)
			removeEntity(alienArmy.getAliens());
		removeEntity(ship);
		deactivateAll();
		scoreBoard.resetScore();
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
	public void keyTyped(KeyEvent e) {
		// System.out.println(KeyEvent.getKeyText(e.getExtendedKeyCode()));
		//if (fsm.getCurrentState().equals(s1))
		//	return;
		switch (e.getExtendedKeyCode()) {

		case KeyEvent.VK_Y:
			currentAction = "G";
			break;
		case KeyEvent.VK_N:
			currentAction = "H";
			break;
		case KeyEvent.VK_ESCAPE:
			dispose();
			System.exit(0);
			break;
		case KeyEvent.VK_ENTER:
			currentAction = "A";
			break;
		default:
			currentAction = "";
			break;
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
	public void stateChanged(StateChangeEvent event) {
		State newState = event.getNewState();
		if (newState.equals(s1)) {
			fsm.transition("B");
		}		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Exercise6E());
	}



}
