package it.agileday.backtobasics;

import it.agileday.backtobasics.validators.WrongNumberOfPlayerException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Game {

	
	private Positions playerPositions;
	private Validator validator;
	private Players players;
	private List<Location> locations;
	private final Bank bank;

	public Game(Validator validator, List<Location> locations, Bank bank) {
		this.validator = validator;
		this.bank = bank;
		this.playerPositions = new Positions();
		this.players = new Players();
		this.locations = locations;
	}

	public int move(Player player, int step) {
		Integer currentPosition = playerPositions.get(player);
		for (int move = 0;  move < step; move++) {
			currentPosition = ++currentPosition % 40;
			locations.get(currentPosition).enteredBy(player);
		}
		locations.get(currentPosition).landedOnBy(player, currentPosition, playerPositions);
		return playerPositions.get(player);
	}

	public List<Player> setPlayers(Player ... players) throws WrongNumberOfPlayerException {
		validator.validate(players);
		this.playerPositions.initializeWith(players);
		this.players = new Players(players, bank);
		return this.players;
	}

}
