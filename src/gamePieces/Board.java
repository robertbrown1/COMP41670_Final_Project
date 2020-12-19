package gamePieces;

import enums.TileNameEnum;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import players.*;

public class Board {
	
	private static Tile[][] board = new Tile[6][6];
	private static Board instance = null;
	
	public static Board getInstance() {
		if (instance == null)
			instance = new Board();
		return instance;
	}
	
	public Board() {
		for (int x = 0; x < 6; x++) {         // For each row in Board
            for (int y = 0; y < 6; y++) {     // For each column in row
            	board[x][y] = null;
            }
		}
//		Collections.shuffle(tileNames);
//		Iterator<TileName> itr = tileNames.iterator();
//		for (int x = 0; x < 6; x++) {
//			for (int y = 0; y < 6; y++) {
//				if (Math.abs(2.5-x)+Math.abs(2.5-y)<4) {
//					board[x][y] = new Tile(itr.next());
//				}
//			}
//		}
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
	
	public boolean canMove(Coordinate point) {
		if (isTile(point.up())
				|| isTile(point.down())
				|| isTile(point.left())
				|| isTile(point.right())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isTile(Coordinate point) {
		
		if (point.getX() >= 0 && point.getX() < 6 &&
				point.getY() >= 0 && point.getY() < 6) {
			if (getTile(point) != null) {
				return true;
			}
		}
		return false;		
	}
	
	public static Tile getTile(Coordinate point) {
		
		return board[point.getX()][point.getY()];
		
	}
	
	public Tile[][] getBoard() {
		return board;
	}

	public void printBoard() {
		int i;
		PlayerList list = PlayerList.getInstance();
		List<Integer> indices = new ArrayList<Integer>();
		for (i = 0; i < list.getNumPlayers(); i++) {
			indices.add(i+1);
		}
		for (int x = 0; x < 6; x++) {         // For each row in Board
			for (int lines = 0; lines < 8; lines++) {
				for (int y = 0; y < 6; y++) {
					//int playerNum = 1;
					if (board[x][y] != null) {
						switch(lines) {
							case 0:
								System.out.print(StringUtils.center(board[x][y].getTileName().toString(), 30));
								break;
							case 1:
								System.out.print(StringUtils.rightPad("Sunk: " + board[x][y].getSinkStatus(), 30, "."));
								break;
							case 2:
								System.out.print(StringUtils.rightPad("Flooded: " + board[x][y].getFloodStatus(), 30, "."));
								break;
							case 3:
								System.out.print(StringUtils.rightPad("Treasure: " + board[x][y].getTreasure(), 30, "."));
								break;
							default:
								for (i = 0; i<indices.size();i++) {
									if (list.getPlayer(indices.get(i)).getPosition().getX() == x && list.getPlayer(indices.get(i)).getPosition().getY() == y && indices.contains(indices.get(i))) {
										System.out.print(StringUtils.rightPad("Player " + indices.get(i) + ": " + list.getPlayer(indices.get(i)).getClass().getSimpleName(), 30, "."));
										indices.remove(Integer.valueOf(indices.get(i)));
										i = -1;
										break;
									}
								}
								if (i == indices.size())
									System.out.print("..............................");
								break;
								//while (playerNum <= list.getNumPlayers()) {
								//	if (list.getPlayer(playerNum).getPosition().getX() == x && list.getPlayer(playerNum).getPosition().getY() == y && indices.contains(playerNum)) {
								//		System.out.print(StringUtils.rightPad("Player " + playerNum + ": " + list.getPlayer(playerNum).getClass().getSimpleName(), 30, "."));
								//		indices.remove(Integer.valueOf(playerNum));
								//		break;
								//	}
								//	playerNum++;
								//}
								//if (playerNum > list.getNumPlayers())
								//	System.out.print("..............................");
								//break;
						}
					}
					else {
						//System.out.print("          ");
						System.out.print(StringUtils.center(" ", 30));
					}
				}
				System.out.print("\n");
			}
		}
	}
	
}
