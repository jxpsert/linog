package linog;

import linog.entities.balls.Ball;
import linog.entities.balls.BlueBall;
import linog.entities.balls.GreenBall;
import linog.entities.balls.RedBall;

/**
 * A ballpit containing balls
 */

public class BallPit {
	
	private Ball[] balls = new Ball[18];
	
	/**
	 * Constructor
	 * @param word The word the balls should contain
	 */
	
	public BallPit(String word) {
		
		
	}
	
	public Ball[] getBalls() {
		return balls;
	}
	
}
