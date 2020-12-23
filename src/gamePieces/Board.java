package gamePieces;

import enums.TileNameEnum;
import java.util.*;
import players.*;

public class Board {
	
    //===========================================================
    // Variable Setup
    //===========================================================
	private static Tile[][] board = new Tile[6][6];
	private static Board instance = null;
	
	//===========================================================
    // Get Instance of Singleton
    //===========================================================
    /**
     * getInstance method returns single instance of board.
     * @return instance singleton Board object.
     */
	public static Board getInstance() {
		if (instance == null)
			instance = new Board();
		return instance;
	}
	
	//===========================================================
    // Constructor
    //===========================================================
    /**
     * Board constructor. 
     * Sets up the board as array of tiles.
     */
	public Board() {
		for (int x = 0; x < 6; x++) {         // For each row in Board
            for (int y = 0; y < 6; y++) {     // For each column in row
            	board[x][y] = null;
            }
		}
	}
	
	//===========================================================
	// Methods
	//===========================================================
	
	/**
	 * findByName returns the location of the tile of a given name
	 * @param the name of the tile
	 * @return the coordinate of the tile
	 */
	public static Coordinate findByName(TileNameEnum name) {
		
		for (int x = 0; x < 6; x++) {	// looping through the whole board
			for (int y = 0; y < 6; y++) {
				
				if (board[x][y] != null) {
					
					if (board[x][y].getTileName() == name){
						
							// if the given tile matches the name given then return the location
						return new Coordinate(x, y);
						
					}
				}
			}
		}
		
			// condition for not finding anything
		System.out.println("Error: TileName " + name + " not found");
		return new Coordinate(-1, -1);
	}
	
	/**
	 * canMoveSimple checks if a pawn at a given location can move in the 4
	 * simple directions. Doesn't account for movement exceptions
	 * @param point is the location of the pawn
	 * @param direction is the direction of travel being checked 0 checks all directions
	 * @return whether a pawn can move in that direction
	 */
	public boolean canMoveSimple(Coordinate point, int direction) {
		
		if(!isTile(point)) { // Shouldn't be on invalid tile
			return false;
		}
		
		switch(direction) {
			case 0:
					// checks all 4 directions to see if it can move at all
				if (isTile(point.north()) && !Board.getTile(point.north()).getSinkStatus() ||
						isTile(point.south()) && !Board.getTile(point.south()).getSinkStatus() ||
						isTile(point.east()) && !Board.getTile(point.east()).getSinkStatus() ||
						isTile(point.west()) && !Board.getTile(point.west()).getSinkStatus())
					return true;
				break;
			case 1:
					// checking north
				if (isTile(point.north()) && !Board.getTile(point.north()).getSinkStatus())
					return true;
				break;
			case 2:
					// checking south
				if (isTile(point.south()) && !Board.getTile(point.south()).getSinkStatus())
					return true;
				break;
			case 3:
					// checking west
				if (isTile(point.west()) && !Board.getTile(point.west()).getSinkStatus())
					return true;
				break;
			case 4:
					// checking east
				if (isTile(point.east()) && !Board.getTile(point.east()).getSinkStatus())
					return true;
				break;
		}
			// if it get's out of the switch then it can't move
		return false;
	}
	
	/**
	 * canMoveDiagonal checks if a pawn at a given location can move in the 4
	 * diagonal directions. 
	 * @param point is the location of the pawn
	 * @param direction is the direction of travel being checked 0 checks all directions
	 * @return whether a pawn can move in that direction
	 */
	public boolean canMoveDiagonal(Coordinate point, int direction) {
		
		if(!isTile(point)) { // Shouldn't be on invalid tile
			return false;
		}
		
		switch(direction) {
			case 0:
					// checks all 4 directions to see if it can move at all
				if (isTile(point.northEast()) && !Board.getTile(point.northEast()).getSinkStatus() ||
						isTile(point.northWest()) && !Board.getTile(point.northWest()).getSinkStatus() ||
						isTile(point.southEast()) && !Board.getTile(point.southEast()).getSinkStatus() ||
						isTile(point.southWest()) && !Board.getTile(point.southWest()).getSinkStatus())
					return true;
				System.out.println("She doesn't think you can move diagonally man");
				break;
			case 1:
					// checking north east
				if (isTile(point.northEast()) && !Board.getTile(point.northEast()).getSinkStatus())
					return true;
				break;
			case 2:
					// checking north west
				if (isTile(point.northWest()) && !Board.getTile(point.northWest()).getSinkStatus())
					return true;
				break;
			case 3:
					// checking south east
				if (isTile(point.southEast()) && !Board.getTile(point.southEast()).getSinkStatus())
					return true;
				break;
			case 4:
					// checking south west
				if (isTile(point.southWest()) && !Board.getTile(point.southWest()).getSinkStatus())
					return true;
				break;
		}
			// if it get's out of the switch then it can't move
		return false;
	}
	
