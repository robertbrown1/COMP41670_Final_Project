package Cards;

public abstract class Card {
	
	//===========================================================
		// Variable Setup
		//===========================================================
		@SuppressWarnings("rawtypes")
	    private Enum name;   // Name of object referred to by card,  e.g. "Mrs. Peacock"
		
		//===========================================================
		// Constructor
		//===========================================================
		/**
		 * Abstract constructor for Card object.
		 * @param name Enum name for card.
		 */
		Card(@SuppressWarnings("rawtypes") Enum name){
			this.name   = name;
		}

		//===========================================================
		// Getters
		//===========================================================
		// getter for card's name
		@SuppressWarnings("rawtypes")
	    protected Enum getName() {
			return this.name;
		}
	
}