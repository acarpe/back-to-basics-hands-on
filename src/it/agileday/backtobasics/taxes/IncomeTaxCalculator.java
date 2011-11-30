package it.agileday.backtobasics.taxes;
import it.agileday.backtobasics.TaxCalculator;


public class IncomeTaxCalculator implements TaxCalculator {

	/* (non-Javadoc)
	 * @see TaxCalculator#calculate(int)
	 */
	@Override
	public int calculate(int base) {
		int amount = (int)(base * 0.1);
		return amount < 200 ? amount : 200;
	}

}
