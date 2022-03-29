package linog.entities.balls;

import javafx.scene.paint.Color;

/**
 * A green ball. Exists purely for comparison reasons.
 */

public class GreenBall extends Ball {
	private int value = 50;
	
	/**
	 * Constructor
	 * 
	 * @param x        The location on the x-axis
	 * @param y        The location on the y-axis
	 * @param diameter The diameter of the ball
	 */
	
	public GreenBall(int x, int y, int diameter) {
		super(x, y, diameter, Color.LIME);
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "GreenBall []";
	}

}
