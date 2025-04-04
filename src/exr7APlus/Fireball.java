package exr7APlus;

import role.SpriteModel;

public abstract class Fireball extends SpriteModel{

	private int cost = 0;

	/**
	 * @param x
	 * @param y
	 */
	public Fireball(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param x
	 * @param y
	 * @param layer
	 */
	public Fireball(int x, int y, int layer) {
		super(x, y, layer);
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}