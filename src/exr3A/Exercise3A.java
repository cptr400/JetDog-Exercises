/**
 * 
 */
package exr3A;

import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import role.GameController;

/**
 * 
 * @author Krish Pillai
 *
 */
public class Exercise3A extends GameController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SoccerBall ball;
	private float speed = 10.0f;
	
	public Exercise3A() {
		setTitle("Exercise 3A");
	}

	@Override
	protected void enlistEntities() {

		ball = new SoccerBall(getWidth()/2, getHeight()/2);
		addEntity(ball);
		ball.setActive(true);
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
		SwingUtilities.invokeLater(Exercise3A::new);
	}

	@Override
	protected void onMessagesPolled(MessageEvent[] events) {
		// TODO Auto-generated method stub

	}
	

	@Override
	protected void onKeysPolled(int[] keyCodes) {

		for (int code : keyCodes) {
			switch(code) {
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				ball.setYVelocity(-speed);
				break;
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				ball.setXVelocity(-speed);
				break;
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				ball.setYVelocity(speed);
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				ball.setXVelocity(speed);
				break;
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
			default:
				ball.setXVelocity(0);
				ball.setYVelocity(0);
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

}
