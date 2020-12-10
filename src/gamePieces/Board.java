package gamePieces;

import enums.TileNameEnum;

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

}
