package linog.scenes;

import com.github.hanyaeger.api.scenes.StaticScene;

import linog.LinogGame;
import linog.entities.LinogLogo;
import linog.entities.buttons.NextButton;
import linog.entities.buttons.StartButton;

/**
 * Starting scene of the game
 */

public class StartScene extends StaticScene {

	private StartButton startButton;
	private LinogGame game;
	
	public StartScene(LinogGame game) {
		this.game = game;
	}

	/**
	 * Adds entities to the scene, like text and buttons.
	 */
	
	@Override
	public void setupEntities() {
		LinogLogo linogTitle = new LinogLogo((int)(getWidth() / 2), (int)(getHeight() / 2), 750, 375);
		addEntity(linogTitle);
		
		startButton = new StartButton(640, 480, game);
		addEntity(startButton);
		
		NextButton volgende = new NextButton(200, 500, "text",  game);
		addEntity(volgende);
	}

	/**
	 * Sets simple scene-specific stuff, like a background colour.
	 */
	
	@Override
	public void setupScene() {
		// TODO Auto-generated method stub
		setBackgroundImage("backgrounds/linogbg.jpg");
	}

}
