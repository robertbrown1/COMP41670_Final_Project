package players;

import java.util.*;

import cards.*;
import enums.*;
import gamePieces.Board;
import gamePieces.Coordinate;
import gamePieces.Tile;

public abstract class Pawn {

	private List<Card> hand = new ArrayList<Card>();
	private Coordinate position;
	
	public Pawn(TileName startingTile) {
		position = Board.findByName(startingTile);
	}
	
	public boolean shoreUp(Coordinate point) {
		Tile floodedTile = Board.getTile(point);
		if (floodedTile.getSinkStatus() == false) {
			floodedTile.setFloodStatus(false);
			return true;
		}
		else {
			return false;
		}
	}
	
	public List<Card> getHand(){
		return this.hand;
	}

	public Coordinate getPosition() {
		return this.position;
	}
	
}
