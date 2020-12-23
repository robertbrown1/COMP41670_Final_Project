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
	
	public static Coordinate findByName(TileNameEnum name) {
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 6; y++) {
				if (board[x][y] != null) {
					if (board[x][y].getTileName() == name){
						return new Coordinate(x, y);
					}
				}
			}
		}
		System.out.println(name);
		return new Coordinate(-1, -1);
	}
	
	public boolean canMove(Coordinate point, int direction) {
		if (!isTile(point)) {
			return false;
		}
		switch(direction) {
			case 0:
			if (isTile(point.north()) && !Board.getTile(point.north()).getSinkStatus() ||
					isTile(point.south()) && !Board.getTile(point.south()).getSinkStatus() ||
					isTile(point.west()) && !Board.getTile(point.west()).getSinkStatus() ||
					isTile(point.east()) && !Board.getTile(point.east()).getSinkStatus())
					return true;
				break;
			case 1:
				if (isTile(point.north()) && !Board.getTile(point.north()).getSinkStatus())
					return true;
				break;
			case 2:
				if (isTile(point.south()) && !Board.getTile(point.south()).getSinkStatus())
					return true;
				break;
			case 3:
				if (isTile(point.west()) && !Board.getTile(point.west()).getSinkStatus())
					return true;
				break;
			case 4:
				if (isTile(point.east()) && !Board.getTile(point.east()).getSinkStatus())
					return true;
				break;
		}
		return false;
	}
	
	public boolean isTile(Coordinate point) {
		
		if (getTile(point) != null) {
			return true;
		}
		else {
			return false;		
		}
	}
	
	public static Tile getTile(Coordinate point) {
		
		if (point.getX() >= 0 && point.getX() < 6 &&
				point.getY() >= 0 && point.getY() < 6) {
			return board[point.getX()][point.getY()];
		}
		else {
			return null;
		}
		
	}
	
	public Tile[][] getBoard() {
		return board;
	}

	public void printBoard(Pawn player) {
		int i;
		PlayerList list = PlayerList.getInstance();
		List<Integer> indices = new ArrayList<Integer>();
		for (i = 0; i < list.getNumPlayers(); i++) {
			indices.add(i+1);
		}
		for (int y = 5; y >= 0; y--) {         // For each row in Board
			for (int lines = 0; lines < 8; lines++) {
				for (int x = 0; x < 6; x++) {
					if (board[x][y] != null) {
						switch(lines) {
							case 0:
								if (player != null) {
									if (player.getPosition().getX() == x &&  player.getPosition().getY() == y) {
										System.out.print(centerString("*** " + board[x][y].getTileName().toString() + " ***"));
										break;
									}
								}
								System.out.print(centerString(board[x][y].getTileName().toString()));
								break;
							case 1:
								System.out.print("Sunk: " + leftAlignString(String.valueOf(board[x][y].getSinkStatus()), 24));
								break;
							case 2:
								System.out.print("Flooded: " + leftAlignString(String.valueOf(board[x][y].getFloodStatus()), 21));
								break;
							case 3:
								System.out.print("Treasure: " + leftAlignString(board[x][y].getTreasure().toString(), 20));
								break;
							default:
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
	
	public String leftAlignString(String s, int n) {
		int width = n;
		s = String.format("%-" + width + "s", s).replace(" ", ".");
		return s;
	}
	
}
