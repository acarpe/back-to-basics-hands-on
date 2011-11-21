package it.agileday.backtobasics;

public interface Validator {

	public abstract void validate(Player[] players) throws WrongNumberOfPlayerException;

}