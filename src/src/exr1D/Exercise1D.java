/**
 * 
 */
package src.exr1D;

import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import role.GameController;
import util.SoundEffects;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * @author Krish Pillai
 *
 */
public class Exercise1D extends GameController {

	private static final long serialVersionUID = 1L;
	private Alien alien1, alien2;

	public Exercise1D() {
		setTitle("Exercise D");
	}

	private void flipVelocities(Alien... aliens) {
		for (Alien a : aliens) {
			a.setXVelocity(-a.getXVelocity());
			a.setYVelocity(-a.getYVelocity());
		}
	}

	@Override
	protected void enlistEntities() {
		alien1 = new Alien(100, 100);
		alien2 = new Alien(200, 200);
		addEntity(alien1, alien2);
		alien1.setXVelocity(3);
		alien1.setYVelocity(2);
		alien2.setXVelocity(-2);
		alien2.setYVelocity(4);
		alien1.setActive(true);
		alien2.setActive(true);
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {
		for (CollisionEvent ev : events) {
			Alien a = (Alien) ev.getA();
			Alien b = (Alien) ev.getB();
			flipVelocities(a, b);
			SoundEffects.BOUNCE.play();
		}
	}


	@Override
	protected void onMessagesPolled(MessageEvent[] events) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateGameState() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Exercise1D::new);
	}

	@Override
	protected void onKeysPolled(int[] keyCodes) {
		for (int key : keyCodes) {
			if (key == KeyEvent.VK_ESCAPE)
				System.exit(0);
		}
	}

	@Override
	protected void onOutOfBounds(OutOfBoundsEvent[] events) {
		for (OutOfBoundsEvent event : events) {
			switch (event.getEdge()) {
			case North:
			case South:
				alien1.setYVelocity(-alien1.getYVelocity());
				alien2.setYVelocity(-alien2.getYVelocity());
				break;
			case East:
			case West:
				alien1.setXVelocity(-alien1.getXVelocity());
				alien2.setXVelocity(-alien2.getXVelocity());
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

}
