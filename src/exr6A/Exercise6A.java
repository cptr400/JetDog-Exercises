/**
 * 
 */
package exr6A;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.SwingUtilities;

import common.FSM;
import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import event.State;
import role.GameController;
import role.SpriteModel;

/**
 * 
 */
public class Exercise6A extends GameController  implements KeyListener {

	private static final long serialVersionUID = 1L;
	private SpriteModel alien;

	private S0 s0;
	private S1 s1;

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
	
	/**
	 * 
	 */
	public Exercise6A() {
		setTitle("Exercise 6A");
		addKeyListener(this);
	}

	/**
	 * @param rate
	 */
	public Exercise6A(int rate) {
		super(rate);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param rate
	 * @param size
	 */
	public Exercise6A(int rate, Dimension size) {
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

		fsm = new FSM<>(new State[] { s0, s1}, 
				new String[] {"A", "I"},
				new State[][] { { s0, s1}, { s0, s1} });	
		fsm.setInitialState(s0);
		fsm.start();
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	protected void onBlocked(BlockedEvent[] events) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Exercise6A());
	}

	@Override
	protected void onKeysPolled(int[] keyCodes) {

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
	}

}
