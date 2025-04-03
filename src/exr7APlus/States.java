package exr7APlus;

import event.State;
import exr6E.Exercise6E;
import util.SoundEffects;

/**
 * Container class for all FSM states
 */
public class States {

	public static class S0 extends State{

		private Exercise7A controller;
		
		public S0(Exercise7A controller) {
			super("S0");
			this.controller = controller;
		}

		@Override
		public void manage() {
			controller.showStartScreen();
		}
		
	}
	public static class S1 extends State{

		private Exercise7A controller;
		
		public S1(Exercise7A controller) {
			super("S1");
			this.controller = controller;
		}

		@Override
		public void manage() {
			controller.doSetup();
		}
		
	}
	
	public static class S2 extends State{

		private Exercise7A controller;

		public S2(Exercise7A controller) {
			super("S2");
			this.controller = controller;
		}

		@Override
		public void manage() {
			controller.inPlay();	
		}
		
	}
	
	public static class S3 extends State{

		private Exercise7A controller;
		
		public S3(Exercise7A controller) {
			super("S3");
			this.controller = controller;
		}

		@Override
		public void manage() {
			controller.showWinScreen();
			SoundEffects.WIN.play();
		}
		
	}
	
	public static class S4 extends State{

		private Exercise7A controller;

		public S4(Exercise7A controller) {
			super("S4");
			this.controller = controller;
		}

		@Override
		public void manage() {
			controller.showLoseScreen();
			SoundEffects.LOSE.play();
		}
		
	}
	
	public static class S5 extends State{

		private Exercise7A controller;

		public S5(Exercise7A controller) {
			super("S5");
			this.controller = controller;
		}

		@Override
		public void manage() {
			controller.showRetryScreen();						
		}
		
	}
	
	public static class S6 extends State{

		private Exercise7A controller;

		public S6(Exercise7A controller) {
			super("S6");
			this.controller = controller;
		}

		@Override
		public void manage() {
			controller.showEndScreen();						
		}
		
	}

	
}