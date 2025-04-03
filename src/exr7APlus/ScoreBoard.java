package exr7APlus;

import java.awt.Color;
import java.util.ArrayList;

import role.EntityModel;
import role.GameController;
import role.TextModel;

public class ScoreBoard {

	private GameController controller;
	private TextModel scoreBoard, hiScore, userInfo;
	private int currentScore, highScore;
	private ArrayList<TextModel> components = new ArrayList<>();
	private String userName = "  ";
	
	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.currentScore = score;
	}

	/**
	 * @return the controller
	 */
	public GameController getController() {
		return controller;
	}

	/**
	 * @param controller the controller to set
	 */
	public void setController(GameController controller) {
		this.controller = controller;
	}

	public ScoreBoard(GameController controller) {
		this.controller = controller;
		addComponents();
	}

	public int getScore() {
		return currentScore;
	}
	
	public void addScore(int value) {
		currentScore += value;
		if (currentScore > highScore)
			highScore = currentScore;
	}
	
	public void resetScore() {
		currentScore = 0;
	}
	
	public String getScoreAsString() {
		return String.format("%6s: %-4d", "Score", currentScore);
	}
	
	public String getHighScoreAsString() {
		return String.format("%10s: %-4d", "High Score", highScore);
	}
	
	private void addComponents() {
		
		scoreBoard = new TextModel(70, 40, 0) {
			
			@Override
			protected void updateParameters(long elapsedTime) {
				modifyMessage(getScoreAsString());
			}

			@Override
			protected void setAppearance() {
				setView(getScoreAsString(), Color.CYAN);
			}

		};
		scoreBoard.setGhost(true);
		components.add(scoreBoard);
		
		hiScore = new TextModel(controller.getWidth() - 100, 40, 0) {
			
			@Override
			protected void updateParameters(long elapsedTime) {
				modifyMessage(getHighScoreAsString());
			}

			@Override
			protected void setAppearance() {
				setView(getHighScoreAsString(), Color.CYAN);
			}

		};
		
		hiScore.setGhost(true);
		components.add(hiScore);
		
		userInfo = new TextModel(400, 40, 0) {
			
			@Override
			protected void updateParameters(long elapsedTime) {

			}

			@Override
			protected void setAppearance() {
				if (userName != null && userName.length() != 0)
					setView(userName, Color.CYAN);
			}

		};
		userInfo.setGhost(true);
		components.add(userInfo);
	}
	
	public EntityModel[] getComponents() {
		return components.toArray(new EntityModel[0]);
	}

	public void setUserName(String name) {
		userName = name;
	}
	
	public void setActive(boolean state) {
		scoreBoard.setActive(state);
		hiScore.setActive(state);
	}

}
