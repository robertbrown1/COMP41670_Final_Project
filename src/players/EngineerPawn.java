package players;

import enums.TileNameEnum;

/**
 * Singleton class for EngineerPawn Extends the Abstract Class Pawn
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class EngineerPawn extends Pawn {
	
	//===========================================================
	// Variable Setup
	//===========================================================
	private static EngineerPawn instance = null;
	
	//===========================================================
	// Get Instance of Singleton
	//===========================================================
	/**
	 * gets the singleton instance of the EngineerPawn object.
	 * @return the single EngineerPawn object.
	 */
	public static EngineerPawn getInstance() {
		if (instance == null)
			instance = new EngineerPawn();
		return instance;
	}
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Calls the super constructor to create a pawn specific to the engineer
	 */
	public EngineerPawn() {
		super(TileNameEnum.BronzeGate);
	}
	
}
