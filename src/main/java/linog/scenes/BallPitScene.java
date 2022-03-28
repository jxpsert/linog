package linog.scenes;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import linog.LinogGame;
import linog.entities.LinogLogo;
import linog.entities.Scoreboard;
import linog.entities.balls.Ball;
import linog.entities.balls.BigBall;
import linog.entities.balls.BlueBall;
import linog.entities.balls.GreenBall;
import linog.entities.balls.RedBall;

import linog.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BallPitScene extends DynamicScene {

	private LinogGame game;
	private ArrayList<Ball> balls = new ArrayList<Ball>();

	public BallPitScene(LinogGame game) {
		this.game = game;
	}

	@Override
	public void setupEntities() {
		// TODO Auto-generated method stub
		LinogLogo linogTitle = new LinogLogo((int) (getWidth() / 2), (int) (getHeight() / 5), 500, 250);
		addEntity(linogTitle);

		Scoreboard scoreBoard = new Scoreboard((int) ((getWidth() / 100) * 5), (int) ((getHeight() / 100) * 5));
		addEntity(scoreBoard);

		createBalls("olijfkleurig");

		Util.setTimeout(() -> {
			grabBall();
		}, 10000);
	}

	private void createBalls(String word) {
		int x = 25;
		word = word.toUpperCase();
		Random rand = new Random();
		for (int i = 0; i < 12; i++) {
			if (balls.size() >= 18) {
			} else {
				balls.add(new BlueBall(x, rand.nextInt((int)(getHeight() / 2)), 50, word.charAt(i)));
				x += 40;
			}
		}
		for (int i = 0; i < 3; i++) {
			if (balls.size() >= 18) {
			} else {
				balls.add(new GreenBall(x, rand.nextInt((int)(getHeight() / 2)), 50));
				x += 40;
			}
		}
		for (int i = 0; i < 3; i++) {
			if (balls.size() >= 18) {
			} else {
				balls.add(new RedBall(x, rand.nextInt((int)(getHeight() / 2)), 50));
				x += 40;
			}
		}
		System.out.println(balls.toString());
		Collections.shuffle(balls);
		System.out.println(balls.toString());
		
		for (int i = 0; i < balls.size(); i++) {
			addEntity(balls.get(i));
			System.out.println("Bal getekend " + i);
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
		ball.remove();
		balls.remove(index);

		BigBall bigBall = new BigBall((int) (getWidth() / 4 * 3), (int) (getHeight() / 4));
		bigBall.setColour(ball.getFill());
		addEntity(bigBall);
		
		TextEntity letterText = new TextEntity(new Coordinate2D(getWidth() / 4 * 3 + 125, getHeight() / 4 + 125), Character.toString(letter));
		letterText.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		letterText.setFill(Color.WHITE);
		letterText.setFont(Font.font("Roboto", FontWeight.BOLD, 70));
		addEntity(letterText);
	}

	@Override
	public void setupScene() {
		// TODO Auto-generated method stub
		setBackgroundImage("backgrounds/linogbg.jpg");
	}

}
