/**
 * 
 */
package exr3D;

import java.awt.Color;
import java.awt.Font;

import role.TextModel;

/**
 * @author Krish Pillai
 *
 */
public class Scoreboard extends TextModel {

	private int score = 0;
	private String message;
	
	public Scoreboard(int x, int y, int layer) {
		super(x, y, layer);
	}

	@Override
	protected void setAppearance() {
		Font font = new Font("Courier New", Font.BOLD|Font.PLAIN, 40);
		message = String.format("Score: %03d", score);
		setView(message, font, Color.YELLOW);
	}

	public void increment() {
		score++;
		message = String.format("Score: %03d", score);
	}
	
	public int getScore() {
		return score;
	}
	
	@Override
	protected void updateParameters(long elapsedTime) {
		modifyMessage(message);
	}

}
