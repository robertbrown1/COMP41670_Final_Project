package cards;

/**
 * Abstract Class for Cards in a card deck
 * Should be extended by HelicopterLiftCard, SandBagCard, TileCard, TreasureCard and WaterRiseCard
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */

public abstract class Card {
	
		//===========================================================
		// Variable Setup
		//===========================================================
		@SuppressWarnings("rawtypes")
	    private Enum name;   // Name of object referred to by card
		
		//===========================================================
		// Constructor
		//===========================================================
		/**
		 * Abstract constructor for Card object.
		 * @param name Enum name for card.
		 */
		Card(@SuppressWarnings("rawtypes") Enum name){
			this.name = name;
		}

		//===========================================================
		// Getters
		//===========================================================
		// getter for card's name
		@SuppressWarnings("rawtypes")
	    public Enum getName() {
			return this.name;
		}
	
}