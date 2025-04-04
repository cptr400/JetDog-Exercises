package exr3F;

import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import event.BlockedEvent;
import event.CollisionEvent;
import event.MessageEvent;
import event.OutOfBoundsEvent;
import role.GameController;

public class Exercise3F extends GameController{

	private static final long serialVersionUID = 1L;

	private Airplane plane;
	private float velocity = 10.0f;
	
	public Exercise3F() {
		setTitle("Exercise 3F");
	}
	
	@Override
	protected void enlistEntities() {
		plane = new Airplane(getWidth()/2, getHeight()/2);
		plane.setActive(true);
		addEntity(plane);
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onKeysPolled(int[] keyCodes) {
			boolean leftKey = false, rightKey = false;
			
			for (int code : keyCodes) {
				if (code == KeyEvent.VK_LEFT) {
					plane.setFlipped(true);
					leftKey = true;
					plane.setXVelocity(-velocity);
				}
				if (code == KeyEvent.VK_RIGHT) {
					plane.setFlipped(false);
					rightKey = true;
					plane.setXVelocity(velocity);
				}
				if (code == KeyEvent.VK_ESCAPE) {
					plane.dispose();
					System.exit(0);
				}
			}
			
			if (!leftKey && !rightKey) {
				plane.setXVelocity(0);
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

	@Override
	protected void onOutOfBounds(OutOfBoundsEvent[] events) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onBlocked(BlockedEvent[] events) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->new Exercise3F());
	}
}
