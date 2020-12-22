package players;

import enums.TileNameEnum;

/**
 * Singleton class for DiverPawn Extends the Abstract Class Pawn
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class DiverPawn extends Pawn {
	
	//===========================================================
	// Variable Setup
	//===========================================================
	private static DiverPawn instance = null;
	
	//===========================================================
	// Get Instance of Singleton
	//===========================================================
	/**
	 * gets the singleton instance of the DiverPawn object.
	 * @return the single DiverPawn object.
	 */
	public static DiverPawn getInstance() {
		if (instance == null)
			instance = new DiverPawn();
		return instance;
	}
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Calls the super constructor to create a pawn specific to the diver
	 */
	public DiverPawn() {
		super(TileNameEnum.IronGate);
	}
	
	//public boolean movePawn()
	
}
