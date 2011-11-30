package it.agileday.backtobasics.rentcalculators;

import it.agileday.backtobasics.Player;

public interface RentCalculator {
	public abstract int calculate(Player owner);
}