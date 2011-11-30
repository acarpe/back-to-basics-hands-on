package it.agileday.backtobasics.rentcalculators;
import it.agileday.backtobasics.Player;
import it.agileday.backtobasics.locations.PropertyGroup;


public class StandardRentCalculator implements RentCalculator {

	private final PropertyGroup propertyGroup;

	public StandardRentCalculator(PropertyGroup propertyGroup) {
		this.propertyGroup = propertyGroup;
	}

	/* (non-Javadoc)
	 * @see RentCalculator#calculate(Player)
	 */
	@Override
	public int calculate(Player owner) {
		return 20 * (propertyGroup.ownedBy(owner) ? 2 : 1);
	}

}
