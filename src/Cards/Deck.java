package Cards;

import java.util.*;

public abstract class Deck {
	
	protected Stack<Card> cardsInDeck;
	protected Stack<Card> discardPile;

	public Deck() {
	    this.cardsInDeck = new Stack<Card>(); // Initialize stack of cards
	    this.discardPile = new Stack<Card>(); // Initialize discard pile
	}
	
	public Card drawCard() {
		if (cardsInDeck.isEmpty()) {
			shuffleDeck();
		}
		return cardsInDeck.pop();
	}
	
	public void shuffleDeck() {
		while (!discardPile.isEmpty()) {
			cardsInDeck.push(discardPile.pop());
		}
		Collections.shuffle(cardsInDeck);
	}
	
	public void addToDiscardPile(Card discardedCard) {
		discardPile.push(discardedCard);
	}
	
	public void replaceCard(Card c) {
		cardsInDeck.push(c);
	}
	
	public void shuffle() {
		
		Collections.shuffle(this.cardsInDeck);
		
	}
	
}