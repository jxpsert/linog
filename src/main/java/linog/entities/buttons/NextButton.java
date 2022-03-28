package linog.entities.buttons;

import com.github.hanyaeger.api.Coordinate2D;

import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import linog.LinogGame;

public class NextButton extends Button {

	private LinogGame game;
	
	public static int currentScene;
	
	public NextButton(int x, int y, String text, LinogGame game) {
		super(x, y, text, game);
		// TODO Auto-generated constructor stub
		this.game = game;
	}

	@Override
	public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2d) {
		currentScene++;
		if(currentScene > 4) {
			currentScene = 0;
		}
		
		game.setActiveScene(currentScene);

	}

}
