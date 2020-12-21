package gamePieces;

import enums.*;

/**
 * Class for Tile on game board
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class Tile {
	
	//===========================================================
    // Variable Setup
    //===========================================================
	private boolean flooded;
	private boolean sunk;
	private TileNameEnum name;
	private TreasureEnum treasure;
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Initializes the flood status, sink status, name and treasure of the tile
	 */
	public Tile(TileNameEnum name) {
		
		this.flooded = false;
		this.sunk = false;
		this.name = name;
		
		switch(this.name)
	    {
		    case TempleOfTheMoon: // Earth treasure on this tile
		    	this.treasure = TreasureEnum.EarthStone;
		    	break;
		    case TempleOfTheSun: // Earth treasure on this tile
		    	this.treasure = TreasureEnum.EarthStone;
		    	break;
		    case WhisperingGarden: // Wind treasure on this tile
		    	this.treasure = TreasureEnum.WindStatue;
		    	break;
		    case HowlingGarden: // Wind treasure on this tile
		    	this.treasure = TreasureEnum.WindStatue;
		    	break;
		    case CaveOfEmbers: // Fire treasure on this tile
		    	this.treasure = TreasureEnum.FireCrystal;
		    	break;
		    case CaveOfShadows: // Fire treasure on this tile
		    	this.treasure = TreasureEnum.FireCrystal;
		    	break;
		    case CoralPalace: // Ocean treasure on this tile
		    	this.treasure = TreasureEnum.OceanChalice;
		    	break;
		    case TidalPalace: // Ocean treasure on this tile
		    	this.treasure = TreasureEnum.OceanChalice;
		    	break;
		    default:
		    	this.treasure = TreasureEnum.None;
		    	break;
	    }	
		
	}
	
	//===========================================================
    // Getters and Setters
    //===========================================================
	/**
     * getTileName returns the name of the tile
     */
	public TileNameEnum getTileName() {
		return this.name;
	}

	/**
     * getSinkStatus returns the sink status
     */
	public boolean getSinkStatus() {
		return this.sunk;
	}
	
	/**
     * getFloodStatus returns the flood status
     */
	public boolean getFloodStatus() {
		return this.flooded;
	}
	
	/**
     * getTreasure returns the treasure
     */
	public TreasureEnum getTreasure() {
		return this.treasure;
	}
	
	/**
     * setFloodStatus sets the flood status
     */
	public void setFloodStatus(boolean status) {
		this.flooded = status;
	}
	
	/**
     * setSinkStatus sets the sink status
     */
	public void setSinkStatus(boolean status) {
		this.sunk = status;
	}
	
	/**
     * setTreasure sets the treasure
     */
	public void setTreasure(TreasureEnum treasure) {
		this.treasure = treasure;
	}
	
}
