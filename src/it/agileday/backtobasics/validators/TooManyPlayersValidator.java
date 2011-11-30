package it.agileday.backtobasics.validators;
import it.agileday.backtobasics.Player;
import it.agileday.backtobasics.Validator;


public class TooManyPlayersValidator implements Validator {

	@Override
	public void validate(Player[] players) throws WrongNumberOfPlayerException {
		if(players.length > 8) {
			throw new TooManyPlayersException();
		}
	}

}
