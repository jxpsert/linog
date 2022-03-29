package linog.timers;

import com.github.hanyaeger.api.Timer;

import linog.entities.Scoreboard;

/**
 * Timer to update the score every x seconds
 */

public class ScoreboardTimer extends Timer {

	Scoreboard board;
	
	/**
	 * Constructor
	 * @param game The game the timer is in
	 */
	public ScoreboardTimer(Scoreboard board) {
		super(2000);
		this.board = board;
	}

	@Override
	public void onAnimationUpdate(long timestamp) {
		board.resetText();
	}

}
