package exr6E;

import role.SpriteModel;

public class Explosion extends SpriteModel {

	private int ttl = 7;
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
		setScale(0.15f);
		setTimeToLive(ttl);
		setActive(true);
		setGhost(true);		
	}

}
