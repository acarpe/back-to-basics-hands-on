package it.agileday.backtobasics;

import java.util.HashMap;


public class Positions  {

	private HashMap<Player, Integer> positionMap = new HashMap<Player, Integer>();
	
	public Integer get(Player key) {
		return positionMap.get(key);
	}

	public Integer put(Player key, Integer value) {
		return positionMap.put(key, value);
	}

	public void initializeWith(Player[] players) {
		for (Player player : players) {
			positionMap.put(player, 0);
		}
	}

}
