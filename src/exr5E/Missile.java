package exr5E;

import role.SpriteModel;

public class Missile extends SpriteModel{

	private final String imageFile = "missile-3.png";
	
	public Missile(int x, int y){
		super(x, y);
	}

	@Override
	protected void setAppearance() {
		setView(imageFile, 3);
		//setCollisionBounds(50, 50);
	}


	@Override
	protected void updateParameters(long elapsedTime) {
		// TODO Auto-generated method stub
		
	}


}
