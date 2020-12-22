package gamePieces;

import enums.TreasureEnum;

/**
 * Class for OceanTreasure Extends the Abstract Class Treasure
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class OceanTreasure extends Treasure {
	
	//===========================================================
	// Variable Setup
	//===========================================================
	private static OceanTreasure instance = null;
	
	//===========================================================
	// Get Instance of Singleton
	//===========================================================
	/**
	 * gets the singleton instance of the OceanTreasure object.
	 * @return the single OceanTreasure object.
	 */
	public static OceanTreasure getInstance() {
		if (instance == null)
			instance = new OceanTreasure();
		return instance;
	}
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Calls the super constructor to create a treasure specific to the ocean treasure
	 */
	public OceanTreasure() {
		super(TreasureEnum.OceanChalice);
	}
	
}