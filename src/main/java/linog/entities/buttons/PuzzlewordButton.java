package linog.entities.buttons;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;

import javafx.scene.input.MouseButton;
import linog.LinogGame;

/**
 * A button containing the word "puzzelwoord". Makes you go to the puzzleword screen.
 */

public class PuzzlewordButton extends Button {

	/**
	 * Constructor
	 * @param x The location on the x-axis
	 * @param y The location on the y-axis
	 * @param game The game the button is in
	 */
	
	public PuzzlewordButton(int x, int y, LinogGame game) {
		super(x, y, "PUZZELWOORD", game);
		setAnchorPoint(AnchorPoint.CENTER_LEFT);
	}

	@Override
	public void onMouseButtonPressed(MouseButton arg0, Coordinate2D arg1) {
		// TODO Auto-generated method stub
		game.setActiveScene(3);
		game.getPuzzlewordScene().resetBoard();
	}

}
