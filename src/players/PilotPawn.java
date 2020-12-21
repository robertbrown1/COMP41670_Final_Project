package players;

import enums.TileNameEnum;

/**
 * Singleton class for PilotPawn Extends the Abstract Class Pawn
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class PilotPawn extends Pawn {
	
	//===========================================================
	// Variable Setup
	//===========================================================
	private static PilotPawn instance = null;
	
	//===========================================================
	// Get Instance of Singleton
	//===========================================================
	/**
	 * gets the singleton instance of the PilotPawn object.
	 * @return the single PilotPawn object.
	 */
	public static PilotPawn getInstance() {
		if (instance == null)
			instance = new PilotPawn();
		return instance;
	}
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Calls the super constructor to create a pawn specific to the pilot
	 */
	public PilotPawn() {
		super(TileNameEnum.FoolsLanding);
	}
	
}
