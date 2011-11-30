package it.agileday.backtobasics;

import java.util.ArrayList;
import java.util.Collections;


public class Players extends ArrayList<Player> {

	public Players() {
	}
	
	public Players(Player[] players, Bank bank) {
		for (Player player : players) {
			this.add(player);
			bank.createAccountFor(player);
		}
		Collections.shuffle(this);
	}

}
