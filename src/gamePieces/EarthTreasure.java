package gamePieces;

import enums.TreasureEnum;

/**
 * Class for EarthTreasure Extends the Abstract Class Treasure
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class EarthTreasure extends Treasure {
	
	//===========================================================
	// Variable Setup
	//===========================================================
	private static EarthTreasure instance = null;
	
	//===========================================================
	// Get Instance of Singleton
	//===========================================================
	/**
	 * gets the singleton instance of the EarthTreasure object.
	 * @return the single EarthTreasure object.
	 */
	public static EarthTreasure getInstance() {
		if (instance == null)
			instance = new EarthTreasure();
		return instance;
	}
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Calls the super constructor to create a treasure specific to the earth treasure
	 */
	public EarthTreasure() {
		super(TreasureEnum.EarthStone);
	}
	
}
