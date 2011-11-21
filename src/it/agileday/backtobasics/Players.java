package it.agileday.backtobasics;

import java.util.ArrayList;
import java.util.Collections;


public class Players extends ArrayList<Player> {

	public Players(Player[] players) {
		for (Player player : players) {
			this.add(player);
		}
		Collections.shuffle(this);
	}

	public Players() {
	}

}
