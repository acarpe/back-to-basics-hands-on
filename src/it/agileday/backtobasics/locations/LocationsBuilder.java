package it.agileday.backtobasics.locations;
import it.agileday.backtobasics.Bank;
import it.agileday.backtobasics.Dice;
import it.agileday.backtobasics.DiceBasedRentCalculator;
import it.agileday.backtobasics.Location;
import it.agileday.backtobasics.StandardRent;
import it.agileday.backtobasics.rentcalculators.DoubleForEachPropertyRentCalculator;
import it.agileday.backtobasics.rentcalculators.StandardRentCalculator;
import it.agileday.backtobasics.taxes.IncomeTaxCalculator;
import it.agileday.backtobasics.taxes.LuxuryTaxCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LocationsBuilder {

	public List<Location> build(Bank bank, Dice dice) {
		PropertyGroup purple = new PropertyGroup();
		PropertyGroup railRoads = new PropertyGroup();
		PropertyGroup utilities = new PropertyGroup();
		List<Location> locations = new ArrayList<Location>();
		
		locations.add(new Go(bank));
		locations.add(new Property(bank, purple, new StandardRent(bank, new StandardRentCalculator(purple)), 60));
		locations.add(new Normal());
		locations.add(new Property(bank, purple, new StandardRent(bank, new StandardRentCalculator(purple)), 60));
		locations.add(new Tax(bank, new IncomeTaxCalculator()));
		locations.add(new Property(bank, railRoads, new StandardRent(bank, new DoubleForEachPropertyRentCalculator(railRoads)), 200));
		locations.add(new Normal());
		locations.add(new Normal());
		locations.add(new Normal());
		locations.add(new Normal());

		locations.add(new Normal());
		locations.add(new Normal());
		locations.add(new Property(bank, utilities , new StandardRent(bank, new DiceBasedRentCalculator(dice, utilities)), 150));
		locations.add(new Normal());
		locations.add(new Normal());
		locations.add(new Property(bank, railRoads, new StandardRent(bank, new DoubleForEachPropertyRentCalculator(railRoads)), 200));
		locations.add(new Normal());
		locations.add(new Normal());
		locations.add(new Normal());
		locations.add(new Normal());

		locations.add(new Normal());
		locations.add(new Normal());
		locations.add(new Normal());
		locations.add(new Normal());
		locations.add(new Normal());
		locations.add(new Property(bank, railRoads, new StandardRent(bank, new DoubleForEachPropertyRentCalculator(railRoads)), 200));
		locations.add(new Normal());
		locations.add(new Normal());
		locations.add(new Property(bank, utilities , new StandardRent(bank, new DiceBasedRentCalculator(dice, utilities)), 150));
		locations.add(new Normal());

		locations.add(new GoToJail());
		locations.add(new Normal());
		locations.add(new Normal());
		locations.add(new Normal());
		locations.add(new Normal());
		locations.add(new Property(bank, railRoads, new StandardRent(bank, new DoubleForEachPropertyRentCalculator(railRoads)), 200));
		locations.add(new Normal());
		locations.add(new Normal());
		locations.add(new Tax(bank, new LuxuryTaxCalculator()));
		locations.add(new Normal());

		return locations;
	}

}
