package gamePieces;

import enums.TreasureEnum;

/**
 * Abstract Class for the Treasures
 * Should be extended by EarthTreasure, FireTreasure, OceanTreasure and WindTreasure
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */

public abstract class Treasure {
	
	//===========================================================
	// Variable Setup
	//===========================================================
	private TreasureEnum name; // Name of treasure
	private boolean collected; // Status of whether treasure has been collected
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Constructor for Treasure object.
	 * @param name of treasure
	 */
	public Treasure(TreasureEnum name) {
		
		this.name = name;
		this.collected = false;
	
	}
	
	//===========================================================
    // Getters and Setters
    //===========================================================
	/**
     * getStatus returns whether the treasure has been collected
     */
	public boolean getStatus() {
		
		return collected;
	
	}
	
	/**
     * getName returns the name of the treasure
     */
	public TreasureEnum getName() {
		
		return name;
		
	}
	
	/**
     * collectTreasure sets the collected status of the treasure to true
     */
	public void collectTreasure() {
		
		collected = true;
		
	}
	
}
