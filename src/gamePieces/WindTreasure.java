package gamePieces;

import enums.TreasureEnum;

/**
 * Class for WindTreasure Extends the Abstract Class Treasure
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class WindTreasure extends Treasure {
	
	//===========================================================
	// Variable Setup
	//===========================================================
	private static WindTreasure instance = null;
	
	//===========================================================
	// Get Instance of Singleton
	//===========================================================
	/**
	 * gets the singleton instance of the WindTreasure object.
	 * @return the single WindTreasure object.
	 */
	public static WindTreasure getInstance() {
		if (instance == null)
			instance = new WindTreasure();
		return instance;
	}
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Calls the super constructor to create a treasure specific to the wind treasure
	 */
	public WindTreasure() {
		super(TreasureEnum.WindStatue);
	}
	
}
