package it.agileday.backtobasics.locations;

import it.agileday.backtobasics.Location;
import it.agileday.backtobasics.Player;
import it.agileday.backtobasics.Positions;

public class GoToJail implements Location {

	@Override
	public void enteredBy(Player player) {
	}

	@Override
	public void landedOnBy(Player player, Integer currentPosition, Positions playerPositions) {
		playerPositions.put(player, 10);
	}

}
