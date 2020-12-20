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
		    	break;
		    case TempleOfTheSun:
		    	this.treasure = TreasureEnum.EarthStone;
		    	break;
		    case WhisperingGarden:
		    	this.treasure = TreasureEnum.WindStatue;
		    	break;
		    case HowlingGarden:
		    	this.treasure = TreasureEnum.WindStatue;
		    	break;
		    case CaveOfEmbers:
		    	this.treasure = TreasureEnum.FireCrystal;
		    	break;
		    case CaveOfShadows:
		    	this.treasure = TreasureEnum.FireCrystal;
		    	break;
		    case CoralPalace:
		    	this.treasure = TreasureEnum.OceanChalice;
		    	break;
		    case TidalPalace:
		    	this.treasure = TreasureEnum.OceanChalice;
		    	break;
		    default:
		    	this.treasure = TreasureEnum.None;
		    	break;
	    }	
		
	}
	
	public TileNameEnum getTileName() {
		return this.name;
	}

	public boolean getSinkStatus() {
		return this.sunk;
	}
	
	public boolean getFloodStatus() {
		return this.flooded;
	}
	
	public TreasureEnum getTreasure() {
		return this.treasure;
	}
	
	public void setFloodStatus(boolean status) {
		this.flooded = status;
	}
	
	public void setSinkStatus(boolean status) {
		this.sunk = status;
	}
	
	public void setTreasure(TreasureEnum treasure) {
		this.treasure = treasure;
	}
	
}
