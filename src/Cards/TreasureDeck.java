package Cards;

import java.util.Collections;
import java.util.Stack;

import Enums.Treasure;

public class TreasureDeck extends Deck {
	
	private Stack<Card> treasureDiscard;
	
	public TreasureDeck() {
	    // Prepare empty array of Cards
	    super();
	    // Create TreasureCards
	    for (int i=0;i<5;i++) {
		    super.cardsInDeck.push(new TreasureCard(Treasure.EarthStone));
		    super.cardsInDeck.push(new TreasureCard(Treasure.WindStatue));
		    super.cardsInDeck.push(new TreasureCard(Treasure.FireCrystal));
		    super.cardsInDeck.push(new TreasureCard(Treasure.OceanChalice));
	    }
	    // Create HelicopterLiftCards
	    for (int i=0;i<3;i++) {
		    super.cardsInDeck.push(new HelicopterLiftCard(Treasure.HelicopterLift));
	    }
	    // Create SandBagCards
	    for (int i=0;i<2;i++) {
		    super.cardsInDeck.push(new SandBagCard(Treasure.SandBag));
	    }
	    // Create WaterRiseCards
	    for (int i=0;i<3;i++) {
		    super.cardsInDeck.push(new WaterRiseCard(Treasure.WaterRise));
	    }
	    Collections.shuffle(super.cardsInDeck);
		this.treasureDiscard = new Stack<Card>(); // Initialize treasure discard pile
	}
	
	public void addToDiscardPile(Card discardedCard) {
		treasureDiscard.push(discardedCard);
	}
	
	public Stack<Card> drawTreasureCards(int numOfCards, boolean setup) {
		Stack<Card> cardsDrawn = new Stack<Card>();
		for (int i = 0; i < 2; i++) {
			if (super.cardsInDeck.isEmpty()) {
				shuffleTreasureDeck();
			}
			if (setup && super.cardsInDeck.peek() instanceof WaterRiseCard) {
				Card temp = super.cardsInDeck.pop();
				cardsDrawn.push(super.cardsInDeck.pop());
				super.cardsInDeck.push(temp);
			}
			else {
				cardsDrawn.push(super.cardsInDeck.pop());
			}
		}
		return cardsDrawn;
	}
	
	public void shuffleTreasureDeck() {
		while (!treasureDiscard.isEmpty()) {
			super.cardsInDeck.push(treasureDiscard.pop());
		}
		Collections.shuffle(super.cardsInDeck);
	}
	
}