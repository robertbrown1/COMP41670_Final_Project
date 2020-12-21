package cards;

import enums.TreasureEnum;

/**
 * Class for Treasure Deck Extends the Abstract Class Deck
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */

public class TreasureDeck extends Deck {
	
	//===========================================================
	// Variable Setup
	//===========================================================
	private static TreasureDeck instance = null;
	
	//===========================================================
	// Get Instance of Singleton
	//===========================================================
	/**
	 * gets the singleton instance of the TreasureDeck object.
	 * @return the single TreasureDeck object.
	 */
	public static TreasureDeck getInstance() {
		if (instance == null)
			instance = new TreasureDeck();
		return instance;
	}
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Generates all the cards in the treasure deck.
	 */
	private TreasureDeck() {
	    super(); // Prepare empty array of cards
	    // Create TreasureCards and place in Treasure Deck
	    for (int i = 0 ; i < 5 ; i++) {
		    super.cardsInDeck.push(new TreasureCard(TreasureEnum.EarthStone));
		    super.cardsInDeck.push(new TreasureCard(TreasureEnum.WindStatue));
		    super.cardsInDeck.push(new TreasureCard(TreasureEnum.FireCrystal));
		    super.cardsInDeck.push(new TreasureCard(TreasureEnum.OceanChalice));
	    }
	    // Create HelicopterLiftCards and place in Treasure Deck
	    for (int i = 0 ; i < 3 ; i++) {
		    super.cardsInDeck.push(new HelicopterLiftCard(TreasureEnum.HelicopterLift));
	    }
	    // Create SandBagCards and place in Treasure Deck
	    for (int i = 0 ; i < 2 ; i++) {
		    super.cardsInDeck.push(new SandBagCard(TreasureEnum.SandBag));
	    }
	    // Create WaterRiseCards and place in Treasure Deck
	    for (int i = 0 ; i < 3 ; i++) {
		    super.cardsInDeck.push(new WaterRiseCard(TreasureEnum.WaterRise));
	    }
	    super.shuffleDeck(); // Shuffle deck
	}

}