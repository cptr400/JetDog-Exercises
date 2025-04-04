package exr4F;

import role.SpriteModel;

public class Missile extends SpriteModel{

	private final String imageFile = "dot-6.png";
	
	public Missile(int x, int y){
		super(x, y);
	}

	@Override
	protected void setAppearance() {
		setView(imageFile, 0.15f, 3);
		//setCollisionBounds(50, 50);
	}


	@Override
	protected void updateParameters(long elapsedTime) {
		// TODO Auto-generated method stub
		
	}


}
