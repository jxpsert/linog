package linog.entities.buttons;

import com.github.hanyaeger.api.Coordinate2D;

/**
 * A button containing the words "Pak een bal".
 */

import javafx.scene.input.MouseButton;
import linog.LinogGame;

public class GrabBallButton extends Button {
	
	/**
	 * Constructor
	 * @param x The location on the x-axis
	 * @param y The location on the y-axis
	 * @param game The game the button is in
	 */
	public GrabBallButton(int x, int y, LinogGame game) {
		super(x, y, "PAK EEN BAL", game);
	}

	@Override
	public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2d) {
		game.getBallPitScene().grabBall();
	}

}
