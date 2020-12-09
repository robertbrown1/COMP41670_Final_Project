package players;

import java.util.*;

import cards.*;
import enums.*;
import gamePieces.*;

public abstract class Pawn {

	private List<Card> hand = new ArrayList<Card>();
	private Coordinate position;
	
	public Pawn(TileNameEnum startingTile) {
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
	
	public boolean giveTreasureCard(Card treasureCard, Pawn p) {
		if (position.equals(p.getPosition())) {
			p.getHand().add(treasureCard);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean captureTreasure(TreasureEnum treasureName) {
		Stack<Card> checkCards = new Stack<Card>();
		for (int i = 0 ; i < 3 ; i++) {
			Card c = getCard(treasureName);
			if (c == null) {
				while(!checkCards.isEmpty()) {
					hand.add(checkCards.pop());
				}
				return false;
			}
			else {
				checkCards.push(c);
			}
		}
		while(!checkCards.isEmpty()) {
			TreasureDeck.getInstance().addToDiscardPile(checkCards.pop());
		}
		switch(treasureName)
	    {
		    case EarthStone:
		    	EarthTreasure.getInstance().collectTreasure();
		    	return true;
		    case WindStatue:
		    	WindTreasure.getInstance().collectTreasure();
		    	return true;
		    case FireCrystal:
		    	FireTreasure.getInstance().collectTreasure();
		    	return true;
		    case OceanChalice:
		    	OceanTreasure.getInstance().collectTreasure();
		    	return true;
		    default:
		    	return false;
	    }
	}
	
	public Card getCard(TreasureEnum card) {
		for (int i = 0 ; i < hand.size() ; i++) {
			if (hand.get(i).getName() == card) {
				return hand.remove(i);
			}
		}
		return null;
    }
	
	public List<Card> getHand(){
		return this.hand;
	}

	public Coordinate getPosition() {
		return this.position;
	}
	
}
