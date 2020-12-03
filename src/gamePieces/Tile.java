package gamePieces;

import enums.*;

public class Tile {
	
	private boolean flooded;
	private boolean sunk;
	private TileName name;
	private Treasure treasure;
	
	public Tile(TileName name) {
		
		this.flooded = false;
		this.sunk = false;
		this.name = name;
		
		switch(this.name)
	    {
		    case TempleOfTheMoon:
		    	this.treasure = Treasure.EarthStone;
		    case TempleOfTheSun:
		    	this.treasure = Treasure.EarthStone;
		    case WhisperingGarden:
		    	this.treasure = Treasure.WindStatue;
		    case HowlingGarden:
		    	this.treasure = Treasure.WindStatue;
		    case CaveOfEmbers:
		    	this.treasure = Treasure.FireCrystal;
		    case CaveOfShadows:
		    	this.treasure = Treasure.FireCrystal;
		    case CoralPalace:
		    	this.treasure = Treasure.OceanChalice;
		    case TidalPalace:
		    	this.treasure = Treasure.OceanChalice;
		    default:
		    	this.treasure = Treasure.None;
	    }	
		
	}
	
	public TileName getTileName() {
		return this.name;
	}

	public boolean getSinkStatus() {
		return sunk;
	}
	
	public void setFloodStatus(boolean status) {
		flooded = status;
	}
	
}
