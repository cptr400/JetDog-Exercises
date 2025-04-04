package exr7APlus;

import role.SpriteModel;


public class JetDogSplashScreen extends SpriteModel {
	
	private int count = 1;
	private int fadeOut = 100;
	
	public JetDogSplashScreen(int x, int y) {
		super(x, y);
	}

	@Override
	protected void setAppearance() {
		setView("jetdoglogo-7.png", 5);
		setActive(true);
		setAnimate(false);
	}

	@Override
	protected void updateParameters(long elapsedTime) {
		if (count <= fadeOut) {
			count++;
			if (count > fadeOut) {
				setTimeToLive(30);
				setAnimate(true);
			}
		} 
	}

}
