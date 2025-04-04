/**
 * 
 */
package exr3E;

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
import role.ScreenBoundary;
import util.Physics;

/**
 * 
 */
public class Exercise3E extends GameController implements MouseListener{

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Exercise3E() {
		setTitle("Exercise 3E");
		addMouseListener(this);
	}

	private void addBall(int x, int y) {
		SoccerBall ball = new SoccerBall(x, y);
		ball.setActive(true);
		ball.setXVelocity(0);
		ball.setYVelocity(5);
		ball.setYAcceleration(0.8f);
		addEntity(ball);	
	}

	@Override
	protected void enlistEntities() {
		BarrierModel bar = new BarrierModel(300, 2 * getHeight()/3, 2 * getWidth()/3, getHeight()/5);
		bar.setVisible(true);
		addEntity(bar);
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {

	}

	@Override
	protected void onKeysPolled(int[] keyCodes) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onMessagesPolled(MessageEvent[] events) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateGameState() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onOutOfBounds(OutOfBoundsEvent[] events) {
		for (OutOfBoundsEvent oe : events) {
			EntityModel em = oe.getEntity();
			ScreenBoundary sb = oe.getBoundary();
			Physics.rebound(em, sb);
		}
	}

	@Override
	protected void onBlocked(BlockedEvent[] events) {
		for (BlockedEvent be : events) {
			EntityModel a = be.getEntity();
			BarrierModel bm = be.getBarrier();
			Physics.rebound(a, bm);
		}
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

	public static void main(String[] args) {
		SwingUtilities.invokeLater(()-> new Exercise3E());
	}


}
