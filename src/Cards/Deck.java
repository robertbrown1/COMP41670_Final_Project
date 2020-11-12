package Cards;

import java.util.*;

public abstract class Deck {
	
	protected List<Card> cardsInDeck;
	
	public Deck() {
	    // Prepare empty array of Cards
	    this.cardsInDeck = new ArrayList<Card>();
	}
	
}