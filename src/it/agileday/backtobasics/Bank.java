package it.agileday.backtobasics;

import java.util.HashMap;


public class Bank {

	private HashMap<Player, Integer> accounts;
	
	public Bank() {
		this.accounts = new HashMap<Player, Integer>();
	}

	public int balanceFor(Player player) {
		return accounts.get(player);
	}

	public void increaseMoneyTo(Player player, int amount) {
		accounts.put(player, balanceFor(player) + amount);
	}

	public void decreaseMoneyTo(Player player, int amount) {
		accounts.put(player, balanceFor(player) - amount);
	}

	public void createAccountFor(Player player) {
		accounts.put(player, 0);
	}

}
