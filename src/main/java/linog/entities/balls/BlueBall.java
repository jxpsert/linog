package linog.entities.balls;

import javafx.scene.paint.Color;

/**
 * A blue ball, able to contain a letter.
 */

public class BlueBall extends Ball {

	private int value = 10;
	private char letter;

	/**
	 * Constructor
	 * 
	 * @param x        The location on the x-axis
	 * @param y        The location on the y-axis
	 * @param diameter The diameter of the ball
	 * @param letter   The letter the ball contains
	 */

	public BlueBall(int x, int y, int diameter, char letter) {
		super(x, y, diameter, Color.BLUE);
		this.letter = letter;
	}

	/**
	 * Get the letter which is on the ball
	 * 
	 * @return The letter
	 */

	public char getLetter() {
		return this.letter;
	}

	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return "BlueBall []";
	}
}
