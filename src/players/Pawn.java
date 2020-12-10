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
	
	public boolean captureTreasure(Treasure treasure) {
		Stack<Card> checkCards = new Stack<Card>();
		for (int i = 0 ; i < 3 ; i++) {
			Card c = getCard(treasure.getName());
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
		
    	treasure.collectTreasure();
    	return true;

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
