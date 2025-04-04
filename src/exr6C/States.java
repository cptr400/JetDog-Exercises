package exr6C;

import event.State;
import util.SoundEffects;

/**
 * Container class for all FSM states
 */
public class States {

	public static class S0 extends State{

		private Exercise6C controller;
		
		public S0(Exercise6C controller) {
			super("S0");
			this.controller = controller;
		}

		@Override
		public void manage() {
			controller.showStartScreen();
		}
		
	}
	
	public static class S1 extends State{

		private Exercise6C controller;

		public S1(Exercise6C controller) {
			super("S1");
			this.controller = controller;
		}

		@Override
		public void manage() {
			controller.inPlay();	
		}
		
	}
	
	public static class S2 extends State{

		private Exercise6C controller;
		
		public S2(Exercise6C controller) {
			super("S2");
			this.controller = controller;
		}

		@Override
		public void manage() {
			controller.showWinScreen();
			SoundEffects.WIN.play();
		}
		
	}
	
	public static class S3 extends State{

		private Exercise6C controller;

		public S3(Exercise6C controller) {
			super("S3");
			this.controller = controller;
		}

		@Override
		public void manage() {
			controller.showLoseScreen();
			SoundEffects.LOSE.play();
		}
		
	}
	
	public static class S4 extends State{

		private Exercise6C controller;

		public S4(Exercise6C controller) {
			super("S4");
			this.controller = controller;
		}

		@Override
		public void manage() {
			controller.showRetryScreen();						
		}
		
	}
	
	public static class S5 extends State{

		private Exercise6C controller;

		public S5(Exercise6C controller) {
			super("S5");
			this.controller = controller;
		}

		@Override
		public void manage() {
			controller.showEndScreen();						
		}
		
	}
	
}