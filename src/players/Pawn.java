package players;

import java.util.*;

import cards.*;
import enums.*;
import gamePieces.*;
import main.Game;
import observer.GameObserver;

/**
 * Abstract Class for the Player pawns
 * Should be extended by DiverPawn, EngineerPawn, ExplorerPawn, MessengerPawn, NavigatorPawn,
 * and PilotPawn
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public abstract class Pawn {

	//===========================================================
	// Variable Setup
	//===========================================================
	protected List<Card> hand = new ArrayList<Card>();
	protected Coordinate position;
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Constructor for Pawn object.
	 * @param startingTile tile that the pawn starts on
	 */
	public Pawn(TileNameEnum startingTile) {
		position = Board.findByName(startingTile); // Find tile coordinate
	}
	
	//===========================================================
	// Methods
	//===========================================================
	/**
	 * shoreUp is used to unflood a tile
	 * @param point tile to shore up
	 * @return true or false if the tile has been shored up
	 */
	public boolean shoreUp(Coordinate point) {
		
		Tile floodedTile = Board.getTile(point); // Get tile at coordinate

		if (floodedTile == null || floodedTile.getSinkStatus() == true || floodedTile.getFloodStatus() == false) {
			return false; // Tile is invalid or is not flooded or has sank
		}
		else {
			floodedTile.setFloodStatus(false); // Shore up tile
			return true;
		}
	}
	
	/**
	 * giveTreasureCard is used to give a card to another player
	 * @param treasureCard card to give to other player
	 * @param player to give card to
	 * @return true or false if the card has been given
	 */
	public boolean giveTreasureCard(Card treasureCard, Pawn p) {
		Card temp;
		if (position.equals(p.getPosition())) { // Pawns must be on same tile
			temp = hand.get(hand.indexOf(treasureCard));
			hand.remove(treasureCard); // Remove card from hand
			p.getHand().add(temp); // Give card to other player
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * captureTreasure allows the player to capture a treasure
	 * @param treasure name of treasure to capture
	 * @return true or false if the treasure has been captured
	 */
	public boolean captureTreasure(TreasureEnum treasure) {
		PlayerList playerList = PlayerList.getInstance(); // Get list of players
		Stack<Card> checkCards = new Stack<Card>();
		
		if (Board.getTile(position).getTreasure() != treasure || treasure == TreasureEnum.None) {
			return false; // Treasure can't be collected from this tile
		}
		for (int i = 0 ; i <= 3 ; i++) { // Need 4 matching cards
			Card c = getCard(treasure); // Try to get matching card from hand
			if (c == null) { // Card could not be found
				while(!checkCards.isEmpty()) {
					hand.add(checkCards.pop()); // Put cards back in hand
				}
				return false; // Not enough cards to capture treasure
			}
			else {
				checkCards.push(c); // Store card in stack
			}
		}
		
		while(!checkCards.isEmpty()) { // Treasure can be captured so discard cards
			TreasureDeck.getInstance().addToDiscardPile(checkCards.pop());
		}
		
    	playerList.collectTreasure(treasure);
    	Board.getTile(position).setTreasure(TreasureEnum.None);
    	playerList.collectTreasure(treasure); // Capture treasure
		Board.getTile(position).setTreasure(TreasureEnum.None); // Remove treasure from tile
    	GameObserver.getInstance().updateTreasuresCollected(playerList.getTreasuresCollected());
    	return true;

	}
	
	/**
	 * movePawn moves the player in a given direction if possible
	 * @param direction the direction to move in
	 * @return true or false if the pawn has moved
	 */
	public boolean movePawn() {
		
		//if (gameBoard.canMoveSimple(this.position, 0)) {
		if (this.canMove()) {
			
			for (;;) {
				System.out.println("Would you like to move up [1], down [2], left [3] or right [4]?");
			
				int direction = Game.getUserInput(1, 4); // Get user to pick an option
				
				if (Board.getInstance().canMoveSimple(position, direction)) { // Check if pawn can move in direction
					switch (direction) {
						case 1: // Move north
							this.setPosition(this.position.north());
							break;
						case 2: // Move South
							this.setPosition(this.position.south());
							break;
						case 3: // Move West
							this.setPosition(this.position.west());
							break;
						case 4: // Move East
							this.setPosition(this.position.east());
							break;
					}
					System.out.println("Pawn move successful");
					GameObserver.getInstance().updatePlayerLocations(PlayerList.getInstance().getAllPlayers());
					return true;
				}
				else {
					System.out.println("Can't Move in this direction, please try again");
				}
			}
		}
		
		System.out.println("Error! Game should be over");
		return false;
		
	}
	
	
	public boolean canMove() {
		
		return Board.getInstance().canMoveSimple(this.position, 0);
		
	}
	
	//===========================================================
	// Getters and Setters
	//===========================================================
	/**
	 * getCard looks for a specific card in the pawns hand
	 * @param card the name of the card
	 * @return card if it is found
	 */
	public Card getCard(TreasureEnum card) {
		
		for (int i = 0 ; i < hand.size() ; i++) { // Look through hand
			
			if (hand.get(i).getName() == card) { // Found card
				
				return hand.remove(i); // Remove card from hand
				
			}
		}
		return null; // Return null if card can't be found
    }
	
	/**
	 * getHand returns the hand
	 */
	public List<Card> getHand(){
		
		return this.hand;
		
	}
	
	/**
	 * getPosition returns the pawns coordinate
	 */
	public Coordinate getPosition() {
		
		return this.position;
		
	}
	
	/**
	 * setPosition changes the pawns position
	 * @param point new coordinate of pawn
	 */
	public void setPosition(Coordinate point) {
		
		this.position = point;
		
	}
	
	
	
}
