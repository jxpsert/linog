package linog.scenes;

import java.util.Set;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.userinput.KeyListener;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import linog.LinogGame;
import linog.Words;
import linog.entities.LinogLogo;
import linog.entities.Scoreboard;
import linog.entities.buttons.ContinueButton;
import linog.entities.buttons.PuzzlewordButton;
import linog.timers.ScoreboardTimer;

public class GameScene extends DynamicScene implements KeyListener, TimerContainer {

	private LinogGame game;

	private TextEntity[][] board = new TextEntity[5][5];
	private int currentRow = 0;
	private int currentLetter = 0;
	private boolean guessed = false;
	private TextEntity feedback;

	private Scoreboard scoreBoard;

	/**
	 * Constructor
	 * 
	 * @param game The game the scene is in
	 */

	public GameScene(LinogGame game) {
		this.game = game;
	}

	@Override
	public void setupEntities() {
		// TODO Auto-generated method stub
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {

				int startX = 405;
				int gap = 6;
				int size = 93;

				int startY = 233;

				TextEntity letter = new TextEntity(
						new Coordinate2D(startX + size * j + (gap * j), startY + size * i * 1.075 + (gap * i)), "");
				letter.setFill(Color.DIMGREY);
				letter.setAnchorPoint(AnchorPoint.CENTER_CENTER);
				letter.setFont(Font.font("Roboto", FontWeight.BOLD, 70));
				board[i][j] = letter;
				addEntity(letter);
			}
		}

		LinogLogo linogTitle = new LinogLogo((int) (getWidth() / 2), (int) (getHeight() / 5), 500, 250);
		addEntity(linogTitle);

		PuzzlewordButton puzzlewordButton = new PuzzlewordButton(75, 100, game);
		addEntity(puzzlewordButton);

		scoreBoard = new Scoreboard((int) ((getWidth() / 100) * 5), (int) ((getHeight() / 100) * 5));
		addEntity(scoreBoard);

		feedback = new TextEntity(new Coordinate2D(getWidth() / 4, getHeight() / 5));
		feedback.setFill(Color.WHITE);
		feedback.setAnchorPoint(AnchorPoint.CENTER_LEFT);
		feedback.setFont(Font.font("Roboto", FontWeight.BOLD, 40));
		addEntity(feedback);

	}

	/**
	 * Write a word unto a row
	 * 
	 * @param row  The row to put it on
	 * @param word The word to put on it
	 */

	public void writeWord(int row, String word) {
		if (word.length() != 5)
			return;
		char[] characters = new char[5];
		for (int i = 0; i < 5; i++) {
			characters[i] = word.toUpperCase().charAt(i); // Splitting the word up into letters (chars)
		}

		for (int i = 0; i < board[row].length; i++) {
			board[row][i].setText(Character.toString(characters[i]));
		}
	}

	/**
	 * Resets the board position to restart a guess
	 */

	public void resetBoard() {
		currentLetter = 0;
		currentRow = 0;
		feedback.setText("");
	}

	public void setFeedback(String text) {
		feedback.setText(text);
	}

	/**
	 * Matches the word on a specific row against the given word
	 * 
	 * @param row  The row the word is on
	 * @param word The word to match against
	 */

	public void checkWord(int row, String word) {
		int amountRight = 0;
		word = word.toUpperCase();
		for (int i = 0; i < board[row].length; i++) {
			String letter = board[row][i].getText();
			if (word.contains(letter)) {
				if (Character.toString(word.charAt(i)).equals(letter)) {
					board[row][i].setFill(Color.RED);
					amountRight++;
				} else {
					board[row][i].setFill(Color.ORANGE);
				}
			} else {
				board[row][i].setFill(Color.BLACK);
			}
		}

		if (amountRight == 5) {

			SoundClip dooDooDoo = new SoundClip("audio/good.mp3");
			dooDooDoo.play();

			feedback.setText("GOED GERADEN!");

			ContinueButton continueButton = new ContinueButton((int) (getWidth() / 5 * 4), (int) (getHeight() / 5 * 3),
					2, game);
			addEntity(continueButton);
		} else {
			SoundClip dooDooDoo = new SoundClip("audio/word.mp3");
			dooDooDoo.play();
		}

		if (amountRight < 5 && currentRow == 4) {
			TextEntity feedback = new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() / 5),
					"HELAAS, NIET GERADEN. HET WOORD WAS: " + Words.getCurrentWord().toUpperCase());
			feedback.setFill(Color.WHITE);
			feedback.setAnchorPoint(AnchorPoint.CENTER_CENTER);
			feedback.setFont(Font.font("Roboto", FontWeight.BOLD, 40));
			addEntity(feedback);

			ContinueButton continueButton = new ContinueButton((int) (getWidth() / 5 * 4), (int) (getHeight() / 5 * 3),
					4, game);
			addEntity(continueButton);
		}

	}

	@Override
	public void setupScene() {
		setBackgroundImage("backgrounds/linog-gamescene.png");
	}

	/**
	 * Submits the word. Adds 1 to currentRow and calls checkWord.
	 * @param word The word that has been input by the player
	 */
	
	public void submitWord(String word) {
		int lastRow = 0;
		if (currentRow < 0) {
			lastRow = 0;
		} else {
			lastRow = currentRow;
		}
		checkWord(lastRow, Words.getCurrentWord());
		currentRow++;
		if (currentRow > 4)
			currentRow = 4;
		currentLetter = 0;
	}

	@Override
	public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
		if (!pressedKeys.iterator().hasNext())
			return;
		KeyCode pressed = pressedKeys.iterator().next();
		char pressedLetter = pressed.getChar().charAt(0);

		if (Character.isLetter(pressedLetter)) {
			board[currentRow][currentLetter].setText(Character.toString(pressedLetter));
			if (currentLetter < 4) {
				currentLetter++;
				board[currentRow][currentLetter].setText("-");
			}
		}

		switch (pressed) {
		case ENTER:
			if (!guessed) {
				String enteredWord = "";
				boolean boardNotFull = false;
				for (int i = 0; i < 5; i++) {
					if (board[currentRow][i].getText().equals("")) {
						boardNotFull = true;
						enteredWord += board[currentRow][i].getText();
					}
				}

				if (boardNotFull == false) {
					submitWord(enteredWord);
				}
			}

			break;
		case BACK_SPACE:
			if (currentLetter > 0) {
				currentLetter--;
			}
			int letterToDelete = currentLetter;
			if (letterToDelete < 0) {
				letterToDelete = 0;
			}

			if (letterToDelete != 0) {
				board[currentRow][letterToDelete].setText("-");
			} else if(letterToDelete == 0) {
				board[currentRow][letterToDelete].setText("-");
			}
			
			if(letterToDelete != 4) {
				board[currentRow][letterToDelete + 1].setText("");
			}


			break;
		default:
			break;
		}
	}

	@Override
	public void setupTimers() {
		ScoreboardTimer scoreboardTimer = new ScoreboardTimer(this.scoreBoard);
		addTimer(scoreboardTimer);
	}

}
