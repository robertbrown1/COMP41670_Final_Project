package Cards;

import Enums.TileName;

public class FloodDeck extends Deck {
	
	public FloodDeck() {
		// Prepare empty array of Cards
		super();
		// Create TileCards
		super.cardsInDeck.add(new TileCard(TileName.TwilightHollow));
		super.cardsInDeck.add(new TileCard(TileName.TidalPalace));
		super.cardsInDeck.add(new TileCard(TileName.BreakersBridge));
		super.cardsInDeck.add(new TileCard(TileName.BronzeGate));
		super.cardsInDeck.add(new TileCard(TileName.CaveOfEmbers));
		super.cardsInDeck.add(new TileCard(TileName.CaveOfShadows));
		super.cardsInDeck.add(new TileCard(TileName.CliffsOfAbandon));
		super.cardsInDeck.add(new TileCard(TileName.CopperGate));
		super.cardsInDeck.add(new TileCard(TileName.CoralPalace));
		super.cardsInDeck.add(new TileCard(TileName.CrimsonForest));
		super.cardsInDeck.add(new TileCard(TileName.PhantomRock));
		super.cardsInDeck.add(new TileCard(TileName.Watchtower));
		super.cardsInDeck.add(new TileCard(TileName.DunesOfDeception));
		super.cardsInDeck.add(new TileCard(TileName.FoolsLanding));
		super.cardsInDeck.add(new TileCard(TileName.GoldGate));
		super.cardsInDeck.add(new TileCard(TileName.HowlingGarden));
		super.cardsInDeck.add(new TileCard(TileName.WhisperingGarden));
		super.cardsInDeck.add(new TileCard(TileName.SilverGate));
		super.cardsInDeck.add(new TileCard(TileName.Observatory));
		super.cardsInDeck.add(new TileCard(TileName.MistyMarsh));
		super.cardsInDeck.add(new TileCard(TileName.LostLagoon));
		super.cardsInDeck.add(new TileCard(TileName.IronGate));
		super.cardsInDeck.add(new TileCard(TileName.TempleOfTheSun));
		super.cardsInDeck.add(new TileCard(TileName.TempleOfTheMoon));
	}
}