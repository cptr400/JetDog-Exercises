/**
 * 
 */
package exr3C;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import role.BarrierModel;
import role.EntityModel;
import role.GameController;
import util.Physics;

/**
 * 
 * @author Krish Pillai
 *
 */
public class Exercise3C extends GameController implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Exercise3C() {
		setTitle("Exercise 3C");
		addMouseListener(this);
	}

	@Override
	protected void enlistEntities() {

	}
	
	private void addBall(int x, int y) {
		SoccerBall ball = new SoccerBall(x, y);
		ball.setActive(true);
		ball.setXVelocity(20);
		ball.setYVelocity(10);
		ball.setYAcceleration(0.8f);
		addEntity(ball);	
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {

		for (CollisionEvent ce : events) {
			EntityModel a = ce.getA();
			EntityModel b = ce.getB();
			Physics.rebound(a, b);
		}
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
				new Exercise3C();
			}
		});
	}

	@Override
	protected void onMessagesPolled(MessageEvent[] events) {
		for (MessageEvent me : events) {
			String msg = me.getDescription();
			EntityModel em = me.getSender();
			if (msg.equalsIgnoreCase("stopped")) {
				System.out.println("The ball has stopped");
				em.dispose();
			}
		}
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
			BarrierModel sb = e.getBoundary();
			EntityModel em = e.getEntity();
			Physics.rebound(em, sb);
		}
	}

	@Override
	protected void onBlocked(BlockedEvent[] events) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		addBall(e.getX(), e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
