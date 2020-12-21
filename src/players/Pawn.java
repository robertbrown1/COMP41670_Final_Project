package players;

import java.util.*;

import cards.*;
import enums.*;
import gamePieces.*;

public abstract class Pawn {

	protected List<Card> hand = new ArrayList<Card>();
	private Coordinate position;
	
	public Pawn(TileNameEnum startingTile) {
		position = Board.findByName(startingTile);
	}
	
	public boolean shoreUp(Coordinate point) {
		Tile floodedTile = Board.getTile(point);
		if (floodedTile == null || floodedTile.getSinkStatus() == true || floodedTile.getFloodStatus() == false) {
			return false;
		}
		else {
			floodedTile.setFloodStatus(false);
			return true;
		}
	}
	
	public boolean giveTreasureCard(Card treasureCard, Pawn p) {
		Card temp;
		if (position.equals(p.getPosition())) {
			temp = hand.get(hand.indexOf(treasureCard));
			hand.remove(treasureCard);
			p.getHand().add(temp);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean captureTreasure(TreasureEnum treasure) {
		PlayerList playerList = PlayerList.getInstance();
		Stack<Card> checkCards = new Stack<Card>();
		if (Board.getTile(position).getTreasure() != treasure) {
			return false;
		}
		for (int i = 0 ; i < 3 ; i++) {
			Card c = getCard(treasure);
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
    	playerList.collectTreasure(treasure);
    	Board.getTile(position).setTreasure(TreasureEnum.None);
    	return true;

	}
	
	public boolean movePawn(int direction) {
		Board gameBoard = Board.getInstance();
		if (gameBoard.canMove(position, direction)) {
			switch (direction) {
				case 1:
					position.setY(position.getY()+1);
					break;
				case 2:
					position.setY(position.getY()-1);
					break;
				case 3:
					position.setX(position.getX()-1);
					break;
				case 4:
					position.setX(position.getX()+1);
					break;
			}
			System.out.println("Pawn move successful");
			return true;
		}
		else {
			System.out.println("Pawn can not move in this direction");
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
	
	public void setPosition(Coordinate point) {
		this.position = point;
	}
	
}
