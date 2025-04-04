package exr4F;

import role.SpriteModel;

public class UFO extends SpriteModel{
	
	private static String imageFile = "ufo-1.png";

	public UFO(int x, int y){
		super(x, y);
	}
	
	@Override
	public void updateParameters(long elapsedTime){

	}

	@Override
	protected void setAppearance() {
		setView(imageFile, 0.2f, 1);
		//setShowBounds(true);
	}
		
}
