package cards;

import enums.TileName;

public class FloodDeck extends Deck {
	
	private static FloodDeck instance = null;
	
	public static FloodDeck getInstance() {
		if (instance == null)
			instance = new FloodDeck();
		return instance;
	}
	
	private FloodDeck() {
		// Prepare empty array of Cards
		super();
		// Create TileCards
		super.cardsInDeck.push(new TileCard(TileName.TwilightHollow));
		super.cardsInDeck.push(new TileCard(TileName.TidalPalace));
		super.cardsInDeck.push(new TileCard(TileName.BreakersBridge));
		super.cardsInDeck.push(new TileCard(TileName.BronzeGate));
		super.cardsInDeck.push(new TileCard(TileName.CaveOfEmbers));
		super.cardsInDeck.push(new TileCard(TileName.CaveOfShadows));
		super.cardsInDeck.push(new TileCard(TileName.CliffsOfAbandon));
		super.cardsInDeck.push(new TileCard(TileName.CopperGate));
		super.cardsInDeck.push(new TileCard(TileName.CoralPalace));
		super.cardsInDeck.push(new TileCard(TileName.CrimsonForest));
		super.cardsInDeck.push(new TileCard(TileName.PhantomRock));
		super.cardsInDeck.push(new TileCard(TileName.Watchtower));
		super.cardsInDeck.push(new TileCard(TileName.DunesOfDeception));
		super.cardsInDeck.push(new TileCard(TileName.FoolsLanding));
		super.cardsInDeck.push(new TileCard(TileName.GoldGate));
		super.cardsInDeck.push(new TileCard(TileName.HowlingGarden));
		super.cardsInDeck.push(new TileCard(TileName.WhisperingGarden));
		super.cardsInDeck.push(new TileCard(TileName.SilverGate));
		super.cardsInDeck.push(new TileCard(TileName.Observatory));
		super.cardsInDeck.push(new TileCard(TileName.MistyMarsh));
		super.cardsInDeck.push(new TileCard(TileName.LostLagoon));
		super.cardsInDeck.push(new TileCard(TileName.IronGate));
		super.cardsInDeck.push(new TileCard(TileName.TempleOfTheSun));
		super.cardsInDeck.push(new TileCard(TileName.TempleOfTheMoon));
		super.shuffleDeck();
	}
	
}