package linog.entities.buttons;

import com.github.hanyaeger.api.Coordinate2D;

import javafx.scene.input.MouseButton;
import linog.LinogGame;
import linog.Words;

/**
 * A button containing the words "GA DOOR".
 */

public class ContinueButton extends Button {

	private int scene;

	/**
	 * Constructor
	 * @param x The location on the x-axis
	 * @param y The location on the y-axis
	 * @param scene The scene this button goes to
	 * @param game The game the button is in
	 */
	
	public ContinueButton(int x, int y, int scene, LinogGame game) {
		super(x, y, "GA DOOR", game);
		this.scene = scene;
	}

	@Override
	public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2d) {
		game.setActiveScene(scene);
		switch (scene) {
		case 0:
			break;
		case 1:
			Words.setCurrentWord(Words.getFiveLetterWord());
			game.getGameScene().resetBoard();
			break;
		}
	}

}
