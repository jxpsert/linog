package linog;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;

import linog.scenes.BallPitScene;
import linog.scenes.EndScene;
import linog.scenes.GameScene;
import linog.scenes.PuzzlewordScene;
import linog.scenes.StartScene;

/**
 * Main class to initialise game
 */

@SuppressWarnings("exports")

public class LinogGame extends YaegerGame {

	private StartScene startScene;
	private GameScene gameScene;
	private EndScene endScene;
	private BallPitScene ballPitScene;
	private PuzzlewordScene puzzlewordScene;

	/**
	 * Main method, starts everything
	 * @param args Supplied by system
	 */
	public static void main(String[] args) {
		
		Words.initWords();
		
		String fiveletter = Words.getFiveLetterWord();
		String twelveletter = Words.getTwelveLetterWord();
		
		Words.setCurrentPuzzleWord(twelveletter);
		Words.setCurrentWord(fiveletter);
		
		launch(args); // required by yaeger api, does some stuff
	}

	/**
	 * Initialises neccessary components for the game
	 */

	public void setupGame() {
		setGameTitle("LINOG");
		setSize(new Size(1280, 720));
		setBackgroundAudio("audio/bgmusic.mp3");
		setBackgroundAudioVolume(0.1);
	}

	/**
	 * Initialises scenes for the game
	 */

	public void setupScenes() {
		this.startScene = new StartScene(this);
		this.gameScene = new GameScene(this);
		this.endScene = new EndScene(this);
		this.ballPitScene = new BallPitScene(this);
		this.puzzlewordScene = new PuzzlewordScene(this);
		
		addScene(0, startScene);
		addScene(1, gameScene);
		addScene(2, ballPitScene);
		addScene(3, puzzlewordScene);
		addScene(4, endScene);
	}
	
	/**
	 * Returns the StartScene
	 * @return The StartScene
	 */
	public StartScene getStartScene() {
		return startScene;
	}

	/**
	 * Returns the GameScene
	 * @return The GameScene
	 */
	
	public GameScene getGameScene() {
		return gameScene;
	}
	
	/**
	 * Returns the EndScene
	 * @return The EndScene
	 */

	public EndScene getEndScene() {
		return endScene;
	}

	/**
	 * Returns the BallPitScene
	 * @return The BallPitScene
	 */
	
	public BallPitScene getBallPitScene() {
		return ballPitScene;
	}

	/**
	 * Returns the PuzzlewordScene
	 * @return The PuzzlewordScene
	 */
	
	public PuzzlewordScene getPuzzlewordScene() {
		return puzzlewordScene;
	}

}
