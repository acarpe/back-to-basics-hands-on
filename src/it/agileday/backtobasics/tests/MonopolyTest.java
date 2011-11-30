package it.agileday.backtobasics.tests;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import it.agileday.backtobasics.Bank;
import it.agileday.backtobasics.Dice;
import it.agileday.backtobasics.Game;
import it.agileday.backtobasics.Location;
import it.agileday.backtobasics.Player;
import it.agileday.backtobasics.Validators;
import it.agileday.backtobasics.locations.LocationsBuilder;
import it.agileday.backtobasics.locations.Property;
import it.agileday.backtobasics.validators.TooFewPlayersValidator;
import it.agileday.backtobasics.validators.TooManyPlayersValidator;
import it.agileday.backtobasics.validators.WrongNumberOfPlayerException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class MonopolyTest {

	private Game game;
	private Player horse;
	private Player car;
	private Bank bank;
	private List<Location> locations;
	private FakeDice dice;

	@Before
	public void setUp() throws Exception {
		bank = new Bank();
		dice = new FakeDice();
		game = createGame(bank, dice);
		horse = new Player();
		car = new Player();
		game.setPlayers(horse, car);
	}
	
	private Game createGame(Bank bank, Dice dice) {
		return new Game(new Validators(	
				new TooManyPlayersValidator(), 
				new TooFewPlayersValidator()), 
				createLocations(bank, dice),
				bank);
	}

	private List<Location> createLocations(Bank bank, Dice dice) {
		locations = new LocationsBuilder().build(bank, dice);
		return locations;		
	}
	private Game createGame() {
		return createGame(bank, dice);
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
		assertThat(bank.balanceFor(horse), equalTo(0));
		game.move(horse, 40);
		assertThat(bank.balanceFor(horse), equalTo(200));
	}
	
	@Test
	public void whenPlayerPassOverGoItsBalanceIncreaseBy200() throws Exception {
		assertThat(bank.balanceFor(horse), equalTo(0));
		game.move(horse, 50);
		assertThat(bank.balanceFor(horse), equalTo(200));
	}
	
	@Test
	public void whenPlayerPassOverGoTwoTimesItsBalanceIncreaseBy400() throws Exception {
		assertThat(bank.balanceFor(horse), equalTo(0));
		game.move(horse, 90);
		assertThat(bank.balanceFor(horse), equalTo(400));
	}
	
	@Test
	public void whenPlayerLandOnNormalPositionItsBalanceNotVary() throws Exception {
		game.move(horse, 20);
		assertThat(bank.balanceFor(horse), equalTo(0));
	}
	
	@Test
	public void whenPlayerLandOnGoToJailHeWillBeMovedOnJustVisiting() throws Exception {
		assertThat(game.move(horse, 30), equalTo(10));
	}
	
	@Test
	public void whenPlayerLandOnIncomeTaxShouldPay10Percent() throws Exception {
		bank.increaseMoneyTo(horse, 1800);
		game.move(horse, 4);
		assertThat(bank.balanceFor(horse), equalTo(1620));
	}
	
	@Test
	public void whenPlayerLandOnIncomeTaxShouldPayNoMoreThan200() throws Exception {
		bank.increaseMoneyTo(horse, 3000);
		game.move(horse, 4);
		assertThat(bank.balanceFor(horse), equalTo(2800));
	}
	
	@Test
	public void whenPlayerLandOnIncomeTaxWithZeroBalanceShouldPayZero() throws Exception {
		bank.increaseMoneyTo(horse, 0);
		game.move(horse, 4);
		assertThat(bank.balanceFor(horse), equalTo(0));
	}
	
	@Test
	public void whenPlayerLandOnLuxuryTaxShouldPay75() throws Exception {
		bank.increaseMoneyTo(horse, 100);
		game.move(horse, 38);
		assertThat(bank.balanceFor(horse), equalTo(25));
	}
	
	@Test
	public void whenPlayerLandOnUnownedPropertyPay60ToTakeOwnership() throws Exception {
		bank.increaseMoneyTo(horse, 100);
		game.move(horse, 1);
		assertThat(bank.balanceFor(horse), equalTo(40));
	}
	
	@Test
	public void whenPlayerLandOnUnownedPropertyTakesOwnership() throws Exception {
		game.move(horse, 1);
		Property property = (Property) locations.get(1);
		assertThat(property.owner(), equalTo(horse));
	}
	
	@Test
	public void whenPlayerLandOnOwnedPropertyShouldNotTakeOwnership() throws Exception {
		game.move(horse, 1);
		game.move(car, 1);
		Property property = (Property) locations.get(1);
		assertThat(property.owner(), equalTo(horse));
	}
	
	@Test
	public void whenPlayerLandOnOwnerPropertyShouldPayTheRent() throws Exception {
		bank.increaseMoneyTo(car, 100);
		game.move(horse, 1);
		game.move(car, 1);
		assertThat(bank.balanceFor(car), equalTo(80));
	}
	
	@Test
	public void whenPlayerLandOnOwnedPropertyShouldPayTheRentToOwner() throws Exception {
		game.move(horse, 1);
		game.move(car, 1);
		assertThat(bank.balanceFor(horse), equalTo(-60+20));
	}

	@Test
	public void whenPlayerLandOnRealEstateAndOwnerOwnsAllTheSameRealEstatesInGroupShouldPayTheRentDoubled() throws Exception {
		bank.increaseMoneyTo(horse, 100);
		bank.increaseMoneyTo(car, 100);
		game.move(horse, 1);
		game.move(horse, 2);
		game.move(car, 1);
		int rent = 20*2;
		int buyPrice = 60*2;
		assertThat(bank.balanceFor(car), equalTo(100-rent));
		assertThat(bank.balanceFor(horse), equalTo(100-buyPrice+rent));
	}
	
	@Test
	public void whenPlayerLandOnRailRoadShouldPay25WhenOwnerHasOneRailRoad() throws Exception {
		game.move(horse, 5);
		game.move(car, 5);
		int rent = 25;
		int buyPrice = 200;
		assertThat(bank.balanceFor(horse), equalTo(-buyPrice+rent));
	}
	
	@Test
	public void whenPlayerLandOnRailRoadShouldPay200WhenOwnerHasAllRailRoads() throws Exception {
		game.move(horse, 5);
		game.move(horse, 10);
		game.move(horse, 10);
		game.move(horse, 10);
		game.move(car, 5);
		int rent = 200;
		assertThat(bank.balanceFor(car), equalTo(-rent));
	}

	@Test
	public void whenPlayerLandOnUtilityShouldPay4TimesDiceValueWhenOwnerHasOnlyOneUtility() throws Exception {
		dice.lastRoll(12);
		game.move(horse, 12);
		game.move(car, 12);
		int rent = 12*4;
		assertThat(bank.balanceFor(car), equalTo(-rent));
	}

	@Test
	public void whenPlayerLandOnUtilityShouldPay10TimesDiceValueWhenOwnerHasAllUtilities() throws Exception {
		dice.lastRoll(12);
		game.move(horse, 12);
		game.move(horse, 16);
		game.move(car, 12);
		int rent = 12*10;
		assertThat(bank.balanceFor(car), equalTo(-rent));
	}
	
}
