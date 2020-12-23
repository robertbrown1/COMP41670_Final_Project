package players;

import java.util.ArrayList;
import java.util.List;

import enums.TileNameEnum;
import gamePieces.Board;
import gamePieces.Coordinate;
import gamePieces.Tile;
import main.Game;
import observer.GameObserver;

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
	
	/**
	 * movePawn moves the player in a given direction if possible
	 * @param direction the direction to move in
	 * @return true or false if the pawn has moved
	 */
	public boolean movePawn() {
		
		if (Board.getInstance().canMoveSimple(this.position, 0)) {
			
			for (;;) {
				
				System.out.println("Would you like to move up [1], down [2], left [3] or right [4]?");
			
				int direction = Game.getUserInput(1, 4); // Get user to pick an option
				
				if (Board.getInstance().canMoveSimple(this.position, direction)) { // Check if pawn can move in direction
					switch (direction) {
						case 1: // Move north
							this.setPosition(this.position.north());
							break;
						case 2: // Move South
							this.setPosition(this.position.south());
							break;
						case 3: // Move West
							this.setPosition(this.position.west());
							break;
						case 4: // Move East
							this.setPosition(this.position.east());
							break;
					}
					
					System.out.println("Pawn move successful");
					GameObserver.getInstance().updatePlayerLocations(PlayerList.getInstance().getAllPlayers());
					return true;
					
				}
				else {
					System.out.println("Can't Move in this direction, please try again");
				}
			}
		}
		else if (this.canMove()) {
			
			System.out.println("You're trapped! But the Diver can swim to safety on the nearest tile, where wolud you like to go?");
			
			Coordinate point = this.getPosition();
			int myX = point.getX();
			int myY = point.getY();
			int manhattanDistance = 2;	// the manhattan distance between the tiles
			List<TileNameEnum> nearestTiles = new ArrayList<TileNameEnum>();	// list of the nearest tiles
			
			do {		// loop compiles a list of the closest available tiles by manhattan distance
				for (int x = 0; x < 6; x++) {	// loop through every tile
					for (int y = 0; y < 6; y++) {
						if (Math.abs(x - myX) + Math.abs(y - myY) == manhattanDistance) {
							Tile tile = Board.getTile(new Coordinate(x, y)); 
							if (tile != null) {
								if (!tile.getSinkStatus()) {
										// if there's a tile at this distance add it to the list
									nearestTiles.add(tile.getTileName());
									
								}
							}
						}
					}
				}
					// if no tile is found, increment the distance and go again
				manhattanDistance++;
			} while (nearestTiles.isEmpty());
			
	    	for (int i = 0; i < nearestTiles.size(); i++) {
	    		
	    			// printing out the options
	    		System.out.println("[" + String.valueOf(i+1) + "]: " + nearestTiles.get(i));
	    		
	    	}
	    	
	    		// taking in the input and moving
	    	int tileSelected = Game.getUserInput(1, nearestTiles.size())-1;
			this.setPosition(Board.findByName(nearestTiles.get(tileSelected)));
			System.out.println("Pawn move successful");
			GameObserver.getInstance().updatePlayerLocations(PlayerList.getInstance().getAllPlayers());
			return true;
	    	
		}
		
		System.out.println("Error! Game should be over");
		return false;
		
	}
	
	/**
	 * canMove checks if the Diver pawn can move at all
	 */
	public boolean canMove() {
		
		Coordinate point = this.getPosition();
		
		for (int x = 0; x < 6; x++) {	// looping through all the tiles
			for (int y = 0; y < 6; y++) {
				
				Tile tile = Board.getTile(new Coordinate(x, y)); 
				
				if (tile != null) {
					
					if (!tile.getSinkStatus() && new Coordinate(x, y) != point) {
							
							// if there's a single tile not sunk then the diver can move
						return true;
						
					}
				}
			}
		}
			// if there are no eligible tiles then the pawn can't move
		return false;
		
	}
	
}
