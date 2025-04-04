package exr5D;

import role.SpriteModel;

public class SpaceRock extends SpriteModel {
	
	public SpaceRock(int x, int y) {
		super(x,y);
	}
	
	public SpaceRock(int x, int y, String name) {
		super(x,y);
		setName(name);
	}
	
	
	@Override
	protected void updateParameters(long elapsedTime) {
		float rot = (float) (getRotation() + 1);
		setRotationAngle(rot % 360);
	}


	@Override
	protected void setAppearance() {
		setView("asteroidA-1.png");		
	}

	
}
