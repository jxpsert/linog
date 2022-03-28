package linog.entities.buttons;

import com.github.hanyaeger.api.Coordinate2D;

import javafx.scene.input.MouseButton;
import linog.LinogGame;

/**
 * A button containing the word "sluit". Exits the application.
 */

public class CloseButton extends Button {

	/**
	 * Constructor
	 * @param x The location on the x-axis
	 * @param y The location on the y-axis
	 * @param game The game the button is in
	 */
	
	public CloseButton(int x, int y, LinogGame game) {
		super(x, y, "SLUIT", game);
	}

	@Override
	public void onMouseButtonPressed(MouseButton arg0, Coordinate2D arg1) {
		this.game.quit();
	}

}
