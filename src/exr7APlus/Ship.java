package exr7APlus;

import role.SpriteModel;

public class Ship extends SpriteModel{
	
	private static String imageFile = "ship-3.png";

	public Ship(int x, int y){
		super(x, y);
	}
	
	@Override
	public void updateParameters(long elapsedTime){

	}

	@Override
	protected void setAppearance() {
		setView(sprite_shipB,10);
	}

}
