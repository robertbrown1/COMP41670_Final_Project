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
	
	private int[] findByName(TileName name) {
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 6; y++) {
				if (board[x][y] != null) {
					if (board[x][y].getTileName() == name){
						return new int[] {x, y};
					}
				}
			}
		}
		return new int[] {-1};
	}
	
	public boolean canMove(TileName name){
		
		int[] coordinates =  findByName(name);
		
		
	}

}
