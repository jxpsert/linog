package linog.entities.buttons;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;

import javafx.scene.input.MouseButton;
import linog.LinogGame;
import linog.Words;

/**
 * A button containing the word "Terug". Makes you go back.
 */

public class BackButton extends Button {

	/**
	 * Constructor
	 * @param x The location on the x-axis
	 * @param y The location on the y-axis
	 * @param game The game the button is in
	 */
	
	public BackButton(int x, int y, LinogGame game) {
		super(x, y, "TERUG", game);
		setAnchorPoint(AnchorPoint.CENTER_LEFT);
	}

	public void onMouseButtonPressed(MouseButton mouse, Coordinate2D coordinate2) {
		// TODO Auto-generated method stub
		this.game.setActiveScene(1);
		game.getGameScene().setFeedback("EERSTE LETTER IS " + Character.toString(Words.getCurrentWord().charAt(0)).toUpperCase());
	}
}
