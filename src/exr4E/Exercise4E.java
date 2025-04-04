/**
 * 
 */
package exr4E;

import java.awt.Dimension;

import javax.swing.SwingUtilities;

import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import role.BarrierModel;
import role.EntityModel;
import role.GameController;
import role.SpriteModel;

/**
 * 
 */
public class Exercise4E extends GameController {

	private static final long serialVersionUID = 1L;
	private SpriteModel alien1, alien2;

	public Exercise4E() {
		setTitle("Exercise 4E");
	}

	/**
	 * @param rate
	 */
	public Exercise4E(int rate) {
		super(rate);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param rate
	 * @param size
	 */
	public Exercise4E(int rate, Dimension size) {
		super(rate, size);
		// TODO Auto-generated constructor stub
	}

	
	private SpriteModel createAlien(String resource, int y) {
		SpriteModel alien = new SpriteModel(50, y) {
			
			@Override
			protected void updateParameters(long elapsedTime) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			protected void setAppearance() {
				setView(resource, 3);
				setXVelocity(5.0f);
				setWrappedMode(true);
			}
		};

		return alien;
	}

	
	@Override
	protected void enlistEntities() {
		
		alien1 = createAlien("alien-4.png", getHeight()/4);
		alien1.setActive(true);	


		alien2 = createAlien("alien-4.png", 3 * getHeight()/4);
		alien2.setActive(true);	
		
		BarrierModel bar = new BarrierModel(getWidth()/2, getHeight()/2, getWidth()/2, getHeight());
		bar.setVisible(true);
		addEntity(alien1, alien2, bar);
	}
	
	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onKeysPolled(int[] keyCodes) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onMessagesPolled(MessageEvent[] events) {
		for (MessageEvent me : events) {
			String msg = me.getDescription();
			EntityModel sm = me.getSender();
			if (sm.getID() == alien1.getID()) {
				alien1 = createAlien("alien-4.png", 100 + getHeight()/8);
				alien1.setXVelocity(5.0f);
				alien1.setActive(true);	
				addEntity(alien1);
			}
		}
	}

	@Override
	protected void updateGameState() {
		// TODO Auto-generated method stub

	}


	@Override
	protected void onOutOfBounds(OutOfBoundsEvent[] events) {

	}
	
	@Override
	protected void onBlocked(BlockedEvent[] events) {
		for (BlockedEvent be : events) {
			EntityModel em = be.getEntity();
			GAMELOGGER.info("Blocked: " +  em.toString());
			if (em.equals(alien2)) {
				alien2.setXVelocity(-alien2.getXVelocity());
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->new Exercise4E());
	}

}
