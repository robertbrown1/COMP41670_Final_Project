package setup;

import cards.*;
import enums.TileNameEnum;
import gamePieces.*;

/**
 * Class to handle all aspects of setting up the Flood Deck
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class FloodSetup {
	
	//===========================================================
	// Setup Variables
	//===========================================================
	private FloodDeck setupFlood;
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Constructor for the FloodSetup Class
	 */
	public FloodSetup() {
		this.setupFlood = FloodDeck.getInstance();
	}
	
	//===========================================================
	// Methods
	//===========================================================
	/**
	 * Sink some of the tiles
	 */
	public void startSinking() {
		Card cardDrawn;
		for (int i = 0 ; i < 6 ; i++) {
			cardDrawn = setupFlood.drawCard(); // Draw card from flood deck
			Coordinate Tile = Board.findByName((TileNameEnum)cardDrawn.getName()); // Corresponding tile
			if (Board.getInstance().getTile(Tile) != null) { // Valid tile
				Board.getInstance().getTile(Tile).setFloodStatus(true); // Flood tile
			}
			setupFlood.addToDiscardPile(cardDrawn); // Put card in discard pile
		}
	}
	
}
