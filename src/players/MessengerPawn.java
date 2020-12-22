package players;

import cards.Card;
import enums.TileNameEnum;

/**
 * Singleton class for MessengerPawn Extends the Abstract Class Pawn
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class MessengerPawn extends Pawn {
	
	//===========================================================
	// Variable Setup
	//===========================================================
	private static MessengerPawn instance = null;
	
	//===========================================================
	// Get Instance of Singleton
	//===========================================================
	/**
	 * gets the singleton instance of the MessengerPawn object.
	 * @return the single MessengerPawn object.
	 */
	public static MessengerPawn getInstance() {
		if (instance == null)
			instance = new MessengerPawn();
		return instance;
	}
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Calls the super constructor to create a pawn specific to the messenger
	 */
	public MessengerPawn() {
		super(TileNameEnum.SilverGate);
	}
	
	//===========================================================
	// Methods
	//===========================================================
	/**
	 * giveTreasureCard is used to give a card to another player.
	 * Position does not need to be checked for the messenger
	 * @param treasureCard card to give to other player
	 * @param player to give card to
	 * @return true or false if the card has been given
	 */
	@Override
	public boolean giveTreasureCard(Card treasureCard, Pawn p) {
		Card temp;
		temp = super.hand.get(super.hand.indexOf(treasureCard));
		super.hand.remove(treasureCard); // Remove card from hand
		p.getHand().add(temp); // Give card to player
		return true;
	}
	
}
