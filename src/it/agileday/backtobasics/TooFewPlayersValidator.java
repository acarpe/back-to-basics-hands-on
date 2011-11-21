package it.agileday.backtobasics;
import it.agileday.backtobasics.Player;
import it.agileday.backtobasics.Validator;


public class TooFewPlayersValidator implements Validator {

	/* (non-Javadoc)
	 * @see Validator#validate(Player[])
	 */
	@Override
	public void validate(Player[] players) throws WrongNumberOfPlayerException {
		if (players.length < 2) {
			throw new TooFewPlayersExpection();
		}
	}

}
