/**
 * 
 */
package exr1B;

import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import role.GameController;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * @author Krish Pillai
 *
 */
public class Exercise1B extends GameController {

	private static final long serialVersionUID = 1L;
	private Alien alien;
	
	public Exercise1B() {
		setTitle("Exercise B");
	}
	
	@Override
	protected void enlistEntities() {
		alien = new Alien(400, 300);
		addEntity(alien);
		alien.setActive(true);
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onMessagesPolled(MessageEvent[] events) {
		// TODO
	}

	@Override
	protected void updateGameState() {
		// TODO
	}

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(Exercise1B::new);
	}

	@Override
	protected void onKeysPolled(int[] keyCodes) {
		for (int key : keyCodes) {
			if (key == KeyEvent.VK_SPACE)
				removeEntity(alien);
			else if (key == KeyEvent.VK_ESCAPE)
				System.exit(0);	
		}
	}

	@Override
	protected void onOutOfBounds(OutOfBoundsEvent[] events) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onBlocked(BlockedEvent[] events) {
		// TODO Auto-generated method stub
		
	}

}
