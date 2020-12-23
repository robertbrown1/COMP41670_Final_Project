package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gamePieces.*;
import setup.*;
import cards.*;
import players.*;

/**
 * JUnit test cases for the Deck class
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class DeckTest {
	
	private BoardSetup boardSetup;
	private Board testBoard;
	private FloodSetup floodSetup;
	private FloodDeck testFlood;
	private PlayerList list;
	private TreasureSetup treasureSetup;
	
	@Before
	public void setUp() throws Exception {
        boardSetup = new BoardSetup();
        boardSetup.assignTiles();
        floodSetup = new FloodSetup();
        floodSetup.startSinking();
        list = PlayerList.getInstance();
        list.addPlayer(new EngineerPawn());
        list.addPlayer(new NavigatorPawn());
        treasureSetup = new TreasureSetup();
        treasureSetup.dealTreasureCards();
        testBoard = Board.getInstance();
        testFlood = FloodDeck.getInstance();
	}
	
	@After
    public void teardown() {
        boardSetup = null;
        floodSetup = null;
        list = null;
        treasureSetup = null;
        testBoard = null;
        testFlood = null;
    }
	
	@Test
	public void testStartSinking() {
		int numTiles = 0;
		for (int x = 0; x < 6; x++) { // For each x coordinate
			for (int y = 0; y < 6; y++) { // For each y coordinate
				Coordinate point = new Coordinate(x, y); // Position of tile
				if (testBoard.isTile(point) && testBoard.getBoard()[x][y].getFloodStatus()) {
					numTiles++; // Number of flooded tiles
				}
			}
		}
		assertEquals("Should be 6 flooded tiles", 6, numTiles);
	}
	
	@Test
	public void testDealTreasureCards() {
		int i;
		for (Pawn player: list.getAllPlayers()) { // For all players
			for (i = 0; i < 2; i++) { // For each card in hand
				assertFalse("Should not have water rise", player.getHand().get(i) instanceof WaterRiseCard);
			}
			assertEquals("More than 2 cards", 2, i);
		}
	}
	
	@Test
	public void testAddToDiscardPile() {
		int deckNum = testFlood.getCardsInDeck().size(); // Number of cards in deck
		int discardNum = testFlood.getDiscardPile().size(); // Number in discard pile
		if (deckNum == 0) { // No cards in deck
			deckNum = 24; // Set to 24 as deck will be shuffled
			discardNum = 0; // Set to 0 as deck will be shuffled
		}
		Card drawnCard = testFlood.drawCard(); // Get card from deck
		testFlood.addToDiscardPile(drawnCard); // Add to discard pile
		assertEquals("Should be 1 more card", discardNum+1, testFlood.getDiscardPile().size());
	}
	
}
