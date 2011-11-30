package it.agileday.backtobasics;

import java.util.HashMap;


public class Positions extends HashMap<Player, Integer> {

	public void initializeWith(Player[] players) {
		for (Player player : players) {
			this.put(player, 0);
		}
	}

}
