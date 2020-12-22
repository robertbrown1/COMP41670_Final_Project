package players;

import enums.TileNameEnum;

/**
 * Singleton class for NavigatorPawn Extends the Abstract Class Pawn
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class NavigatorPawn extends Pawn {
	
	//===========================================================
	// Variable Setup
	//===========================================================
	private static NavigatorPawn instance = null;
	
	//===========================================================
	// Get Instance of Singleton
	//===========================================================
	/**
	 * gets the singleton instance of the NavigatorPawn object.
	 * @return the single NavigatorPawn object.
	 */
	public static NavigatorPawn getInstance() {
		if (instance == null)
			instance = new NavigatorPawn();
		return instance;
	}
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Calls the super constructor to create a pawn specific to the navigator
	 */
	public NavigatorPawn() {
		super(TileNameEnum.GoldGate);
	}
	
}
