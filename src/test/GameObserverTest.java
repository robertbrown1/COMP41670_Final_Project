package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cards.FloodDeck;
import cards.TreasureDeck;
import enums.TileNameEnum;
import enums.TreasureEnum;
import gamePieces.*;
import players.*;
import setup.*;
import observer.GameObserver;

public class GameObserverTest {

	private BoardSetup boardSetup;
	private Board testBoard;
	private FloodSetup floodSetup;
	private FloodDeck testFlood;
	private PlayerList list;
	private TreasureSetup treasureSetup;
	private TreasureDeck testTreasure;
	private WaterMeter testWater;
	private GameObserver testObserver;
	
	@Before
	public void setUp() throws Exception {
        boardSetup = new BoardSetup();
        boardSetup.assignTiles();
        floodSetup = new FloodSetup();
        floodSetup.startSinking();
        list = PlayerList.getInstance();
        list.addPlayer(new MessengerPawn());
        list.addPlayer(new DiverPawn());
        list.addPlayer(new ExplorerPawn());
        list.addPlayer(new PilotPawn());
        //treasureSetup = new TreasureSetup();
        //treasureSetup.dealTreasureCards();
        testWater = WaterMeter.getInstance();
        testWater.setWaterLevel(1);
        testBoard = Board.getInstance();
        //testFlood = FloodDeck.getInstance();
        //testTreasure = TreasureDeck.getInstance();
        testObserver = GameObserver.getInstance();
	}
	
	@After
    public void teardown() {
        boardSetup = null;
        floodSetup = null;
        list = null;
        treasureSetup = null;
        testBoard = null;
        testFlood = null;
        testTreasure = null;
        testWater = null;
        testObserver = null;
    }
	
	@Test
	public void testCheckPlayerTrapped() {
		testObserver.updatePlayerLocations(list.getAllPlayers()); // Get players location
		Pawn player = list.getPlayer(1); // Get player
		if (testBoard.isTile(player.getPosition().north()))
			// If possible, sink tile to the north
			Board.getTile(player.getPosition().north()).setSinkStatus(true);
		if (testBoard.isTile(player.getPosition().south()))
			// If possible, sink tile to the south
			Board.getTile(player.getPosition().south()).setSinkStatus(true);
		if (testBoard.isTile(player.getPosition().west()))
			// If possible, sink tile to the west
			Board.getTile(player.getPosition().west()).setSinkStatus(true);
		if (testBoard.isTile(player.getPosition().east()))
			// If possible, sink tile to the east
			Board.getTile(player.getPosition().east()).setSinkStatus(true);
		assertTrue("Pawn should be trapped", testObserver.checkPlayerTrapped());
		if (testBoard.isTile(player.getPosition().north()))
			// If possible, unsink tile to the north
			Board.getTile(player.getPosition().north()).setSinkStatus(false);
		if (testBoard.isTile(player.getPosition().south()))
			// If possible, unsink tile to the south
			Board.getTile(player.getPosition().south()).setSinkStatus(false);
		if (testBoard.isTile(player.getPosition().west()))
			// If possible, unsink tile to the west
			Board.getTile(player.getPosition().west()).setSinkStatus(false);
		if (testBoard.isTile(player.getPosition().east()))
			// If possible, unsink tile to the east
			Board.getTile(player.getPosition().east()).setSinkStatus(false);
	}
	
	@Test
	public void testCheckTreasureLost() {
		// Get treasures collected
		testObserver.updateTreasuresCollected(list.getTreasuresCollected());
		assertFalse("No treasure should be lost", testObserver.checkTreasureLost());
		// Sink one tile with the earth stone
		Board.getTile(Board.findByName(TileNameEnum.TempleOfTheMoon)).setSinkStatus(true);
		assertFalse("Can collect Earth Stone from other tile", testObserver.checkTreasureLost());
		// Sink other tile with the earth stone
		Board.getTile(Board.findByName(TileNameEnum.TempleOfTheSun)).setSinkStatus(true);
		assertTrue("Treasure should be lost", testObserver.checkTreasureLost());
		// Unsink tiles
		Board.getTile(Board.findByName(TileNameEnum.TempleOfTheMoon)).setSinkStatus(false);
		Board.getTile(Board.findByName(TileNameEnum.TempleOfTheSun)).setSinkStatus(false);
	}
	
	@Test
	public void testInPositionToWin() {
		// Capture all treasures
		list.collectTreasure(TreasureEnum.EarthStone);
		list.collectTreasure(TreasureEnum.WindStatue);
		list.collectTreasure(TreasureEnum.OceanChalice);
		list.collectTreasure(TreasureEnum.FireCrystal);
		// Put players on Fools Landing
		for (Pawn player: list.getAllPlayers()) {
			player.setPosition(Board.findByName(TileNameEnum.FoolsLanding));
		}
		testObserver.updatePlayerLocations(list.getAllPlayers()); // Get players location
		assertTrue("Game should be won", testObserver.inPositionToWin());
	}
	
	@Test
	public void testIsGameLost() {
		Board.getTile(Board.findByName(TileNameEnum.FoolsLanding)).setSinkStatus(true);
		// Check condition for Fools Landing sunk
		assertTrue("Fools Landing is sunk, so game is over", testObserver.isGameLost());
		Board.getTile(Board.findByName(TileNameEnum.FoolsLanding)).setSinkStatus(false);
		// Check condition for water level above 4
		testObserver.updateWaterLevel(5);
		assertTrue("Water level is 5, so game is over", testObserver.isGameLost());
	}

}
