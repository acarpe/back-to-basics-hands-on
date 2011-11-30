package it.agileday.backtobasics;


public interface Location {

	public abstract void enteredBy(Player player);

	public abstract void landedOnBy(Player player, Integer currentPosition, Positions playerPositions);

}