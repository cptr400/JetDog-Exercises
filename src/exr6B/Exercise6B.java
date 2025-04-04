/**
 * 
 */
package exr6B;

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
import role.ScreenBoundary;
import role.SpriteModel;
import util.Physics;

/**
 * 
 */
public class Exercise6B extends GameController implements KeyListener{

	private static final long serialVersionUID = 1L;

	private SpriteModel alien;

	private S0 s0;
	private S1 s1;
	private S2 s2;

	private FSM<String> fsm;
	
	// ModelState represents the state of a specific entity
	private class S0 extends State {

		private SpriteModel model;

		public S0(SpriteModel model) {
			super("S0");
			this.model = model;
		}
		
		@Override
		public void manage() {
			model.setActive(true);
			model.setXVelocity(0);
			model.setYVelocity(0);
		}

	}

	private class S1 extends State {


		private SpriteModel model;

		public S1(SpriteModel model) {
			super("S1");
			this.model = model;
		}
		
		@Override
		public void manage() {
			model.setActive(false);
		}
	}
	
	
	private class S2 extends State {


		private SpriteModel model;
		private Random rand;
		
		public S2(SpriteModel model) {
			super("S2");
			this.model = model;
			rand = new Random();
		}
		
		@Override
		public void manage() {
			model.setActive(true);
			model.setXVelocity(5 + rand.nextInt(15));
			model.setYVelocity(5 + rand.nextInt(15));
		}
	}
	
	/**
	 * 
	 */
	public Exercise6B() {
		setTitle("Exercise 6B");
		addKeyListener(this);
	}

	/**
	 * @param rate
	 */
	public Exercise6B(int rate) {
		super(rate);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param rate
	 * @param size
	 */
	public Exercise6B(int rate, Dimension size) {
		super(rate, size);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void enlistEntities() {
		alien = new SpriteModel(getWidth() / 2, getHeight() / 2) {

			@Override
			protected void updateParameters(long elapsedTime) {
				// TODO Auto-generated method stub

			}

			@Override
			protected void setAppearance() {
				setView("alieny-4.png", 7);
			}
		};
		addEntity(alien);

		s0 = new S0(alien);
		s1 = new S1(alien);
		s2 = new S2(alien);

		fsm = new FSM<String>(new State[] { s0, s1, s2}, 
				new String[] {"A", "I", "M"},
				new State[][] { { s0, s1, s2}, { s0, s1, s2}, {s0, s1, s2} });
		fsm.setInitialState(s0);
		fsm.start();
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onKeysPolled(int[] keyCodes) {

	}

	@Override
	protected void onMessagesPolled(MessageEvent[] events) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateGameState() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onOutOfBounds(OutOfBoundsEvent[] events) {
		for (OutOfBoundsEvent ev : events) {
			EntityModel em = ev.getEntity();
			ScreenBoundary sb = ev.getBoundary();
			Physics.rebound(em, sb);
		}
	}

	@Override
	protected void onBlocked(BlockedEvent[] events) {
		// TODO Auto-generated method stub

	}


	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
			dispose();
			System.exit(0);
		}
		String action = KeyEvent.getKeyText(e.getExtendedKeyCode());
		fsm.transition(action);			
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
		SwingUtilities.invokeLater(()->new Exercise6B());
	}
}
