package linog.scenes;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.StaticScene;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import linog.LinogGame;
import linog.entities.LinogLogo;
import linog.entities.Scoreboard;
import linog.entities.buttons.CloseButton;
import linog.entities.buttons.RetryButton;

public class EndScene extends StaticScene {

	private LinogGame game;
	
	/**
	 * Constructor
	 * @param game The game the scene is in
	 */
	
	public EndScene(LinogGame game) {
		this.game = game;
	}

	@Override
	public void setupEntities() {
		LinogLogo linogTitle = new LinogLogo((int)(getWidth() / 2), (int)(getHeight() / 2), 750, 375);
		addEntity(linogTitle);
		
		TextEntity profitText = new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() / 2), "JE HEBT € " + Scoreboard.getMoney() + " GEWONNEN!");
		profitText.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		profitText.setFill(Color.WHITE);
		profitText.setFont(Font.font("Roboto", FontWeight.BOLD, 50));
		addEntity(profitText);
		
		RetryButton retry = new RetryButton((int)(getWidth() / 2 - 50), (int)(getHeight() / 4 * 3), game);
		addEntity(retry);
		
		CloseButton close = new CloseButton((int)(getWidth() / 2 + 50), (int)(getHeight() / 4 * 3), game);
		addEntity(close);
	}

	@Override
	public void setupScene() {
		// TODO Auto-generated method stub
		setBackgroundImage("backgrounds/linogbg.jpg");
	}

}
