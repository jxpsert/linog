package linog.entities.buttons;

import com.github.hanyaeger.api.Coordinate2D;

import javafx.scene.input.MouseButton;
import linog.LinogGame;

public class ContinueButton extends Button {
	
	private int scene;
	
	public ContinueButton(int x, int y, int scene, LinogGame game) {
		super(x, y, "GA DOOR", game);
		this.scene = scene;
	}

	@Override
	public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2d) {
		game.setActiveScene(scene);
	}

}
