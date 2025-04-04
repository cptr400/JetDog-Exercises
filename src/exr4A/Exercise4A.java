/**
 * 
 */
package exr4A;

import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import role.EntityModel;
import role.GameController;
import util.Physics;

/**
 * @author Krish Pillai
 *
 */
public class Exercise4A extends GameController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MountainScape scenery;
	
	public Exercise4A() {
		setTitle("Demo 6");
	}
	
	@Override
	protected void enlistEntities() {
		scenery = new MountainScape(getWidth(), getHeight());
		scenery.setXVelocity(5.0f);
		addEntity(scenery);
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {
		for (CollisionEvent e: events)
			Physics.rebound(e.getA(), e.getB());
	}


	@Override
	protected void updateGameState() {

	}


	@Override
	protected void onMessagesPolled(MessageEvent[] events) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	protected void onKeysPolled(int[] keyCodes) {
		for (int key : keyCodes) {
			if (key == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}	
		}
	}

	@Override
	protected void onOutOfBounds(OutOfBoundsEvent[] events) {

	}

	@Override
	protected void onBlocked(BlockedEvent[] events) {
		// TODO Auto-generated method stub
		
	}


	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() ->new Exercise4A());
	}

}
