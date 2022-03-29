package linog.entities.balls;

import java.util.Random;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.Newtonian;
import com.github.hanyaeger.api.entities.SceneBorderTouchingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicCircleEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;

import javafx.scene.paint.Color;

/**
 * Parent class for all balls
 */

public class Ball extends DynamicCircleEntity implements Collider, Collided, Newtonian, SceneBorderTouchingWatcher {
	private int x;
	private int y;
	private int diameter;

	protected int value;

	/**
	 * Constructor
	 * 
	 * @param x        The location on the x-axis
	 * @param y        The location on the y-axis
	 * @param diameter The diameter of the ball
	 * @param colour   The colour of the ball
	 */

	public Ball(int x, int y, int diameter, Color colour) {
		super(new Coordinate2D(x, y));
		this.x = x;
		this.y = y;
		this.diameter = diameter;
		setRadius(diameter / 2);
		setFill(colour);
		
		Random rand = new Random();
		setMotion(5, rand.nextInt(180));

		setGravityConstant(1.00);
		setFrictionConstant(0.01);
		//System.out.println("x" + x + "y" + y);
	}

	@Override
	public void onCollision(Collider arg0) {
		//invertSpeedInDirection(getDirection());
		// Gebruikt om code te testen van collisions tussen ballen
	}

	@Override
	public void notifyBoundaryTouching(SceneBorder border) {
		Random rand = new Random();
		switch(border) {
		case BOTTOM:
			//invertSpeedInDirection(Direction.DOWN);
			setDirection(rand.nextInt(270 - 90) + 90);
			break;
		case LEFT:
			invertSpeedInDirection(Direction.LEFT);
			break;
		case RIGHT:
			invertSpeedInDirection(Direction.RIGHT);
		default:
			break;
		}
//		setSpeed(0);
//		System.out.println(border);
	}
	
	public int getValue() {
		return value;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDiameter() {
		return diameter;
	}
	
}
