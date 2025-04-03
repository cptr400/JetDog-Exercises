package exr6E;

import java.util.Random;

/**
 * The Soldier Alien speeds up as it moves forward. 
 * It also sends a notification to the controller requesting to fire a projectile.
 * @author Krish Pillai
 *
 */
public abstract class AlienSoldier extends Alien {

	/**
	 * Takes marching orders
	 */
	private int rank, file;

	// Higher the threshold, the more it fires
	private int firingThreshold = 5;
	private float firingDelayIncrement = 0.05f;
	private boolean frontLine = false;
	private Random rand = new Random();
	private int cost;
	
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

	/**
	 * @return the cost
	 */
	protected int getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	protected void setCost(int cost) {
		this.cost = cost;
	}
	
	@Override
	public void updateParameters(long elapsedTime) {
		if (isFrontLine())
			generateSignal();
		firingThreshold += firingDelayIncrement;
	}

	public void setFiringThreshold(int value) {
		firingThreshold = value;
	}

	public void setFiringDelayIncrement(float inc) {
		firingDelayIncrement = inc;
	}
	
	
	public void advance(Direction edge) {
		if (edge == Direction.East) {
			moveLeft();
		} else {
			moveRight();
		}
		stepForward();
		speedUp();
	}
	
	private void generateSignal() {
		int val = rand.nextInt(5000);
		if (val < firingThreshold) {
			signal();
		}		
	}
	
	protected abstract void signal();
}
