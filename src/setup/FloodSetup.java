package setup;

import cards.*;
import enums.TileNameEnum;
import gamePieces.*;

public class FloodSetup {
	
	private FloodDeck setupFlood;
	
	public FloodSetup() {
	    // Get first instance of Board and extract dimensions
		this.setupFlood = FloodDeck.getInstance();
	}
	
	public void startSinking() {
		Card cardDrawn;
		for (int i = 0 ; i < 6 ; i++) {
			cardDrawn = setupFlood.drawCard();
			Coordinate Tile = Board.findByName((TileNameEnum)cardDrawn.getName());
			if (Board.getTile(Tile) != null) {
				Board.getTile(Tile).setFloodStatus(true);                                 
			}
			setupFlood.addToDiscardPile(cardDrawn);
		}
	}
	
}
