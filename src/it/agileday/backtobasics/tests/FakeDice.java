package it.agileday.backtobasics.tests;
import it.agileday.backtobasics.Dice;


public class FakeDice implements Dice {

	private int roll;

	public void lastRoll(int roll) {
		this.roll = roll;
		
	}

	@Override
	public int lastRoll() {
		return roll;
	}

}
