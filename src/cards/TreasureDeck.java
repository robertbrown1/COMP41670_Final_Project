package cards;

import enums.Treasure;

public class TreasureDeck extends Deck {
	
	private static TreasureDeck instance = null;
	
	public static TreasureDeck getInstance() {
		if (instance == null)
			instance = new TreasureDeck();
		return instance;
	}
	
	private TreasureDeck() {
	    super();
	    // Create TreasureCards
	    for (int i = 0 ; i < 5 ; i++) {
		    super.cardsInDeck.push(new TreasureCard(Treasure.EarthStone));
		    super.cardsInDeck.push(new TreasureCard(Treasure.WindStatue));
		    super.cardsInDeck.push(new TreasureCard(Treasure.FireCrystal));
		    super.cardsInDeck.push(new TreasureCard(Treasure.OceanChalice));
	    }
	    // Create HelicopterLiftCards
	    for (int i = 0 ; i < 3 ; i++) {
		    super.cardsInDeck.push(new HelicopterLiftCard(Treasure.HelicopterLift));
	    }
	    // Create SandBagCards
	    for (int i = 0 ; i < 2 ; i++) {
		    super.cardsInDeck.push(new SandBagCard(Treasure.SandBag));
	    }
	    // Create WaterRiseCards
	    for (int i = 0 ; i < 3 ; i++) {
		    super.cardsInDeck.push(new WaterRiseCard(Treasure.WaterRise));
	    }
	    super.shuffleDeck();
	}

}