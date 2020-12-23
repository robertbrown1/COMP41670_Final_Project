package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cards.*;
import gamePieces.Board;
import gamePieces.Coordinate;
import players.*;
import setup.BoardSetup;
import setup.FloodSetup;
import setup.TreasureSetup;
import enums.*;
import java.util.*;

/**
 * JUnit test cases for the Pawn class
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */

public class PawnTest {
	
	private BoardSetup boardSetup;
	private Board testBoard;
	private FloodSetup floodSetup;
	private FloodDeck testFlood;
	private PlayerList list;
	private TreasureSetup treasureSetup;
	private TreasureDeck testTreasure;
	
	@Before
	public void setUp() throws Exception {
        boardSetup = new BoardSetup();
        boardSetup.assignTiles();
        floodSetup = new FloodSetup();
        floodSetup.startSinking();
        list = PlayerList.getInstance();
        list.addPlayer(new MessengerPawn());
        list.addPlayer(new NavigatorPawn());
        treasureSetup = new TreasureSetup();
        treasureSetup.dealTreasureCards();
        testBoard = Board.getInstance();
        testFlood = FloodDeck.getInstance();
        testTreasure = TreasureDeck.getInstance();
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
    }
	
	@Test
	public void testShoreUp() {
		for (int x = 0; x < 6; x++) { // For each x coordinate
			for (int y = 0; y < 6; y++) { // For each y coordinate
				Coordinate point = new Coordinate(x, y); // Position of tile
				if (Board.getTile(point) == null || Board.getTile(point).getSinkStatus() == true || Board.getTile(point).getFloodStatus() == false) {
					// Tile is invalid or is not flooded or has sank
					assertEquals("Can't Shore up", false, list.getPlayer(1).shoreUp(point));
				}
				else { // Tile can be shored up
					assertEquals("Can Shore up", true, list.getPlayer(1).shoreUp(point));
				}
			}
		}
	}
	
	@Test
	public void testGiveTreasureCard() {
		Card giveCard = list.getPlayer(1).getHand().get(0); // Get card from hand
		int playerOneSize = list.getPlayer(1).getHand().size(); // Size of 1st players hand
		int playerTwoSize = list.getPlayer(2).getHand().size(); // Size of 2nd players hand
		// Messenger can give card in all circumstances
		list.getPlayer(1).giveTreasureCard(giveCard, list.getPlayer(2));
		assertEquals("Should be one less card", playerOneSize-1, list.getPlayer(1).getHand().size());
		assertEquals("Should be one more card", playerTwoSize+1, list.getPlayer(2).getHand().size());
		assertEquals("Player 2 should have card", giveCard, list.getPlayer(2).getHand().get(playerTwoSize));
		// Other pawns can only give card when the two pawns are on the same tile
		assertFalse("Can't give card", list.getPlayer(2).giveTreasureCard(giveCard, list.getPlayer(1)));
	}
	
	@Test
	public void testCaptureTreasure() {
		Map<TreasureEnum, Integer> cards = new HashMap<TreasureEnum, Integer>();
		for (int i = 0; i < list.getPlayer(1).getHand().size(); i++) {
			// Keep track of how many of each card the player has
			if (cards.containsKey(list.getPlayer(1).getHand().get(i).getName())) {
				// Already in hash map
				cards.put((TreasureEnum)list.getPlayer(1).getHand().get(i).getName(),
						cards.get(list.getPlayer(1).getHand().get(i).getName())+1);
			}
			else { // Not in hash map yet
				cards.put((TreasureEnum)list.getPlayer(1).getHand().get(i).getName(), 1);
			}
		}
		// Should be able to capture Earth Stone
		if (cards.containsKey(TreasureEnum.EarthStone) && cards.get(TreasureEnum.EarthStone) == 4
				&& Board.getTile(list.getPlayer(1).getPosition()).getTreasure() == TreasureEnum.EarthStone)
			assertEquals("Should have treasure", true, list.getPlayer(1).captureTreasure(TreasureEnum.EarthStone));
		// Should be able to capture Fire Crystal
		else if (cards.containsKey(TreasureEnum.FireCrystal) && cards.get(TreasureEnum.FireCrystal) == 4
				&& Board.getTile(list.getPlayer(1).getPosition()).getTreasure() == TreasureEnum.FireCrystal)
			assertEquals("Should have treasure", true, list.getPlayer(1).captureTreasure(TreasureEnum.FireCrystal));
		// Should be able to capture Ocean Chalice
		else if (cards.containsKey(TreasureEnum.OceanChalice) && cards.get(TreasureEnum.OceanChalice) == 4
				&& Board.getTile(list.getPlayer(1).getPosition()).getTreasure() == TreasureEnum.OceanChalice)
			assertEquals("Should have treasure", true, list.getPlayer(1).captureTreasure(TreasureEnum.OceanChalice));
		// Should be able to capture Wind Statue
		else if (cards.containsKey(TreasureEnum.WindStatue) && cards.get(TreasureEnum.WindStatue) == 4
				&& Board.getTile(list.getPlayer(1).getPosition()).getTreasure() == TreasureEnum.WindStatue)
			assertEquals("Should have treasure", true, list.getPlayer(1).captureTreasure(TreasureEnum.WindStatue));
		else // Can't capture any treasure
			assertEquals("Should not be able to capture", false, list.getPlayer(1).captureTreasure(TreasureEnum.EarthStone));
			assertEquals("Should not be able to capture", false, list.getPlayer(1).captureTreasure(TreasureEnum.FireCrystal));
			assertEquals("Should not be able to capture", false, list.getPlayer(1).captureTreasure(TreasureEnum.OceanChalice));
			assertEquals("Should not be able to capture", false, list.getPlayer(1).captureTreasure(TreasureEnum.WindStatue));
	}

}
