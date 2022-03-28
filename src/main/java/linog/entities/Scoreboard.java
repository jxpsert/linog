package linog.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Scoreboard, containing the player's prize
 */

public class Scoreboard extends TextEntity {

	private int x, y;
	private static int money;
	
	/**
	 * Constructor
	 * 
	 * @param x The location on the x-axis
	 * @param y The location on the y-axis
	 */
	public Scoreboard(int x, int y) {
		super(new Coordinate2D(x, y), "€ 000");
		this.x = x;
		this.y = y;
		this.setFill(Color.WHITE);
		this.setFont(Font.font("Roboto", FontWeight.BOLD, 35));
	}

	/**
	 * Returns the amount of money the player has earned
	 * 
	 * @return The amount of money
	 */
	public static int getMoney() {
		return money;
	}

	/**
	 * Changes the amount the player has earne
	 * 
	 * @param money The amount
	 */
	
	public static void setMoney(int money) {
		Scoreboard.money = money;
	}
	
	/**
	 * Resets the text to represent accurate data
	 */
	
	public void resetText() {
		this.setText("€ " + Scoreboard.money);
	}

}
