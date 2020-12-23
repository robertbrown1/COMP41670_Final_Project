package players;

import enums.TileNameEnum;
import gamePieces.Board;
import gamePieces.Coordinate;
import gamePieces.Tile;
import main.Game;
import observer.GameObserver;

/**
 * Singleton class for PilotPawn Extends the Abstract Class Pawn
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class PilotPawn extends Pawn {
	
	//===========================================================
	// Variable Setup
	//===========================================================
	private static PilotPawn instance = null;
	
	//===========================================================
	// Get Instance of Singleton
	//===========================================================
	/**
	 * gets the singleton instance of the PilotPawn object.
	 * @return the single PilotPawn object.
	 */
	public static PilotPawn getInstance() {
		if (instance == null)
			instance = new PilotPawn();
		return instance;
	}
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Calls the super constructor to create a pawn specific to the pilot
	 */
	public PilotPawn() {
		super(TileNameEnum.FoolsLanding);
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
			
			System.out.println("You're trapped! But the Pilot can escape to any tile");
			
			for (;;) {
				
					// user input
				System.out.println("Give the x coordinate of the tile you would like to move to");
				int x = Game.getUserInput(0, 5); // Get user to pick option
				System.out.println("Give the y coordinate of the tile you would like to move to");
				int y = Game.getUserInput(0, 5); // Get user to pick option
				
				if (Board.getInstance().isTile(new Coordinate(x,y)) && !Board.getTile(new Coordinate(x,y)).getSinkStatus()) {
					
					this.setPosition(new Coordinate(x, y));
					GameObserver.getInstance().updatePlayerLocations(PlayerList.getInstance().getAllPlayers());
					System.out.println("Moved Successfully");
					return true;
					
				}
				else {					
					System.out.println("Can't Move in this direction, please try again");	
				}
			}
		}
		
		System.out.println("Error! Game should be over");
		return false;
		
	}
	
	/**
	 * canMove checks if the Pilot pawn can move at all
	 */
	public boolean canMove() {
		
		Coordinate point = this.getPosition();
		
		for (int x = 0; x < 6; x++) {	// looping through all the tiles
			for (int y = 0; y < 6; y++) {
				
				Tile tile = Board.getTile(new Coordinate(x, y)); 
				
				if (tile != null) {
					
					if (!tile.getSinkStatus() && new Coordinate(x, y) != point) {
							
							// if there's a single tile not sunk then the pilot can move
						return true;
						
					}
				}
			}
		}
			// if there are no eligible tiles then the pawn can't move
		return false;
		
	}
	
}
