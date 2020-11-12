package Cards;

import Enums.Treasure;

public class TreasureDeck extends Deck {
	
	public TreasureDeck() {
	    // Prepare empty array of Cards
	    super();
	    // Create TreasureCards
	    for (int i=0;i<5;i++) {
		    super.cardsInDeck.add(new TreasureCard(Treasure.EarthStone));
		    super.cardsInDeck.add(new TreasureCard(Treasure.WindStatue));
		    super.cardsInDeck.add(new TreasureCard(Treasure.FireCrystal));
		    super.cardsInDeck.add(new TreasureCard(Treasure.OceanChalice));
	    }
	    // Create HelicopterLiftCards
	    for (int i=0;i<3;i++) {
		    super.cardsInDeck.add(new HelicopterLiftCard(Treasure.HelicopterLift));
	    }
	    // Create SandBagCards
	    for (int i=0;i<2;i++) {
		    super.cardsInDeck.add(new SandBagCard(Treasure.SandBag));
	    }
	    // Create WaterRiseCards
	    for (int i=0;i<3;i++) {
		    super.cardsInDeck.add(new WaterRiseCard(Treasure.WaterRise));
	    }
	}
	
}