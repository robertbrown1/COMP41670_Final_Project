package setup;

import cards.Card;
import cards.TreasureDeck;
import cards.WaterRiseCard;
import players.Pawn;
import players.PlayerList;

/**
 * Class to handle all aspects of setting up the treasure deck
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class TreasureSetup {
	
	// ===========================================================
	// Variable Setup
	// ===========================================================
	private TreasureDeck setupTreasure;
	
	// ===========================================================
	// Constructor
	// ===========================================================
	/**
	 * Constructor for TreasureSetup class
	 */
	public TreasureSetup() {
		this.setupTreasure = TreasureDeck.getInstance();
	}
	
	//===========================================================
	// Methods
	//===========================================================
	/**
	 * Give treasure cards to players
	 */
	public void dealTreasureCards() {
		Card cardDrawn;
		for (Pawn p : PlayerList.getInstance().getAllPlayers()) { // For each player
			for (int i = 0 ; i < 2 ; i++) { // Give 2 cards to each player
				cardDrawn = setupTreasure.drawCard(); // Draw treasure card
				while (cardDrawn instanceof WaterRiseCard) { // Can not draw water rise card in setup
					setupTreasure.replaceCard(cardDrawn); // Put card back in deck
					setupTreasure.shuffleDeck(); // Shuffle the deck
					cardDrawn = setupTreasure.drawCard(); // Draw a different card
				}
				p.getHand().add(cardDrawn); // Put card in hand
			}
		}
	}
	
}
