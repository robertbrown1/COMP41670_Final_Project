package cards;

import enums.TileNameEnum;

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
		super.shuffleDeck();
	}
	
}