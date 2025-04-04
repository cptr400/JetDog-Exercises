/**
 * 
 */
package exr3G;

import java.awt.Color;
import java.awt.Font;

import role.TextModel;

/**
 * @author Krish Pillai
 *
 */
public class Scoreboard extends TextModel {

	private int score = 0;
	private String message, format =  "Score: %03d";
	
	public Scoreboard(int x, int y) {
		super(x, y, 3);
		setGhost(true);
	}

	@Override
	protected void setAppearance() {
		Font font = new Font("Courier New", Font.BOLD|Font.PLAIN, 20);
		message = String.format(format, score);
		setView(message, font, Color.YELLOW);
	}

	public void increment() {
		score++;
		message = String.format(format, score);
	}
	
	
	@Override
	protected void updateParameters(long elapsedTime) {
		// changes made to message for each clock tick
		modifyMessage(message);
	}

	public int getScore() {
		return score;
	}
}
