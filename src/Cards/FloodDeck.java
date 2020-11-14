package Cards;

import Enums.TileName;
import java.util.*;

public class FloodDeck extends Deck {
	
	private Stack<Card> floodDiscard;
	
	public FloodDeck() {
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
		Collections.shuffle(super.cardsInDeck);
		this.floodDiscard = new Stack<Card>(); // Initialize flood discard pile
	}
	
	public Stack<Card> drawFloodCards(int numOfCards) {
		Stack<Card> cardsDrawn = new Stack<Card>();
		for (int i = 0; i < numOfCards; i++) {
			if (super.cardsInDeck.isEmpty()) {
				shuffleFloodDeck();
			}
			cardsDrawn.push(super.cardsInDeck.peek());
			floodDiscard.push(super.cardsInDeck.pop());
		}
		return cardsDrawn;
	}
	
	public void shuffleFloodDeck() {
		while (!floodDiscard.isEmpty()) {
			super.cardsInDeck.push(floodDiscard.pop());
		}
		Collections.shuffle(super.cardsInDeck);
	}
	
}