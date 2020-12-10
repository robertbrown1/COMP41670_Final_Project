package setup;

import gamePieces.*;
import java.util.*;
import enums.TileNameEnum;

public class BoardSetup {
	
	private Board setupBoard;
	private List<TileNameEnum> tileNames = Arrays.asList(TileNameEnum.values());
	
	public BoardSetup() {
	    // Get first instance of Board and extract dimensions
		this.setupBoard = Board.getInstance();
	}
	
	public void assignTiles() {
		Collections.shuffle(tileNames);
		Iterator<TileNameEnum> itr = tileNames.iterator();
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 6; y++) {
				if (Math.abs(2.5-x)+Math.abs(2.5-y)<4) {
					setupBoard.getBoard()[x][y] = new Tile(itr.next());
				}
			}
		}
	}	
}
