package linog.scenes;

import java.util.ArrayList;
import java.util.Set;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
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
import linog.entities.buttons.BackButton;
import linog.entities.buttons.NextButton;

public class PuzzlewordScene extends DynamicScene implements KeyListener {

	private LinogGame game;
	private ArrayList<String> guessed = new ArrayList<String>();
	private ArrayList<String> word = new ArrayList<String>();
	
	private int currentLetter;
	private TextEntity[] board = new TextEntity[12];
	private boolean guessedIt = false;
	
	private TextEntity guessedLetters;
	
	public PuzzlewordScene(LinogGame game) {
		this.game = game;
		
        for (int i = 0; i < Words.getCurrentPuzzleWord().length(); i++) {
            word.add(Character.toString(Words.getCurrentPuzzleWord().charAt(i))); // Splitting the word up into letters (chars)
        }
	}
	
	@Override
	public void setupEntities() {
		LinogLogo linogTitle = new LinogLogo((int)(getWidth() / 2), (int)(getHeight() / 2), 750, 375);
		addEntity(linogTitle);
		
		TextEntity puzzleWordText = new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() / 2), "PUZZELWOORD");
		puzzleWordText.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		puzzleWordText.setFill(Color.WHITE);
		puzzleWordText.setFont(Font.font("Roboto", FontWeight.BOLD, 70));
		addEntity(puzzleWordText);
		
		BackButton backButton = new BackButton(75, 100, game);
		addEntity(backButton);
		
		guessedLetters = new TextEntity(new Coordinate2D(getWidth() / 10 * 9, getHeight() / 10), "");
		guessedLetters.setAnchorPoint(AnchorPoint.CENTER_RIGHT);
		guessedLetters.setFill(Color.RED);
		guessedLetters.setFont(Font.font("Roboto", FontWeight.BOLD, 40));
		addEntity(guessedLetters);
		
		NextButton volgende = new NextButton(200, 500, "text",  game);
		addEntity(volgende);
		
		Scoreboard scoreBoard = new Scoreboard((int)((getWidth() / 100) * 5), (int)((getHeight() / 100) * 5));
		addEntity(scoreBoard);
		
		for (int i = 0; i< board.length; i++) {

			int startX = 75;
			int gap = 6;
			int size = 93;

			int startY = 535;

			TextEntity letter = new TextEntity(new Coordinate2D(startX + size * i + (gap * i), startY), "");
			letter.setFill(Color.DIMGREY);
			letter.setAnchorPoint(AnchorPoint.CENTER_CENTER);
			letter.setFont(Font.font("Roboto", FontWeight.BOLD, 70));
			board[i] = letter;
			addEntity(letter);
		}
		
	}
	
	public void updateGuessed() {
		String text = String.join(", ", guessed);
		guessedLetters.setText(text);
	}
	
	public void checkWord(String word) {
		int amountRight = 0;
		word = word.toUpperCase();
		for (int i = 0; i < board.length; i++) {
			String letter = board[i].getText();
			if (word.contains(letter)) {
				if (Character.toString(word.charAt(i)).equals(letter)) {
					board[i].setFill(Color.RED);
					amountRight++;
				} else {
					board[i].setFill(Color.ORANGE);
				}
			} else {
				board[i].setFill(Color.BLACK);
				if(!guessed.contains(board[i].getText())) {
					guessed.add(board[i].getText());
				}
			}
		}

		if (amountRight == 12) {
			TextEntity feedback = new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() / 7 * 6), "GOED GERADEN! DRUK OP ENTER OM DOOR TE GAAN!");
			feedback.setFill(Color.WHITE);
			feedback.setAnchorPoint(AnchorPoint.CENTER_CENTER);
			feedback.setFont(Font.font("Roboto", FontWeight.BOLD, 40));
			addEntity(feedback);
			guessedIt = true;
		}
		
		updateGuessed();
	}
	
	public void submitWord(String word) {
		checkWord("zoetvloeiend");
	}

	@Override
	public void setupScene() {
		// TODO Auto-generated method stub
		setBackgroundImage("backgrounds/linog-puzzleword.png");
	}

	@Override
	public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
		if(!pressedKeys.iterator().hasNext()) return;
		KeyCode pressed = pressedKeys.iterator().next();
		char pressedLetter = pressed.getChar().charAt(0);
		
		if(Character.isLetter(pressedLetter)) {
			board[currentLetter].setText(Character.toString(pressedLetter));
			if(currentLetter < 11) {
				currentLetter++;
			}
		}
		
		switch(pressed) {
		case ENTER:
			if(!guessedIt) {
			String enteredWord = "";
			boolean boardNotFull = false;
			for(int i = 0; i < 12; i++) {
				if(board[i].getText().equals("")) {
					boardNotFull = true;
					enteredWord += board[i].getText();
				}
			}
			
			if(boardNotFull == false) {
				submitWord(enteredWord);
			}
			} else {
				game.setActiveScene(4);
			}
			
			break;
		case BACK_SPACE:
			int letterToDelete = currentLetter;
			if(letterToDelete < 0) {
				letterToDelete = 0;
			} else if(currentLetter == 11) {
				letterToDelete = currentLetter;
			}
			
			board[letterToDelete].setText("");
			if(currentLetter > 0) {
				currentLetter--;
			}
			break;
		default:
			break;
	}
	}

}
