package observer;

import java.util.ArrayList;
import java.util.List;

import enums.TileNameEnum;
import enums.TreasureEnum;
import gamePieces.Board;
import gamePieces.Coordinate;
import gamePieces.Tile;
import players.Pawn;

/**
 * GameObserver class acts as an observer and controller 
 * to end the game when the game is won or lost
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class GameObserver {
	
    //===========================================================
    // Variable Setup
    //===========================================================
	private boolean gameWon;	// has the game been won
	private static GameObserver instance = null;
	private List<Pawn> playerList;	// a list of the players in the game
	private List<Coordinate> playerLocations;	// list of the locations of each player
    private List<TreasureEnum> treasuresCollected; 	// list of the treasures collected by the team
    private int waterLevel;		// the water level of the game
	
	//===========================================================
    // Get Instance of Singleton
    //===========================================================
    /**
     * getInstance method returns single instance of GameObserver.
     * @return instance singleton GameObserver object.
     */
	public static GameObserver getInstance() {
		if (instance == null)
			instance = new GameObserver();
		return instance;
	}
	
	//===========================================================
    // Constructor
    //===========================================================
    /**
     * GameObserver constructor. 
     * Sets up the GameObserver with the game on
     */
	public GameObserver() {

		this.gameWon = false;
		
	}
	
	//===========================================================
	// Methods
	//===========================================================
	
	/**
	 * winGame allows the subject to end the game with a win
	 */
	public void winGame() {
		
		this.gameWon = true;	// setting the game as won
		
	}
	
	/**
	 * isGameOver checks if the game is over
	 */
	public boolean isGameOver() {
		
			// check if game is won or game is lost
		return this.isGameWon() || this.isGameLost();
		
	}
	
	/**
	 * isGameLost checks if the game has been lost
	 */
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
	
	/**
	 * isGameWon checks if the game has been won
	 */
	public boolean isGameWon() {
		
		return this.gameWon;
		
	}
	
	/**
	 * updatePlayerLocations allows the subject class to update the player locations
	 */
	public void updatePlayerLocations(List<Pawn> newPlayerList) {
		
		List<Coordinate> temp = new ArrayList<Coordinate>();	// temp variable
		this.playerList = newPlayerList;				// update the playerList
		
		for (int i = 0; i < newPlayerList.size(); i++) {	// looping through each player
			
			temp.add(newPlayerList.get(i).getPosition());	// add the player location to the temp list 
			
		}
		
		this.playerLocations = temp;	// update the playerLoction list
		
	}
	
	/**
	 * updateWaterLevel allows the subject class to update the water level of the game
	 */
	public void updateWaterLevel(int newWaterLevel) {
		
		this.waterLevel = newWaterLevel; // updating waterLevel
		
	}
	
	/**
	 * checkTreasureLost checks if any of the treasures have been lost from the 
	 * board before it's been collected
	 */
	public boolean checkTreasureLost() {
			
			// list of the treasures availible
		List<TreasureEnum> treasuresAvailible = new ArrayList<TreasureEnum>();
		
//		List<TreasureEnum> fullTreasureList = Arrays.asList(TreasureEnum.values());
//		Iterator<TreasureEnum> itr = fullTreasureList.iterator();
		
		for (int x = 0; x < 6; x++) {		// looping through the whole board
			for (int y = 0; y < 6; y++) {
				
				Tile tile = Board.getTile(new Coordinate(x, y)); 
				
				if (tile != null) {
					
					if (!tile.getSinkStatus() && tile.getTreasure() != TreasureEnum.None && !treasuresAvailible.contains(tile.getTreasure())) {
						
							// if there's a treasure on the tile add it to the list
						treasuresAvailible.add(tile.getTreasure());
						
					}
				}
			}
		}
		
		if (this.treasuresCollected != null) {
			
				// add the treasures collected to the list if there are any 
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
		
			// return true if any of the treasures aren't available
		return !(treasuresAvailible.contains(TreasureEnum.EarthStone) &&
				treasuresAvailible.contains(TreasureEnum.FireCrystal) &&
				treasuresAvailible.contains(TreasureEnum.OceanChalice) &&
				treasuresAvailible.contains(TreasureEnum.WindStatue));
		
	}
	
	/**
	 * updateTreasuresCollected allows the subject class to update the list of treasures collected
	 */
	public void updateTreasuresCollected(List<TreasureEnum> newTreasureList) {
		
		this.treasuresCollected = newTreasureList;	// updating
		
	}
	
	/**
	 * inPositionToWin checks if the team is in position to win
	 */
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
		
			// return true if the team is in position to win
		return true;
		
	}
	
	/**
	 * checkPlayerTrapped checks if there are any players who are trapped where they are
	 */
	public boolean checkPlayerTrapped() {
		
			// looping through the list of players
		for (int i = 0; i < this.playerList.size(); i++) {
			
			if (!this.playerList.get(i).canMove()) {
				
					// if anyone can't move return true
				return true;
			}
		}
		
			// otherwise everyone can still move
		return false;
		
	}
	
}
