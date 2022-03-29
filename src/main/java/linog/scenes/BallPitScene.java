package linog.scenes;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import linog.LinogGame;
import linog.Words;
import linog.entities.LinogLogo;
import linog.entities.Scoreboard;
import linog.entities.balls.Ball;
import linog.entities.balls.BigBall;
import linog.entities.balls.BlueBall;
import linog.entities.balls.GreenBall;
import linog.entities.balls.RedBall;
import linog.entities.buttons.ContinueButton;
import linog.entities.buttons.GrabBallButton;
import linog.timers.ScoreboardTimer;

import java.util.ArrayList;
import java.util.Random;

public class BallPitScene extends DynamicScene implements TimerContainer {

	private LinogGame game;
	private ArrayList<Ball> balls = new ArrayList<Ball>();
	
	private Scoreboard scoreBoard;
	private TextEntity grabbedText;
	private GrabBallButton grabBallButton;
	private ContinueButton continueButton;

	public BallPitScene(LinogGame game) {
		this.game = game;
	}

	@Override
	public void setupEntities() {
		// TODO Auto-generated method stub
		LinogLogo linogTitle = new LinogLogo((int) (getWidth() / 2), (int) (getHeight() / 5), 500, 250);
		addEntity(linogTitle);

		scoreBoard = new Scoreboard((int) ((getWidth() / 100) * 5), (int) ((getHeight() / 100) * 5));
		addEntity(scoreBoard);
		
		grabBallButton = new GrabBallButton((int) (getWidth() / 2), (int) (getHeight() / 4), game);
		addEntity(grabBallButton);
		
		grabbedText = new TextEntity(new Coordinate2D((int) (getWidth() / 4 * 3), (int) (getHeight() / 6)), "");
		grabbedText.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		grabbedText.setFill(Color.WHITE);
		grabbedText.setFont(Font.font("Roboto", FontWeight.BOLD, 30));
		addEntity(grabbedText);
		
		continueButton = new ContinueButton((int) (getWidth() / 4 * 3), (int) (getHeight() / 5 * 4), 1, game);
		continueButton.hide();
		addEntity(continueButton);
		
		createBalls(Words.getCurrentPuzzleWord());
	}

	private void createBalls(String word) {
		int x = 25;
		word = word.toUpperCase();
		Random rand = new Random();
		for (int i = 0; i < 12; i++) {
			if (balls.size() >= 18) {
			} else {
				balls.add(new BlueBall(x, rand.nextInt((int)(getHeight() / 2)), 50, word.charAt(i)));
				x += 60;
			}
		}
		for (int i = 0; i < 3; i++) {
			if (balls.size() >= 18) {
			} else {
				balls.add(new GreenBall(x, rand.nextInt((int)(getHeight() / 2)), 50));
				x += 60;
			}
		}
		for (int i = 0; i < 3; i++) {
			if (balls.size() >= 18) {
			} else {
				balls.add(new RedBall(x, rand.nextInt((int)(getHeight() / 2)), 50));
				x += 60;
			}
		}
		
		for (int i = 0; i < balls.size(); i++) {
			addEntity(balls.get(i));
//			System.out.println("Bal getekend " + i);
		}

	}

	public void grabBall() {
		Random rand = new Random();
		int index = rand.nextInt(balls.size());
		Ball ball = balls.get(index);
		char letter = ' ';
		if(ball instanceof BlueBall) {
			letter = ((BlueBall) ball).getLetter();
		}

		BigBall bigBall = new BigBall((int) (getWidth() / 4 * 3), (int) (getHeight() / 2) - 125);
		bigBall.setColour(ball.getFill());
		addEntity(bigBall);
		
		TextEntity letterText = new TextEntity(new Coordinate2D(getWidth() / 4 * 3 + 125, getHeight() / 2), Character.toString(letter));
		letterText.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		letterText.setFill(Color.WHITE);
		letterText.setFont(Font.font("Roboto", FontWeight.BOLD, 120));
		addEntity(letterText);
		
		Scoreboard.setMoney(Scoreboard.getMoney() + ball.getValue());
		
		if(!(ball instanceof GreenBall)) {
			grabBallButton.hide();
		}
		
		if(ball instanceof RedBall) {
			grabbedText.setText("JAMMER!\n + € 0");
			continueButton.show();
		} else if(ball instanceof BlueBall) {
			grabbedText.setText("MOOI, JE HEBT\n EEN LETTER!\n + € 10");
			game.getPuzzlewordScene().addFound(((BlueBall) (ball)).getLetter());
			continueButton.show();
		} else if(ball instanceof GreenBall) {
			grabbedText.setText("GROOOOOOOOEEEEEEN\n + € 50");
		}
		
		ball.remove();
		balls.remove(index);
	}

	@Override
	public void setupScene() {
		setBackgroundImage("backgrounds/linogbg.jpg");
	}

	@Override
	public void setupTimers() {
		// TODO Auto-generated method stub
		ScoreboardTimer scoreboardTimer = new ScoreboardTimer(this.scoreBoard);
		addTimer(scoreboardTimer);
	}

}
