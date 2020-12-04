package cards;

import enums.TreasureEnum;

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
		    super.cardsInDeck.push(new TreasureCard(TreasureEnum.EarthStone));
		    super.cardsInDeck.push(new TreasureCard(TreasureEnum.WindStatue));
		    super.cardsInDeck.push(new TreasureCard(TreasureEnum.FireCrystal));
		    super.cardsInDeck.push(new TreasureCard(TreasureEnum.OceanChalice));
	    }
	    // Create HelicopterLiftCards
	    for (int i = 0 ; i < 3 ; i++) {
		    super.cardsInDeck.push(new HelicopterLiftCard(TreasureEnum.HelicopterLift));
	    }
	    // Create SandBagCards
	    for (int i = 0 ; i < 2 ; i++) {
		    super.cardsInDeck.push(new SandBagCard(TreasureEnum.SandBag));
	    }
	    // Create WaterRiseCards
	    for (int i = 0 ; i < 3 ; i++) {
		    super.cardsInDeck.push(new WaterRiseCard(TreasureEnum.WaterRise));
	    }
	    super.shuffleDeck();
	}

}