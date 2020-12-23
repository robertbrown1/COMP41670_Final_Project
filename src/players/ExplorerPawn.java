package players;

import enums.TileNameEnum;
import gamePieces.Board;
import gamePieces.Coordinate;
import main.Game;
import observer.GameObserver;

/**
 * Singleton class for ExplorerPawn Extends the Abstract Class Pawn
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class ExplorerPawn extends Pawn {
	
	//===========================================================
	// Variable Setup
	//===========================================================
	private static ExplorerPawn instance = null;
	
	//===========================================================
	// Get Instance of Singleton
	//===========================================================
	/**
	 * gets the singleton instance of the ExplorerPawn object.
	 * @return the single ExplorerPawn object.
	 */
	public static ExplorerPawn getInstance() {
		
		if (instance == null)
			instance = new ExplorerPawn();
		
		return instance;
		
	}
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Calls the super constructor to create a pawn specific to the explorer
	 */
	public ExplorerPawn() {
		
		super(TileNameEnum.CopperGate);
		
	}
	
	/**
	 * movePawn asks the player which direction it wants to move and tries to move there
	 * accounts for the Explorer's movement exception
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
					
						// if you move the pawn then update the observer with the locations
					GameObserver.getInstance().updatePlayerLocations(PlayerList.getInstance().getAllPlayers());
					System.out.println("Pawn move successful");
					return true;
				
				}
				else {
						// otherwise repeat the loop
					System.out.println("Can't Move in this direction, please try again");
				}
			}
		}
		else if (this.canMove()) {	// condition for requiring the movement exception
			
			System.out.println("You're trapped! But the Explorer can swim diagonally to safety");
			
			for (;;) {
				
				System.out.println("Would you like to move NorthEast [1], NorthWest [2], SouthEast [3] or SouthWest [4]?");
			
				int direction = Game.getUserInput(1, 4); // Get user to pick an option
				
				if (Board.getInstance().canMoveDiagonal(this.position, direction)) { // Check if pawn can move in direction
					
					switch (direction) {
						case 1: // Move north east
							 this.setPosition(this.position.northEast());
							break;
						case 2: // Move north west
							this.setPosition(this.position.northWest());
							break;
						case 3: // Move south east
							this.setPosition(this.position.southEast());
							break;
						case 4: // Move south west
							this.setPosition(this.position.southWest());
							break;
							
					}
					
						// if you move the pawn then update the observer with the locations
					GameObserver.getInstance().updatePlayerLocations(PlayerList.getInstance().getAllPlayers());
					System.out.println("Pawn move successful");
					return true;
					
				}
				else {
						// otherwise repeat the loop
					System.out.println("Can't Move in this direction, please try again");
				}
			}
		}
		
		System.out.println("Error! Game should be over");
		return false;
		
	}
	
	/**
	 * canMove checks if the Explorer pawn can move at all
	 */
	public boolean canMove() {
		
		Coordinate point = this.getPosition();
		
		return Board.getInstance().canMoveSimple(point, 0) ||
				Board.getInstance().canMoveDiagonal(point, 0);
		
	}
	
}
