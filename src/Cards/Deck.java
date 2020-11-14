package Cards;

import java.util.*;

public abstract class Deck {
	
	protected Stack<Card> cardsInDeck;
	
	public Deck() {
	    // Prepare empty array of Cards
	    this.cardsInDeck = new Stack<Card>();
	}
	
}