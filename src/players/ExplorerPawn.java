package players;

import enums.TileNameEnum;

/**
 * Singleton class for ExplorerPawn Extends the Abstract Class Pawn
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class ExplorerPawn extends Pawn {
	
	//===========================================================
	// Variable Setup
	//===========================================================
	private static ExplorerPawn instance = null;
	
	//===========================================================
	// Get Instance of Singleton
	//===========================================================
	/**
	 * gets the singleton instance of the ExplorerPawn object.
	 * @return the single ExplorerPawn object.
	 */
	public static ExplorerPawn getInstance() {
		if (instance == null)
			instance = new ExplorerPawn();
		return instance;
	}
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Calls the super constructor to create a pawn specific to the explorer
	 */
	public ExplorerPawn() {
		super(TileNameEnum.CopperGate);
	}
	
}
