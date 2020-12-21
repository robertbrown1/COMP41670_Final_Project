package gamePieces;

import enums.TileNameEnum;
import java.util.*;
import players.*;

/**
 * Board singleton class.
 * A single board object is created and that object is called using the getInstance method.
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
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
		for (int x = 0; x < 6; x++) { // For all x coordinates
            for (int y = 0; y < 6; y++) { // For all y coordinates
            	board[x][y] = null; // Initialize as null
            }
		}
	}
	
	//===========================================================
    // Methods
    //===========================================================
	/**
     * findByName returns the coordinates of a particular tile
     * @param name the name of the tile
     * @return the coordinate of the tile
     */
	public static Coordinate findByName(TileNameEnum name) {
		for (int x = 0; x < 6; x++) { // For all x coordinates
			for (int y = 0; y < 6; y++) { // For all y coordinates
				if (board[x][y] != null) { // Ignore certain tiles near the corners of the board
					if (board[x][y].getTileName() == name){ // Tile is found
						return new Coordinate(x, y);
					}
				}
			}
		}
		System.out.println(name);
		return new Coordinate(-1, -1); // Return this if tile cannot be found
	}
	
	/**
     * canMove checks if a pawn can move in a given direction
     * @param point the coordinates of the tile that the pawn is on
     * @param direction the direction the pawn wants to move
     * @return true or false if the pawn can move
     */
	public boolean canMove(Coordinate point, int direction) {
		switch(direction) {
			case 1: // Move north
				if (isTile(point.north()) && Board.getTile(point.north()).getSinkStatus() == false)
					return true; // There is a tile north and it has not been sunk
				break;
			case 2: // Move south
				if (isTile(point.south()) && Board.getTile(point.south()).getSinkStatus() == false)
					return true; // There is a tile south and it has not been sunk
				break;
			case 3: // Move west
				if (isTile(point.west()) && Board.getTile(point.west()).getSinkStatus() == false)
					return true; // There is a tile west and it has not been sunk
				break;
			case 4: // Move east
				if (isTile(point.east()) && Board.getTile(point.east()).getSinkStatus() == false)
					return true; // There is a tile east and it has not been sunk
				break;
		}
		return false; // Can not move in the specified direction
	}
	
	/**
     * isTile checks if a coordinate is a tile
     * @param point the coordinate to check
     * @return true or false if the coordinate is a tile
     */
	public boolean isTile(Coordinate point) {
		
		if (getTile(point) != null) { // If tile is null then it is not a valid tile
			return true;
		}
		else {
			return false;		
		}
		
	}
	
	//===========================================================
    // Getters
    //===========================================================
	/**
     * getTile tries to return the tile at a coordinate
     * @param point the coordinate of the tile
     * @return tile at given coordinate
     */
	public static Tile getTile(Coordinate point) {
		
		if (point.getX() >= 0 && point.getX() < 6 &&
				point.getY() >= 0 && point.getY() < 6) { // x and y values are valid
			return board[point.getX()][point.getY()];
		}
		else {
			return null;
		}
		
	}
	
	/**
     * getBoard returns the board object
     */
	public Tile[][] getBoard() {
		return board;
	}
	
	//===========================================================
    // Printing the Board
    //===========================================================
	/**
     * Method for printing the board and important info about players and tiles
     */
	public void printBoard() {
		int i;
		PlayerList list = PlayerList.getInstance(); // Get list of players
		List<Integer> indices = new ArrayList<Integer>();
		for (i = 0; i < list.getNumPlayers(); i++) {
			indices.add(i+1); // Keeps track of what players have been printed already
		}
		for (int y = 5; y >= 0; y--) { // For each y coordinate
			for (int lines = 0; lines < 8; lines++) { // 7 lines per tile needed for all info
				for (int x = 0; x < 6; x++) { // For each x coordinate
					if (board[x][y] != null) { // Skip invalid tiles
						switch(lines) {
							case 0: // Print name of tile
								System.out.print(centerString(board[x][y].getTileName().toString()));
								break;
							case 1: // Print sink status of tile
								System.out.print("Sunk: " + leftAlignString(String.valueOf(board[x][y].getSinkStatus()), 24));
								break;
							case 2: // Print flood status of tile
								System.out.print("Flooded: " + leftAlignString(String.valueOf(board[x][y].getFloodStatus()), 21));
								break;
							case 3: // Print treasure associated with tile
								System.out.print("Treasure: " + leftAlignString(board[x][y].getTreasure().toString(), 20));
								break;
							default: // Print players on tile
								for (i = 0; i<indices.size();i++) {
									// Players position is the same as tile and hasn't been printed
									if (list.getPlayer(indices.get(i)).getPosition().equals(new Coordinate(x,y))
											&& indices.contains(indices.get(i))) {
										System.out.print("Player " + indices.get(i) + ": " + leftAlignString(list.getPlayer(indices.get(i)).getClass().getSimpleName(), 20));
										// Remove player index from list
										indices.remove(Integer.valueOf(indices.get(i)));
										i = -1;
										break;
									}
								}
								if (i == indices.size()) // No more players so print line of dots
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
		int width = 30; // Total size of string
		int padSize = width - s.length(); // Spaces to be padded
		int padStart = s.length() + padSize/2; // Place to start padding
		// Pad left and right of string
		s = String.format("%" + padStart + "s", s);
		s = String.format("%-" + width + "s", s);
		return s;
	}
	
	/**
     * leftAlignString aligns string to the left
     * @param s string to align
     * @param n number of dots to place after string
     * @return aligned string
     */
	public String leftAlignString(String s, int n) {
		int width = n; // Total size of padding
		s = String.format("%-" + width + "s", s).replace(" ", "."); // Pad end of string with dots
		return s;
	}
	
}
