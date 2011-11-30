package it.agileday.backtobasics;

import it.agileday.backtobasics.locations.PropertyGroup;
import it.agileday.backtobasics.rentcalculators.RentCalculator;


public class DiceBasedRentCalculator implements RentCalculator {

	private final PropertyGroup propertyGroup;
	private final Dice dice;

	public DiceBasedRentCalculator(Dice dice, PropertyGroup propertyGroup) {
		this.dice = dice;
		this.propertyGroup = propertyGroup;
	}

	@Override
	public int calculate(Player owner) {
		int multiplicator = propertyGroup.ownedBy(owner) ? 10 : 4;
		return dice.lastRoll() * multiplicator;
	}

}
