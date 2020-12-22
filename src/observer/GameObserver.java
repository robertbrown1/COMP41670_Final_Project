package observer;

import java.util.ArrayList;
import java.util.List;

import enums.TileNameEnum;
import enums.TreasureEnum;
import gamePieces.Board;
import gamePieces.Coordinate;
import gamePieces.Tile;
import players.Pawn;

public class GameObserver {
	
	private boolean gameWon;
	private static GameObserver instance = null;
	private List<Pawn> playerList;
	private List<Coordinate> playerLocations;
    private List<TreasureEnum> treasuresCollected;
    private int waterLevel;
	
	
	public static GameObserver getInstance() {
		if (instance == null)
			instance = new GameObserver();
		return instance;
	}
	
	public GameObserver() {

		this.gameWon = false;
		
	}
	
	public void winGame() {
		
		this.gameWon = true;
		
	}
	
	public boolean isGameOver() {
		
		return this.isGameWon() || this.isGameLost();
		
	}
	
	public boolean isGameLost() {
		
		// condition for FoolsLanding Sunk
		if (Board.getTile(Board.findByName(TileNameEnum.FoolsLanding)).getSinkStatus())
			return true;
		
		// condition for water level reaching 5
		if (this.waterLevel > 4)
			return true;
		
		// condition for lost treasures
		if (this.checkTreasureLost())
			return true;
		
		// condition for player trapped
		if (this.checkPlayerTrapped()) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean isGameWon() {
		
		return this.gameWon;
		
	}
	
	public void updatePlayerLocations(List<Pawn> newPlayerList) {
		
		List<Coordinate> temp = new ArrayList<Coordinate>();
		this.playerList = newPlayerList;
		for (int i = 0; i < newPlayerList.size(); i++) {
			temp.add(newPlayerList.get(i).getPosition());
		}
		this.playerLocations = temp;
		//System.out.println("Updated");
	}
	
	public void updateWaterLevel(int newWaterLevel) {
		
		this.waterLevel = newWaterLevel;
		
	}
	
	public boolean checkTreasureLost() {
		
		List<TreasureEnum> treasuresAvailible = new ArrayList<TreasureEnum>();
//		List<TreasureEnum> fullTreasureList = Arrays.asList(TreasureEnum.values());
//		Iterator<TreasureEnum> itr = fullTreasureList.iterator();
		
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 6; y++) {
				
				Tile tile = Board.getTile(new Coordinate(x, y)); 
				if (tile != null) {
					if (!tile.getSinkStatus() && tile.getTreasure() != TreasureEnum.None && !treasuresAvailible.contains(tile.getTreasure())) {
						
						treasuresAvailible.add(tile.getTreasure());
						
					}
				}
			}
		}
		
		if (this.treasuresCollected != null) {
			treasuresAvailible.addAll(this.treasuresCollected);
		}
		
//		System.out.println(treasuresAvailible);
//		
//		System.out.println("TreasureLost: " + !(treasuresAvailible.contains(TreasureEnum.EarthStone) ||
//				treasuresAvailible.contains(TreasureEnum.FireCrystal) ||
//				treasuresAvailible.contains(TreasureEnum.OceanChalice) ||
//				treasuresAvailible.contains(TreasureEnum.WindStatue)));
//
//		System.out.println(treasuresAvailible.contains(TreasureEnum.EarthStone));
//		System.out.println(treasuresAvailible.contains(TreasureEnum.FireCrystal));
//		System.out.println(treasuresAvailible.contains(TreasureEnum.OceanChalice));
//		System.out.println(treasuresAvailible.contains(TreasureEnum.WindStatue));
		
		return !(treasuresAvailible.contains(TreasureEnum.EarthStone) &&
				treasuresAvailible.contains(TreasureEnum.FireCrystal) &&
				treasuresAvailible.contains(TreasureEnum.OceanChalice) &&
				treasuresAvailible.contains(TreasureEnum.WindStatue));
		
	}
	
	public void updateTreasuresCollected(List<TreasureEnum> newTreasureList) {
		
		this.treasuresCollected = newTreasureList;		
		
	}
	
	public boolean inPositionToWin() {
		
		// condition for not having all the treasures collected
		if (!(this.treasuresCollected.contains(TreasureEnum.EarthStone) &&
				this.treasuresCollected.contains(TreasureEnum.FireCrystal) &&
				this.treasuresCollected.contains(TreasureEnum.OceanChalice) &&
				this.treasuresCollected.contains(TreasureEnum.WindStatue))) {
			return false;
		}
		
		// condition for not everyone on FoolsLanding
		for (int i = 0; i < this.playerLocations.size(); i++) {
			if (Board.getTile(this.playerLocations.get(i)).getTileName() != TileNameEnum.FoolsLanding) {
				return false;
			}
		}
		
		return true;
		
	}
	
	public boolean checkPlayerTrapped() {
		
		for (int i = 0; i < this.playerList.size(); i++) {
			
			if (!this.playerList.get(i).canMove()) {
				return true;
			}
		}
		
		return false;
		
	}
	
}
