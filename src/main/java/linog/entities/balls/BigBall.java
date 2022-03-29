package linog.entities.balls;

import javafx.scene.paint.Color;

/**
 * The big ball, able to display a blue ball's letter
 */

public class BigBall extends Ball {

	public BigBall(int x, int y) {
		super(x, y, 250, Color.WHITE);
		setMotion(0, 0); // doesnt move
		setGravityConstant(0.0);
		setFrictionConstant(0.0);
		//System.out.println("Grote bal getekend op " + "x" + x + "y" + y);
	}
	
	public void setColour(Color colour) {
		setFill(colour);
	}

}
