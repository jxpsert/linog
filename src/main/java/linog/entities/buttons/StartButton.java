package linog.entities.buttons;

import com.github.hanyaeger.api.Coordinate2D;

import javafx.scene.input.MouseButton;
import linog.LinogGame;
import linog.Words;

/**
 * A button containing the word "start". Makes you start the game.
 */

public class StartButton extends Button {

	/**
	 * Constructor
	 * @param x The location on the x-axis
	 * @param y The location on the y-axis
	 * @param game The game the button is in
	 */
	
	public StartButton(int x, int y, LinogGame game) {
		super(x, y, "START", game);
	}

	@Override
	public void onMouseButtonPressed(MouseButton arg0, Coordinate2D arg1) {
		// TODO Auto-generated method stub
		game.setActiveScene(1);
	}

}
