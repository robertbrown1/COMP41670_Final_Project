package cards;

import enums.TileNameEnum;

/**
 * Class for Flood Deck Extends the Abstract Class Deck
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */

public class FloodDeck extends Deck {
	
	//===========================================================
	// Variable Setup
	//===========================================================
	private static FloodDeck instance = null;
	
	//===========================================================
	// Get Instance of Singleton
	//===========================================================
	/**
	 * gets the singleton instance of the FloodDeck object.
	 * @return the single FloodDeck object.
	 */
	public static FloodDeck getInstance() {
		if (instance == null)
			instance = new FloodDeck();
		return instance;
	}
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Generates all the cards in the flood deck.
	 */
	private FloodDeck() {
		super(); // Prepare empty array of Cards
		// Create TileCards and place in flood deck
		super.cardsInDeck.push(new TileCard(TileNameEnum.TwilightHollow));
		super.cardsInDeck.push(new TileCard(TileNameEnum.TidalPalace));
		super.cardsInDeck.push(new TileCard(TileNameEnum.BreakersBridge));
		super.cardsInDeck.push(new TileCard(TileNameEnum.BronzeGate));
		super.cardsInDeck.push(new TileCard(TileNameEnum.CaveOfEmbers));
		super.cardsInDeck.push(new TileCard(TileNameEnum.CaveOfShadows));
		super.cardsInDeck.push(new TileCard(TileNameEnum.CliffsOfAbandon));
		super.cardsInDeck.push(new TileCard(TileNameEnum.CopperGate));
		super.cardsInDeck.push(new TileCard(TileNameEnum.CoralPalace));
		super.cardsInDeck.push(new TileCard(TileNameEnum.CrimsonForest));
		super.cardsInDeck.push(new TileCard(TileNameEnum.PhantomRock));
		super.cardsInDeck.push(new TileCard(TileNameEnum.Watchtower));
		super.cardsInDeck.push(new TileCard(TileNameEnum.DunesOfDeception));
		super.cardsInDeck.push(new TileCard(TileNameEnum.FoolsLanding));
		super.cardsInDeck.push(new TileCard(TileNameEnum.GoldGate));
		super.cardsInDeck.push(new TileCard(TileNameEnum.HowlingGarden));
		super.cardsInDeck.push(new TileCard(TileNameEnum.WhisperingGarden));
		super.cardsInDeck.push(new TileCard(TileNameEnum.SilverGate));
		super.cardsInDeck.push(new TileCard(TileNameEnum.Observatory));
		super.cardsInDeck.push(new TileCard(TileNameEnum.MistyMarsh));
		super.cardsInDeck.push(new TileCard(TileNameEnum.LostLagoon));
		super.cardsInDeck.push(new TileCard(TileNameEnum.IronGate));
		super.cardsInDeck.push(new TileCard(TileNameEnum.TempleOfTheSun));
		super.cardsInDeck.push(new TileCard(TileNameEnum.TempleOfTheMoon));
		super.shuffleDeck(); // Shuffle deck
	}
	
}