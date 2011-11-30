package it.agileday.backtobasics;
import it.agileday.backtobasics.validators.WrongNumberOfPlayerException;

public interface Validator {

	public abstract void validate(Player[] players) throws WrongNumberOfPlayerException;

}