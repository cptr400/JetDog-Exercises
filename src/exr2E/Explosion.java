package exr2E;

import role.SpriteModel;

public class Explosion extends SpriteModel {

	private final String imageFile = "explosion-4.png";

	public Explosion(int x, int y, int layer) {
		super(x, y, layer);
	}

	@Override
	protected void setAppearance() {
		setView(imageFile, 0.6f, 3);
		setTimeToLive(10); // ten frames to live
	}


	@Override
	protected void updateParameters(long elapsedTime) {
		// TODO Auto-generated method stub

	}


}
