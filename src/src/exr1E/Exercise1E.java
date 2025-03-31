/**
 * 
 */
package src.exr1E;

import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import role.EntityModel;
import role.GameController;
import role.ScreenBoundary;
import util.Physics;
import util.SoundEffects;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * @author Krish Pillai
 *
 */
public class Exercise1E extends GameController {

	private static final long serialVersionUID = 1L;
	private Alien alien1, alien2;
	private int velocity = 10;

	public Exercise1E() {
		setTitle("Exercise E");
	}

	private void addAliens() {
		Random rand = new Random();
		alien1 = new Alien(200 - rand.nextInt(100), 100);
		alien2 = new Alien(150 + rand.nextInt(50), 200);
		addEntity(alien1, alien2);
		alien1.setXVelocity(velocity);
		alien1.setYVelocity(velocity);
		alien2.setXVelocity(-velocity);
		alien2.setYVelocity(velocity);
		alien1.setActive(true);
		alien2.setActive(true);
	}

	@Override
	protected void enlistEntities() {
		addAliens();
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {
		for (CollisionEvent ev : events) {
			Alien a = (Alien) ev.getA();
			Alien b = (Alien) ev.getB();
			SoundEffects.COLLIDE.play();
			removeEntity(a, b);
		}
	}

	@Override
	protected void onMessagesPolled(MessageEvent[] events) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateGameState() {

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Exercise1E::new);
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
			EntityModel em = event.getEntity();
			ScreenBoundary sb = event.getBoundary();
			Physics.rebound(em, sb);
		}
	}

	@Override
	protected void onBlocked(BlockedEvent[] events) {
		// TODO Auto-generated method stub

	}

}
