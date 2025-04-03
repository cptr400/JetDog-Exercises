/**
 * 
 */
package exr1A;

import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import role.GameController;

import javax.swing.*;

/**
 * @author Krish Pillai
 *
 */
public class Exercise1A extends GameController {

	private static final long serialVersionUID = 1L;
	private Alien alien;
	public Exercise1A() {
		setTitle("Exercise A");
	}
	
	@Override
	protected void enlistEntities() {
		alien = new Alien(400, 300);
		addEntity(alien);
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {
		// TODO
	}

	@Override
	protected void onMessagesPolled(MessageEvent[] events) {
		// TODO
	}

	@Override
	protected void updateGameState() {
		// TODO
	}


	@Override
	protected void onKeysPolled(int[] keyCodes) {
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
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(Exercise1A::new);
	}

}
