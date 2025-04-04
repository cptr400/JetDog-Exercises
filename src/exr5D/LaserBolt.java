package exr5D;

import java.awt.Color;

import role.SpriteModel;

public class LaserBolt extends SpriteModel {

	public LaserBolt(int x, int y) {
		super(x, y);
	}
	
	@Override
	protected void updateParameters(long elapsedTime) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void setAppearance() {
		setView(Color.WHITE);
	}

}
