package exr5F;

import role.SpriteModel;

public class Explosion extends SpriteModel {

	private int ttl = 10;
	private final String imageFile = "explosion-5.png";
	
	public Explosion(int x, int y) {
		super(x, y);
	}

	@Override
	public void updateParameters(long elapsedTime) {
	}


	@Override
	protected void setAppearance() {
		setView( imageFile, 3);
		setScale(0.2f);
		setTimeToLive(ttl);
		setActive(true);
		setGhost(true);		
	}

}
