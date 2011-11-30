package it.agileday.backtobasics.taxes;
import it.agileday.backtobasics.TaxCalculator;


public class LuxuryTaxCalculator implements TaxCalculator {

	@Override
	public int calculate(int base) {
		return 75;
	}

}
