package exr3H;

import role.SpriteModel;

public class Pail extends SpriteModel{
	
	private static String imageFile = "bucket-1.png";

	public Pail(int x, int y){
		// on layer 1
		super(x, y, 1);
	}
	
	@Override
	public void updateParameters(long elapsedTime){

	}

	@Override
	protected void setAppearance() {
		setView(imageFile, 10);
	}
		
}
