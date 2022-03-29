package linog.entities.buttons;

import com.github.hanyaeger.api.Coordinate2D;

import javafx.scene.input.MouseButton;
import linog.LinogGame;
import linog.Words;
import linog.entities.Scoreboard;

/**
 * A button containing the word "opnieuw". Makes you restart the game.
 */

public class RetryButton extends Button {

	/**
	 * Constructor
	 * @param x The location on the x-axis
	 * @param y The location on the y-axis
	 * @param game The game the button is in
	 */
	
	public RetryButton(int x, int y, LinogGame game) {
		super(x, y, "OPNIEUW", game);
	}

	@Override
	public void onMouseButtonPressed(MouseButton arg0, Coordinate2D arg1) {
		this.game.setActiveScene(0);
		Scoreboard.setMoney(0);
		Words.setCurrentPuzzleWord(Words.getTwelveLetterWord());
		Words.setCurrentWord(Words.getFiveLetterWord());
		game.getGameScene().resetBoard();
		game.getPuzzlewordScene().resetBoard();
	}

}
