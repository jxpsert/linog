package linog.entities.balls;

import javafx.scene.paint.Color;

/**
 * A red ball. Exists purely for comparison reasons.
 */

public class RedBall extends Ball {
	
	private int value = 0;
	
	/**
	 * Constructor
	 * 
	 * @param x        The location on the x-axis
	 * @param y        The location on the y-axis
	 * @param diameter The diameter of the ball
	 */
	
	public RedBall(int x, int y, int diameter) {
		super(x, y, diameter, Color.RED);
	}

	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return "RedBall []";
	}
}
