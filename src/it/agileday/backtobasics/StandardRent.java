package it.agileday.backtobasics;

import it.agileday.backtobasics.rentcalculators.RentCalculator;


public class StandardRent implements Rent {

	private final Bank bank;
	private RentCalculator rentCalculator;

	public StandardRent(Bank bank, RentCalculator rentCalculator) {
		this.bank = bank;
		this.rentCalculator = rentCalculator;
	}

	/* (non-Javadoc)
	 * @see Rent#pay(Player, Player)
	 */
	@Override
	public void pay(Player owner, Player player) {
		int rent = rentCalculator.calculate(owner);
		bank.decreaseMoneyTo(player, rent);
		bank.increaseMoneyTo(owner, rent);
	}

}
