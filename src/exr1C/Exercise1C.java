/**
 * 
 */
package exr1C;

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
public class Exercise1C extends GameController {

	private static final long serialVersionUID = 1L;
	private Alien alien;
	
	public Exercise1C() {
		setTitle("Exercise C");
	}
	
	@Override
	protected void enlistEntities() {
		alien = new Alien(400, 300);
		addEntity(alien);
		alien.setXVelocity(2);
		alien.setYVelocity(2);
		alien.setActive(true);
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {

	}

	@Override
	protected void updateGameState() {

	}

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(Exercise1C::new);
	}

	@Override
	protected void onKeysPolled(int[] keyCodes) {
		for (int key : keyCodes) {
			if (key == KeyEvent.VK_SPACE) {
				removeEntity(alien);
			}	
			if (key == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}	
		}
	
	}

	@Override
	protected void onOutOfBounds(OutOfBoundsEvent[] events) {
		for (OutOfBoundsEvent event : events) {
			switch(event.getEdge()) {
			case North:
			case South:
					alien.setYVelocity(-alien.getYVelocity());
					break;
			case East:
			case West:
					alien.setXVelocity(-alien.getXVelocity());
					break;
			default:
				break;
			}
		}
	}

	@Override
	protected void onBlocked(BlockedEvent[] events) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onMessagesPolled(MessageEvent[] events) {
		// TODO Auto-generated method stub
		
	}

}
