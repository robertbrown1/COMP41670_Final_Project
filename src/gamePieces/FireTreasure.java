package gamePieces;

import enums.TreasureEnum;

/**
 * Class for FireTreasure Extends the Abstract Class Treasure
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class FireTreasure extends Treasure {
	
	//===========================================================
	// Variable Setup
	//===========================================================
	private static FireTreasure instance = null;
	
	//===========================================================
	// Get Instance of Singleton
	//===========================================================
	/**
	 * gets the singleton instance of the FireTreasure object.
	 * @return the single FireTreasure object.
	 */
	public static FireTreasure getInstance() {
		if (instance == null)
			instance = new FireTreasure();
		return instance;
	}
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Calls the super constructor to create a treasure specific to the fire treasure
	 */
	public FireTreasure() {
		super(TreasureEnum.FireCrystal);
	}
	
}
