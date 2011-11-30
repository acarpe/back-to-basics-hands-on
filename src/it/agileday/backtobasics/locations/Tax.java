package it.agileday.backtobasics.locations;

import it.agileday.backtobasics.Bank;
import it.agileday.backtobasics.Location;
import it.agileday.backtobasics.Player;
import it.agileday.backtobasics.Positions;
import it.agileday.backtobasics.TaxCalculator;




public class Tax implements Location {

	private final Bank bank;
	private TaxCalculator taxCalculator;
	private final Location location;

	public Tax(Bank bank, TaxCalculator taxCalculator) {
		this(bank, taxCalculator, new Normal());
	}
	
	public Tax(Bank bank, TaxCalculator taxCalculator, Location location) {
		this.bank = bank;
		this.taxCalculator = taxCalculator;
		this.location = location;
	}

	@Override
	public void enteredBy(Player player) {
	}

	
	private int calculateTax(int base) {
		return taxCalculator.calculate(base);
	}

	@Override
	public void landedOnBy(Player player, Integer currentPosition, Positions playerPositions) {
		location.landedOnBy(player, currentPosition, playerPositions);
		int base = bank.balanceFor(player);
		bank.decreaseMoneyTo(player, calculateTax(base)); 
	}

}
