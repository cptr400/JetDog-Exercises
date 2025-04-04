package exr4A;

import role.SceneryModel;

public class MountainScape extends SceneryModel {

	private final String imageFile = "sceneryB-1.png";
	
	public MountainScape(int width, int height) {
		super(width, height);
	}

	@Override
	protected void setAppearance() {
		setView(imageFile);
		// get it to respond to updates
		setActive(true);
		//setScale(1.0f, 1.0f);
	}

}
