package it.agileday.backtobasics.rentcalculators;
import it.agileday.backtobasics.Player;
import it.agileday.backtobasics.locations.PropertyGroup;


public class DoubleForEachPropertyRentCalculator implements RentCalculator {

	private final PropertyGroup propertyGroup;

	public DoubleForEachPropertyRentCalculator(PropertyGroup propertyGroup) {
		this.propertyGroup = propertyGroup;
	}

	@Override
	public int calculate(Player owner) {
		int numberOfPropertiesOwnedByOwner = propertyGroup.howManyPropertiesOwnedBy(owner);
		return (int) (25 * (Math.pow(2, numberOfPropertiesOwnedByOwner-1)));
	}

}