	/**
	 * isTile checks if there's a tile at a given position
	 * @param the position being checked
	 * @return whether there is a tile at the point
	 */
	public boolean isTile(Coordinate point) {
		
		if (getTile(point) != null) 	
			return true;
		
		else 	
			return false;	
			
	}
	
	/**
	 * getTile returns the tile at a location or null if there is no tile
	 * @param the location being queried
	 * @return the tile at that location or null
	 */
	public static Tile getTile(Coordinate point) {
		
		if (point.getX() >= 0 && point.getX() < 6 &&
				point.getY() >= 0 && point.getY() < 6) {
			
				// if the coordinates are on the board then return the tile there 
			return board[point.getX()][point.getY()];
		
		}
		else {
			
			 	// otherwise return null
			return null;
			
		}
		
	}
	
	/**
	 * getBoard returns the board (i.se. the array of tiles)
	 * @return the array of tiles
	 */
	public Tile[][] getBoard() {
		
		return board;
		
	}

	/**
	 * printBoard prints the board for the players
	 * @param the current player to be highlighted, if null then no tile is highlighted
	 */
	public void printBoard(Pawn player) {
		
		int i;
		PlayerList list = PlayerList.getInstance();
		List<Integer> indices = new ArrayList<Integer>();
		
		for (i = 0; i < list.getNumPlayers(); i++) {
			indices.add(i+1);
		}
		
		for (int y = 5; y >= 0; y--) {         // looping backwards through the board
			for (int lines = 0; lines < 8; lines++) {
				for (int x = 0; x < 6; x++) {
					if (board[x][y] != null) {
						switch(lines) {
							case 0:
								if (player != null) {
										// if there's a current player highlight the TileName at their 
										// position with "***" on either side
									if (player.getPosition().getX() == x &&  player.getPosition().getY() == y) {
										
										System.out.print(centerString("*** " + board[x][y].getTileName().toString() + " ***"));
										break;
										
									}
								}
								 	// otherwise print the title as is
								System.out.print(centerString(board[x][y].getTileName().toString()));
								break;
							case 1:
									// printing the tile sunk status
								System.out.print("Sunk: " + leftAlignString(String.valueOf(board[x][y].getSinkStatus()), 24));
								break;
							case 2:
									// printing the tile flooded status
								System.out.print("Flooded: " + leftAlignString(String.valueOf(board[x][y].getFloodStatus()), 21));
								break;
							case 3:
									// printing the treasure on that tile
								System.out.print("Treasure: " + leftAlignString(board[x][y].getTreasure().toString(), 20));
								break;
							default:
									// printing the players on that tile
								for (i = 0; i<indices.size();i++) {
									if (list.getPlayer(indices.get(i)).getPosition().getX() == x && list.getPlayer(indices.get(i)).getPosition().getY() == y && indices.contains(indices.get(i))) {
										
										System.out.print("Player " + indices.get(i) + ": " + leftAlignString(list.getPlayer(indices.get(i)).getClass().getSimpleName(), 20));
										indices.remove(Integer.valueOf(indices.get(i)));
										i = -1;
										break;
										
									}
								}
								if (i == indices.size())
									System.out.print("..............................");
								break;
						}
					}
					else {
						System.out.print(centerString(" ")); // Leave invalid tiles blank
					}
				}
				System.out.print("\n");
			}
		}
	}
	
	/**
     * centerString aligns string to the center
     * @param s string to align
     * @return aligned string
     */
	public String centerString(String s) {
		
		int width = 30;
		int padSize = width - s.length();
		int padStart = s.length() + padSize/2;
		s = String.format("%" + padStart + "s", s);
		s = String.format("%-" + width + "s", s);
		return s;
		
	}
	
	/**
     * centerString aligns string to the left
     * @param s string to align
     * @return left aligned string
     */
	public String leftAlignString(String s, int width) {
		
		s = String.format("%-" + width + "s", s).replace(" ", ".");
		return s;
		
	}
	
}
