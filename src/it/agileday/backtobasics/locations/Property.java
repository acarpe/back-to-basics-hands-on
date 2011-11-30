package it.agileday.backtobasics.locations;
import it.agileday.backtobasics.Bank;
import it.agileday.backtobasics.Location;
import it.agileday.backtobasics.Player;
import it.agileday.backtobasics.Positions;
import it.agileday.backtobasics.Rent;


public class Property implements Location {

	private final Bank bank;
	private Player owner;
	private final PropertyGroup propertyGroup;
	private Rent rent;
	private int buyPrice;
	private final Location location;

	public Property(Bank bank, PropertyGroup propertyGroup, Rent rent, int buyPrice) {
		this(bank, propertyGroup, rent, buyPrice, new Normal());
	}

	public Property(Bank bank, PropertyGroup propertyGroup, Rent rent, int buyPrice, Location location) {
		this.bank = bank;
		this.rent = rent;
		this.propertyGroup = propertyGroup;
		this.location = location;
		this.propertyGroup.add(this);
		this.buyPrice = buyPrice;
	}

	@Override
	public void enteredBy(Player player) {
	}

	private boolean isNotOwned() {
		return this.owner == null;
	}

	public Player owner() {
		return owner;
	}

	@Override
	public void landedOnBy(Player player, Integer currentPosition, Positions playerPositions) {
		location.landedOnBy(player, currentPosition, playerPositions);
		if(isNotOwned()) {
			this.owner = player; 
			bank.decreaseMoneyTo(player, buyPrice);
			return;
		}
		rent.pay(owner, player);
	}

}
