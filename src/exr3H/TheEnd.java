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
public class TheEnd extends TextModel {
	private boolean win = false;
	
	public TheEnd(int x, int y, boolean win) {
		super(x, y, 2);
		setGhost(true);
		this.win = win;
	}

	@Override
	protected void setAppearance() {
		Font font = new Font("Courier New", Font.BOLD|Font.PLAIN, 40);
		String message;
		if (win)
			message = "You have won!!!";
		else
			message = "You lost!";
		setView(message, font, Color.RED);
	}

	@Override
	protected void updateParameters(long elapsedTime) {
	}

}
