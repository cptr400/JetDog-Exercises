/**
 * 
 */
package exr3D;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import role.EntityModel;
import role.GameController;
import role.ScreenBoundary;
import util.Physics;

/**
 * 
 * @author Krish Pillai
 *
 */
public class Exercise3D extends GameController implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Scoreboard sboard;
	private boolean gameOver = false;
	
	public Exercise3D() {
		setTitle("Exercise 3D");
		addMouseListener(this);
	}

	@Override
	protected void enlistEntities() {
		sboard = new Scoreboard(getWidth()/2, 500, 2);
		sboard.setGhost(true);
		sboard.setActive(true);
		addEntity(sboard);
	}
	
	private void addBall(int x, int y) {
		if (gameOver)
			return;
		SoccerBall ball = new SoccerBall(x, y);
		ball.setActive(true);
		ball.setXVelocity(30);
		ball.setYVelocity(20);
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
		if (sboard.getScore() >= 10) {
			sboard.modifyMessage("Game Over!");
			gameOver = true;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Exercise3D();
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
			if (sb.getEdge() == Direction.South)
				sboard.increment();
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
