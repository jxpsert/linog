package linog.entities.buttons;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.entities.impl.RectangleEntity;
import com.github.hanyaeger.api.userinput.MouseButtonPressedListener;
import com.github.hanyaeger.api.userinput.MouseEnterListener;
import com.github.hanyaeger.api.userinput.MouseExitListener;

import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import linog.LinogGame;

/**
 * Main button class
 */

public abstract class Button extends TextEntity implements MouseButtonPressedListener, MouseExitListener, MouseEnterListener {

	protected int x, y;
	protected String text;
	protected LinogGame game;

	/**
	 * Constructor
	 * 
	 * @param x      The location on the x-axis
	 * @param y      The location on the y-axis
	 * @param text   The text of the button
	 * @param game   The game the button is in
	 */

	public Button(int x, int y, String text, LinogGame game) {
		super(new Coordinate2D(x, y));
		this.x = x;
		this.y = y;
		this.game = game;
		this.setFill(Color.WHITE);
		this.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		this.setFont(Font.font("Roboto", FontWeight.BOLD, 30));
		this.setText("[" + text + "]");
	}

	public void onMouseEntered() {
		setFill(Color.LIGHTGREY);
	}
	
	public void onMouseExited() {
		setFill(Color.WHITE);
	}
}
