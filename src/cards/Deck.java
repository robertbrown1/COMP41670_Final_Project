package cards;

import java.util.*;

/**
 * Abstract Class for the Deck of Cards
 * Should be extended by TreasureDeck and FloodDeck
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */

public abstract class Deck {
	
	//===========================================================
	// Variable Setup
	//===========================================================
	protected Stack<Card> cardsInDeck; // Cards in deck
	protected Stack<Card> discardPile; // Cards in discard pile

	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Constructor for Deck object.
	 */
	public Deck() {
	    this.cardsInDeck = new Stack<Card>(); // Initialize stack of cards
	    this.discardPile = new Stack<Card>(); // Initialize discard pile
	}
	
	//===========================================================
	// Methods
	//===========================================================
	/**
	 * Remove card from the top of the deck.
	 * Put cards from discard pile back in deck and shuffle if there are no more cards in deck.
	 * @return the card from deck.
	 */
	public Card drawCard() {
		if (cardsInDeck.isEmpty()) { // Check if deck is empty
			shuffleDeck(); // Put discarded cards back in deck and shuffle
		}
		return cardsInDeck.pop(); // Remove card from top of deck
	}
	
	/**
	 * Put cards from discard pile in deck and shuffle.
	 */
	public void shuffleDeck() {
		while (!discardPile.isEmpty()) { // Until discard pile is empty
			cardsInDeck.push(discardPile.pop()); // Transfer from discard pile to deck
		}
		Collections.shuffle(cardsInDeck); // Shuffle cards
	}
	
	/**
	 * Adds card to discard pile.
	 * @param discardedCard card to discard
	 */
	public void addToDiscardPile(Card discardedCard) {
		discardPile.push(discardedCard); // Put card in discard pile
	}
	
	/**
	 * Adds card to deck.
	 * @param c card to put in deck
	 */
	public void replaceCard(Card c) {
		cardsInDeck.push(c); // Put card in deck
	}
	
	/**
	 * Shuffles deck
	 */
	public void shuffle() {
		Collections.shuffle(this.cardsInDeck); // Shuffle deck
	}
	
}