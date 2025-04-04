/**
 * 
 */
package exr3B;

import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import role.BarrierModel;
import role.EntityModel;
import role.GameController;
import role.ScreenBoundary;
import util.Physics;

/**
 * 
 * @author Krish Pillai
 *
 */
public class Exercise3B extends GameController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SoccerBall ball;
	
	public Exercise3B() {
		setTitle("Exercise 3B");
	}

	@Override
	protected void enlistEntities() {
		ball = new SoccerBall(getWidth()/2, getHeight()/8);
		ball.setActive(true);
		ball.setXVelocity(20);
		ball.setYVelocity(10);
		ball.setYAcceleration(0.8f);
		addEntity(ball);	
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {

	}


	@Override
	protected void updateGameState() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Exercise3B();
			}
		});
	}

	@Override
	protected void onMessagesPolled(MessageEvent[] events) {
		// TODO Auto-generated method stub

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
		for (OutOfBoundsEvent e : events) {
			ScreenBoundary sb = e.getBoundary();
			EntityModel em = e.getEntity();
			Physics.rebound(em, sb);
		}
	}

	@Override
	protected void onBlocked(BlockedEvent[] events) {

	}

}
