package setup;

import gamePieces.*;
import java.util.*;
import enums.TileNameEnum;

/**
 * Class to handle all aspects of setting up the Board
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class BoardSetup {
	
	//===========================================================
	// Setup Variables
	//===========================================================
	private Board setupBoard;
	private List<TileNameEnum> tileNames = Arrays.asList(TileNameEnum.values());
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Constructor for the BoardSetup Class
	 */
	public BoardSetup() {
		this.setupBoard = Board.getInstance();
	}
	
	//===========================================================
	// Methods
	//===========================================================
	/**
	 * Randomly assign tiles to board
	 */
	public void assignTiles() {
		Collections.shuffle(tileNames); // Shuffle the list of tile names
		Iterator<TileNameEnum> itr = tileNames.iterator();
		for (int x = 0; x < 6; x++) { // For each x coordinate
			for (int y = 0; y < 6; y++) { // For each y coordinate
				if (Math.abs(2.5-x) + Math.abs(2.5-y) < 4) { // Tile can be placed at this coordinate
					setupBoard.getBoard()[x][y] = new Tile(itr.next()); // Assign tile
				}
			}
		}
	}	
}
