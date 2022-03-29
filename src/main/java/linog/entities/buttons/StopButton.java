package linog.entities.buttons;

import com.github.hanyaeger.api.Coordinate2D;

import javafx.scene.input.MouseButton;
import linog.LinogGame;

/**
 * A button containing the word "stop". Makes the game stop.
 */

public class StopButton extends Button {

	/**
	 * Constructor
	 * 
	 * @param x      The location on the x-axis
	 * @param y      The location on the y-axis
	 * @param game   The game the button is in
	 */

	public StopButton(int x, int y, LinogGame game) {
		super(x, y, "STOP", game);
	}

	@Override
	public void onMouseButtonPressed(MouseButton arg0, Coordinate2D arg1) {
		this.game.setActiveScene(4);
	}

}
