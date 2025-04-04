package exr5E;

import java.util.Random;

/**
 * The Soldier Alien speeds up as it moves forward. 
 * It also sends a notification to the controller requesting to fire a projectile.
 * @author Krish Pillai
 *
 */
public class AlienSoldier extends Alien {

	/**
	 * Rank and file needed to help identify soldier to be removed from 
	 * the platoon.
	 */
	private int rank, file;

	private boolean frontLine = false;

	private float stepVelocity = 2.0f;	
		
	private double firingThreshold = 5, firingDelayIncrement = 0.05;
	private Random rand = new Random();

	public AlienSoldier(int x, int y){
		super(x, y);
	}

	/**
	 * @return the frontLine
	 */
	public boolean isFrontLine() {
		return frontLine;
	}

	/**
	 * @param frontLine the frontLine to set
	 */
	public void setFrontLine(boolean state) {
		setShowBounds(state);
		this.frontLine = state;
	}
	
	/**
	 * @return the file
	 */
	public int getFile() {
		return file;
	}


	/**
	 * @param file the file to set
	 */
	public void setFile(int file) {
		this.file = file;
	}

	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}	
	
	@Override
	public void updateParameters(long elapsedTime) {
		if (isFrontLine())
			signal();
		firingThreshold += firingDelayIncrement;
	}

	// Soldier alien takes special commands (marching Orders)
	public void speedUp() {
		setXVelocity(getXVelocity() + stepVelocity);
	}
	
	public void moveLeft() {
		setXVelocity(-Math.abs(getXVelocity()));
	}
	
	public void moveRight() {
		setXVelocity(Math.abs(getXVelocity()));
	}
	
	public void stepForward() {
		// step forward just enough to avoid overlap (a.k.a open ranks)
		setYLocation(getYLocation()+ getHitboxHeight());
	}
	

	private void signal() {
		int val = rand.nextInt(5000);
		if (val < firingThreshold) {
			notifyController("fire");
		}			
	}
	
}
