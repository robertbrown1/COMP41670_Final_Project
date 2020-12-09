package gamePieces;

import enums.*;

public class Tile {
	
	private boolean flooded;
	private boolean sunk;
	private TileNameEnum name;
	private TreasureEnum treasure;
	
	public Tile(TileNameEnum name) {
		
		this.flooded = false;
		this.sunk = false;
		this.name = name;
		
		switch(this.name)
	    {
		    case TempleOfTheMoon:
		    	this.treasure = TreasureEnum.EarthStone;
		    case TempleOfTheSun:
		    	this.treasure = TreasureEnum.EarthStone;
		    case WhisperingGarden:
		    	this.treasure = TreasureEnum.WindStatue;
		    case HowlingGarden:
		    	this.treasure = TreasureEnum.WindStatue;
		    case CaveOfEmbers:
		    	this.treasure = TreasureEnum.FireCrystal;
		    case CaveOfShadows:
		    	this.treasure = TreasureEnum.FireCrystal;
		    case CoralPalace:
		    	this.treasure = TreasureEnum.OceanChalice;
		    case TidalPalace:
		    	this.treasure = TreasureEnum.OceanChalice;
		    default:
		    	this.treasure = TreasureEnum.None;
	    }	
		
	}
	
	public TileNameEnum getTileName() {
		return this.name;
	}

	public boolean getSinkStatus() {
		return this.sunk;
	}
	
	public void setFloodStatus(boolean status) {
		this.flooded = status;
	}
	
}
