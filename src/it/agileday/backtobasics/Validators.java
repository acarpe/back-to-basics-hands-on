package it.agileday.backtobasics;


public class Validators implements Validator {

	private final Validator[] validators;

	public Validators(Validator ...validators) {
		this.validators = validators;
	}

	@Override
	public void validate(Player[] players) throws WrongNumberOfPlayerException {
		for (Validator validator : validators) {
			validator.validate(players);
		}
	}

}
