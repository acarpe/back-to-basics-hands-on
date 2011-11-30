package it.agileday.backtobasics.locations;
import it.agileday.backtobasics.Bank;
import it.agileday.backtobasics.Location;
import it.agileday.backtobasics.Player;
import it.agileday.backtobasics.Positions;


public class Go implements Location {

	private final Bank bank;
	private final Location location;

	public Go(Bank bank) {
		this(bank, new Normal());
	}
	
	public Go(Bank bank, Location location) {
		this.bank = bank;
		this.location = location;
	}

	@Override
	public void enteredBy(Player player) {
		bank.increaseMoneyTo(player, 200);
	}

	@Override
	public void landedOnBy(Player player, Integer currentPosition, Positions playerPositions) {
		location.landedOnBy(player, currentPosition, playerPositions);
	}

	

}
