package setup;

import cards.Card;
import cards.TreasureDeck;
import cards.WaterRiseCard;
import players.Pawn;
import players.PlayerList;

public class TreasureSetup {
	
	private TreasureDeck setupTreasure;
	private PlayerList playerList;
	
	public TreasureSetup() {
	    // Get first instance of Board and extract dimensions
		this.setupTreasure = TreasureDeck.getInstance();
		this.playerList = PlayerList.getInstance();
	}
	
	public void dealTreasureCards() {
		Card cardDrawn;
		for (Pawn p:playerList.getAllPlayers()) {
			for (int i = 0 ; i < 2 ; i++) {
				cardDrawn = setupTreasure.drawCard();
				while (cardDrawn instanceof WaterRiseCard) {
					setupTreasure.replaceCard(cardDrawn);
					setupTreasure.shuffleDeck();
					cardDrawn = setupTreasure.drawCard();
				}
				p.getHand().add(cardDrawn);
			}
		}
	}
	
}
