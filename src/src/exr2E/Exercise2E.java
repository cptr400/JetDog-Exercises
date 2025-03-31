/**
 * 
 */
package src.exr2E;

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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Krish Pillai
 *
 */
public class Exercise2E extends GameController implements MouseListener{

	private static final long serialVersionUID = 1L;
	private SeekingAlien alien;
	
	public Exercise2E() {
		setTitle("Exercise F");
		addMouseListener(this);
	}
	
	@Override
	protected void enlistEntities() {
		alien = new SeekingAlien(400, 300, 1);
		addEntity(alien);
		alien.setSpeed(20.0f);
	}

	@Override
	protected void onCollisionsPolled(CollisionEvent[] events) {

		for (CollisionEvent ev : events) {
				EntityModel a = ev.getA();
				EntityModel b = ev.getB();
				SoundEffects.COLLIDE.play();
				int x = 0, y = 0;

				if (a instanceof Cookie) {
					x = a.getXLocation();
					y = a.getYLocation();
					removeEntity(a);
					b.setXLocation(x);
					b.setYLocation(y);
					b.setActive(false);
				} else {
					x = b.getXLocation();
					y = b.getYLocation();
					removeEntity(b);
					a.setXLocation(x);
					a.setYLocation(y);
					a.setActive(false);
				}

				/* Add to layer 0 */
				Explosion exp = new Explosion(x, y, 0);
				exp.setGhost(true);
				exp.setActive(true);
				addEntity(exp);
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
	@Override
	protected void onMessagesPolled(MessageEvent[] events) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void updateGameState() {
		// TODO Auto-generated method stub
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
		int x = e.getX();
		int y = e.getY();
		Cookie cookie = new Cookie(x, y, 1);
		addEntity(cookie);
		alien.setDestX(x);
		alien.setDestY(y);
		alien.setActive(true);
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
		SwingUtilities.invokeLater(Exercise2E::new);
	}

}
