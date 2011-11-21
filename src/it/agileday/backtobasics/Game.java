package it.agileday.backtobasics;

import java.util.ArrayList;


public class Game {

	private Positions playerPositions;
	private Players players;
	private final Validator validator;
	
	public Game(Validator validator) {
		this.validator = validator;
		this.playerPositions = new Positions();
		this.players = new Players();
	}

	public Integer move(Player player, int step) {
		Integer currentPosition = playerPositions.get(player);
		
		Integer newPosition = (currentPosition + step) % 40;
		playerPositions.put(player, newPosition);
		return newPosition;
	}

	public Players setPlayers(Player ... players) throws WrongNumberOfPlayerException {
		validator.validate(players);
		this.playerPositions.initializeWith(players);
		this.players = new Players(players);
		return this.players;
	}

}
