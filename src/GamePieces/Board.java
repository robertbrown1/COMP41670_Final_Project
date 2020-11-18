package GamePieces;

import java.util.*;

import Enums.TileName;

public class Board {
	
	private Tile[][] board = new Tile[6][6];
	private List<TileName> tileNames = Arrays.asList(TileName.values());
	
	public Board() {
		
		Collections.shuffle(tileNames);
		Iterator<TileName> itr = tileNames.iterator();
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 6; y++) {
				if (Math.abs(2.5-x)+Math.abs(2.5-y)<4) {
					board[x][y] = new Tile(itr.next());
				}
			}
		}
	}
	
	public static Coordinate findByName(TileName name) {
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 6; y++) {
				if (board[x][y] != null) {
					if (board[x][y].getTileName() == name){
						return new Coordinate(x, y);
					}
				}
			}
		}
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
	
	public Tile getTile(Coordinate point) {
		
		return this.board[point.getX()][point.getY()];
		
	}

}
