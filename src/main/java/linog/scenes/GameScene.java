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
	
	private Scoreboard scoreBoard;

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

				TextEntity letter = new TextEntity(new Coordinate2D(startX + size * j + (gap * j), startY + size * i * 1.075 + (gap * i)), "");
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

	}

	/**
	 * Write a word unto a row
	 * @param row The row to put it on
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
	}
	
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
			
			TextEntity feedback = new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() / 5),
					"GOED GERADEN! DRUK OP DE KNOP OM DOOR TE GAAN!");
			feedback.setFill(Color.WHITE);
			feedback.setAnchorPoint(AnchorPoint.CENTER_CENTER);
			feedback.setFont(Font.font("Roboto", FontWeight.BOLD, 40));
			addEntity(feedback);
			
			ContinueButton continueButton = new ContinueButton((int)(getWidth() / 5 * 4), (int)(getHeight() / 4 * 3), 2, game);
			addEntity(continueButton);
		} else {
			SoundClip dooDooDoo = new SoundClip("audio/word.mp3");
			dooDooDoo.play();
		}
		
		
	}

	@Override
	public void setupScene() {
		setBackgroundImage("backgrounds/linog-gamescene.png");
	}

	public void submitWord(String word) {
		int lastRow = 0;
		currentRow++;
		currentLetter = 0;

		if (currentRow - 1 < 0) {
			lastRow = 0;
		} else {
			lastRow = currentRow - 1;
		}

		checkWord(lastRow, Words.getCurrentWord());
	}

	@Override
	public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
		// TODO Auto-generated method stub
		if(!pressedKeys.iterator().hasNext()) return;
		KeyCode pressed = pressedKeys.iterator().next();
		char pressedLetter = pressed.getChar().charAt(0);
		
		if(Character.isLetter(pressedLetter)) {
			board[currentRow][currentLetter].setText(Character.toString(pressedLetter));
			if(currentLetter  < 4) {
				currentLetter++;
			}
		}
		
		switch(pressed) {
			case ENTER:
				if(!guessed) {
					String enteredWord = "";
					boolean boardNotFull = false;
					for(int i = 0; i < 5; i++) {
						if(board[currentRow][i].getText().equals("")) {
							boardNotFull = true;
							enteredWord += board[currentRow][i].getText();
						}
					}
					
					if(boardNotFull == false) {
						submitWord(enteredWord);
					}
				} 
				
				break;
			case BACK_SPACE:
				int letterToDelete = currentLetter;
				if(letterToDelete < 0) {
					letterToDelete = 0;
				} else if(currentLetter == 4) {
					letterToDelete = currentLetter;
				}
				
				board[currentRow][letterToDelete].setText("");
				if(currentLetter > 0) {
					currentLetter--;
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
