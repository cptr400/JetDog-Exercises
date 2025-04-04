/**
 * 
 */
package exr3H;

import java.awt.Color;
import java.awt.Font;

import role.TextModel;

/**
 * @author Krish Pillai
 *
 */
public class Scoreboard extends TextModel {

	private int score = 0;
	private String message, format = "Catch Count: %03d";
	private boolean gameOver = false;
	
	public Scoreboard(int x, int y) {
		super(x, y, 3);
		setGhost(true);
	}

	@Override
	protected void setAppearance() {
		Font font = new Font("Courier New", Font.BOLD|Font.PLAIN, 40);
		message = String.format(format, score);
		setView(message, font, Color.YELLOW);
	}

	public void increment() {
		score++;
		message = String.format(format, score);
	}
	
	public void decrement() {
		score--;
		if (score < 0) {
			score = 0;
			gameOver = true;
		}
		message = String.format(format, score);
	}
	
	public int getScore() {
		return score;
	}
	
	@Override
	protected void updateParameters(long elapsedTime) {
		// changes made to message for each clock tick
		modifyMessage(message);
	}

	public boolean gameOver() {
		return gameOver;
	}

}
