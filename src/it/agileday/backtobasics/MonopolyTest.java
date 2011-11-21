package it.agileday.backtobasics;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MonopolyTest {

	private Game game;
	private Player horse;
	private Player car;

	@Before
	public void setUp() throws Exception {
		game = createGame();
		horse = new Player();
		car = new Player();
		game.setPlayers(horse, car);
	}
	
	private Game createGame() {
		return new Game(new Validators(	
				new TooManyPlayersValidator(), 
				new TooFewPlayersValidator()));
	}

	@Test
	public void playerBeginsOnZeroPositionRolls7EndsUponPosition7() throws Exception {
		assertThat(game.move(horse, 7), equalTo(7));
	}

	@Test
	public void playerBeginsOn39PositionRolls6EndsUponPosition5() throws Exception {
		game.move(horse, 39);
		assertThat(game.move(horse, 6), equalTo(5));
	}
	
	@Test
	public void twoPlayersOnBoardAreMoving() throws Exception {
		game.setPlayers(horse, car);
		assertThat(game.move(horse, 7), equalTo(7));
		assertThat(game.move(car, 4), equalTo(4));
	}

	
	@Test(expected=WrongNumberOfPlayerException.class)
	public void usingTheGameWithLessThanTwoPlayersIsNotPossible() throws Exception {
		Game gameBoard = createGame();
		gameBoard.setPlayers(horse);
		
	}
	
	@Test(expected=WrongNumberOfPlayerException.class)
	public void usingTheGameWithMoreThanEightPlayersIsNotPossible() throws Exception {
		Player dog = new Player();
		Player cannon = new Player();
		Player hat = new Player();
		Player boat = new Player();
		Player shoe = new Player();
		Player iron = new Player();
		Player thimble = new Player();
		Game gameBoard = createGame();
		gameBoard.setPlayers(horse, car, dog, cannon, hat, boat, shoe, iron, thimble);
	}
	

	@Test
	public void theOrderOfPlayerShouldBeRandomized() throws Exception {
		HashMap<List<Player>, Boolean> results = new HashMap<List<Player>, Boolean>();
		List<Player> horse_car = Arrays.asList(horse, car);
		List<Player> car_horse = Arrays.asList(car, horse);
		results.put(horse_car, false);
		results.put(car_horse, false);
		for (int i = 0; i < 100; i++) {
			Game game = createGame();
			results.put(game.setPlayers(horse, car), true);
		}
		for (Boolean result : results.values()) {
			assertTrue("One of the players combination has not occourred!", result);
		}
	}
	
	@Test
	public void whenPlayerLandOnGoItsBalanceIncreaseBy200() throws Exception {
	}

}
