package linog.timers;

import com.github.hanyaeger.api.Timer;

import linog.LinogGame;

/**
 * Timer to reset the text showing what letters have been found in the balls
 */

public class FoundLettersTimer extends Timer {

	private LinogGame game;
	
	/**
	 * Constructor
	 * @param game The game the timer is in
	 */
	public FoundLettersTimer(LinogGame game) {
		super(2000);
		this.game = game;
	}

	@Override
	public void onAnimationUpdate(long timestamp) {
		this.game.getPuzzlewordScene().updateFound();
	}

}
