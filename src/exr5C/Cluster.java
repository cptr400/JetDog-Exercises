/**
 * 
 */
package exr5C;

import role.EntityModel;

/**
 * 
 */
public class Cluster {
	
	private int xgap = 60, ygap = 40;
	private int offset = 100;
	private int rank = 2, file = 2;
	private String name = "clusterRock";
	private SpaceRock[][] cluster = new SpaceRock[rank][file];

	/**
	 * 
	 */
	public Cluster(int x, int y) {
		for (int i = 0; i < cluster.length; i++) {
			for (int j = 0; j < cluster[i].length; j++) {
				int xoff = offset + j * xgap;
				int yoff = offset + i * ygap;
				cluster[i][j] =  new SpaceRock(xoff, yoff, name);
			}
		}
	}
	
	private SpaceRock nearest(int x, int y) {
		
		float min = Float.MAX_VALUE;
		SpaceRock nearest = null;
		for (int i = 0; i < cluster.length; i++) {
			for (int j = 0; j < cluster[i].length; j++) {
			int xr = cluster[i][j].getXLocation();
			int yr = cluster[i][j].getYLocation();

			float euclidDistance =(float) Math.sqrt((x - xr) * (x - xr) + (y - yr) * (y - yr));
			if (!cluster[i][j].isActive())
				continue;
			if (euclidDistance < min) {
				min = euclidDistance;
				nearest = cluster[i][j];
			}
			}
		}
		// nearest is null is all rocks are destroyed
		return nearest;
	}

	public SpaceRock[] getRocks() {
		SpaceRock[] arr = new SpaceRock[rank * file];
		int count = 0;
		for (int i = 0; i < cluster.length; i++) {
			for (int j = 0; j < cluster[i].length; j++) {
				arr[count++] = cluster[i][j];
			}
		}
		return arr;
	}
	
	public void setXVelocity(float xVelocity) {
		for (int i = 0; i < cluster.length; i++) {
			for (int j = 0; j < cluster[i].length; j++) {
				cluster[i][j].setXVelocity(xVelocity);
			}
		}
	}
	
	public void setYVelocity(float yVelocity) {
			for (int i = 0; i < cluster.length; i++) {
			for (int j = 0; j < cluster[i].length; j++) {
				cluster[i][j].setYVelocity(yVelocity);
			}
		}
	}
	
	public void setActive(boolean active) {
		for (int i = 0; i < cluster.length; i++) {
			for (int j = 0; j < cluster[i].length; j++) {
				cluster[i][j].setActive(active);
			}
		}
	}
	
	public void setWrappedMode(boolean active) {
		for (int i = 0; i < cluster.length; i++) {
			for (int j = 0; j < cluster[i].length; j++) {
				cluster[i][j].setWrappedMode(active);
			}
		}
	}

	public void highLightNearest(Ship ship) {
		for (int i = 0; i < cluster.length; i++) {
			for (int j = 0; j < cluster[i].length; j++) {
				cluster[i][j].setShowBounds(false);
			}
		}		
		SpaceRock rock = nearest(ship.getXLocation(), ship.getYLocation());
		// avoid hitting null if there are 
		if (rock != null)
			rock.setShowBounds(true);;
	}

	public boolean isMember(EntityModel rock) {
		return name.equals(rock.getName());
	}

	public float getXVelocity() {
		return cluster[0][0].getXVelocity();
	}
	
	public float getYVelocity() {
		return cluster[0][0].getYVelocity();
	}
}
