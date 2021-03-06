package players;

import main.Game;
import java.util.*;
import enums.*;
import observer.GameObserver;
import gamePieces.*;
import cards.*;

/**
 * PlayerTurn class manages all of the options a player can make whilst 
 * they have a turn.
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class PlayerTurn {
	
	// ===========================================================
	// Setup Variables
	// ===========================================================
	private Pawn pawn;
	private Board board;
    private int actions;
    private PlayerList list;
    private TreasureDeck treasure;
    private WaterMeter meter;
    private FloodDeck flood;
	
	// ===========================================================
	// Constructor
	// ===========================================================
    /**
     * Constructor for a PlayerTurn.
     * @param player The player whose turn it is.
     */
    public PlayerTurn(Pawn player) {
    	
		this.pawn = player;
		this.board = Board.getInstance();
		this.list = PlayerList.getInstance();
		this.actions = 3;
		this.treasure = TreasureDeck.getInstance();
		this.meter = WaterMeter.getInstance();
		this.flood = FloodDeck.getInstance();
		
	}

	
	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * Main method for player turn
	 */
    public void doTurn() {
    	
    	this.actions = 3;
    	
        System.out.println("It is " + pawn.getClass().getSimpleName() + "'s turn");
        checkHand(); // Ensure that there are not more than 5 cards in hand
		while (actions > 0 && !GameObserver.getInstance().isGameOver()) { // While user can still take actions
			giveOptions(); // Print player options 
			int takeAction = Game.getUserInput(0, 10); // Get user to pick an option
			switch (takeAction) {
			    case 0: // End turn
			    	actions = 0;
			    	System.out.println("Player has decided to take no more actions");
			    	break;
			    case 1: // Try to move
			    	tryMovement();
			    	break;
			    case 2: // Try to shore up
			    	tryShoreUp();
			    	break;
			    case 3: // Try to give a card
			    	tryGiveCard();
			    	break;
			    case 4: // Try to capture a treasure
			    	tryCaptureTreasure();
			    	break;
			    case 5: // Try to use a helicopter lift card
			    	tryHelicopterLift();
			    	break;
			    case 6: // Try to use a sand bag card
			    	trySandbag();
			    	break;
			    case 7: // Print cards in players hand
			    	printHand();
			    	break;
			    case 8: // Print the board
			    	board.printBoard(pawn);
			    	break;
			    case 9: // Show the treasures that have been captured
			    	printCapturedTreasures();
			    	break;
			    case 10: // Special action to create various situations for testing, should be removed for deployment
			    	specialAction();
			    	break;
			    default:
			    	System.out.println("Error");
			}
		}
		if (!GameObserver.getInstance().isGameOver()) {
			
			drawTreasureCards(); // Draw cards from treasure deck
			drawFloodCards(); // Draw cards from flood deck
			
			System.out.println("Your turn has ended.\n");
		}
    }
    
    /**
	 * Print options for player
	 */
    private void giveOptions() {
    	
    	System.out.println("\nYou have " + actions + " actions left\nWhat action would you like to take?\nYour options are:");
    	System.out.println("[0] End turn");
    	System.out.println("[1] Move");
    	System.out.println("[2] Shore Up");
    	System.out.println("[3] Give Treasure Card");
    	System.out.println("[4] Capture Treasure");
    	System.out.println("[5] Use Helicopter Lift");
    	System.out.println("[6] Use Sandbag");
    	System.out.println("[7] Show Hand");
    	System.out.println("[8] Print Board");
    	System.out.println("[9] Show Captured Treasures");
    	
	}
    
    /**
	 * tryMovement moves the pawn if possible
	 */
    public void tryMovement() {
    	
		if (pawn.movePawn()) // Check if the user can move
			actions--;
		
	}
    
    /**
	 * tryShoreUp shores up a tile if possible
	 */
    public void tryShoreUp() {
    	
    	int shoreUps = 0;
    	int tileNum;
		//Create coordinate for surrounding tiles
    	Coordinate current = pawn.getPosition();
    	Coordinate north = current.north();
		Coordinate south = current.south();
		Coordinate west = current.west();
		Coordinate east = current.east();
    	Coordinate[] tiles = {current, north, south, west, east};
    	if (pawn instanceof EngineerPawn) { // Engineer can shore up 1 or 2 tiles for 1 action
    		System.out.println("How many tiles would you like to shore up? 1 or 2?");
    		tileNum = Game.getUserInput(1, 2); // Get user to pick option
    	}
    	else
    		tileNum = 1; // Other pawns can only shore up 1 tile per action
    	for (int i = 0; i < tileNum; i++) { // For each shore up attempt
	    	while ((Board.getTile(current).getFloodStatus() && !Board.getTile(current).getSinkStatus())
	    			|| (board.isTile(north) && Board.getTile(north).getFloodStatus() && !Board.getTile(north).getSinkStatus())
	    			|| (board.isTile(south) && Board.getTile(south).getFloodStatus() && !Board.getTile(south).getSinkStatus())
	    			|| (board.isTile(west) && Board.getTile(west).getFloodStatus() && !Board.getTile(west).getSinkStatus())
	    			|| (board.isTile(east) && Board.getTile(east).getFloodStatus() && !Board.getTile(east).getSinkStatus())) {
	    		// Can only shore up if surrounding tiles are valid and are flooded
	    		System.out.println("Which tile do you want to shore up? current tile [0], up [1], down [2], left [3] or right [4]?");
	    		int direction = Game.getUserInput(0, 4); // Get user to pick option
	    		if (!board.isTile(tiles[direction])) { // Check there is a tile in this direction
	    			System.out.println("There is no tile in this direction");
	    		}
	    		else if (pawn.shoreUp(tiles[direction])) { // Check if tile can be shored up
	    			System.out.println("Tile has been shored up");
	    			shoreUps++; // Increment number of tiles that have been shored up
	    			break;
	    		}
	    		else {
	    			System.out.println("Tile can not be shored up");
	    		}
	    	}
    	}
    	if (shoreUps == 0) { // No tile can be shored up
    		System.out.println("There are no adjacent tiles to shore up");
    	}
    	else
    		actions--;
    }
    
    /**
	 * tryGiveCard attempts to give a card to another pawn
	 */
    public void tryGiveCard() {
    	int playerNum = list.getPlayerIndex(pawn); // Initialize as number of current player
    	int cardNum;
    	if (pawn.getHand().size() == 0) { // Can't give a card because there are none on hand
    		System.out.println("No cards in hand to give away");
    		return;
    	}
    	while (playerNum == list.getPlayerIndex(pawn)) { // Until a different player is chosen
    		System.out.println("Which player would you like to give a card to?");
	    	for (int i = 1; i <= list.getNumPlayers(); i++) {
	    		if (i != playerNum) { // Print all players except player giving card away
	    			System.out.println(i + ": " + list.getPlayer(i).getClass().getSimpleName());
	    		}
	    	}
    		playerNum = Game.getUserInput(1, list.getNumPlayers()); // Get user to pick option
    	}
        System.out.println("Which card would you like to give?");
    	printHand(); // Show cards in hand
    	cardNum = Game.getUserInput(1, pawn.getHand().size()); // Get user to pick option
    	if (pawn.giveTreasureCard(pawn.getHand().get(cardNum-1), list.getPlayer(playerNum))) {
    		// Card can be given to player
    		System.out.println("Card has been given");
    		actions--;
    	}
    	else {
    		System.out.println("Could not give card because pawns are on different tiles");
    	}
    }
    
    /**
	 * tryCaptureTreasure is used to try collect a treasure
	 */
    public void tryCaptureTreasure() {
    	
    	TreasureEnum targetTreasure = Board.getTile(pawn.getPosition()).getTreasure();
    	
    	if(pawn.captureTreasure(targetTreasure)) {
			System.out.println("Treasure has been captured");
			actions--;
		}
    	else {
    		System.out.println("Cannot capture treasure from this tile");
    	}
    }
    
    /**
	 * tryHelicopterLift tries to use a helicopter lift card
	 */
    public void tryHelicopterLift() {
    	int playerNum;
    	Card checkCard = pawn.getCard(TreasureEnum.HelicopterLift); // Check if card in hand
    	if (checkCard == null) { // Card was not found
    		System.out.println("No Helicopter Lift card in hand");
    		return;
    	}
    	
    	// Update players locations and treasures collected
    	GameObserver.getInstance().updatePlayerLocations(list.getAllPlayers());
    	GameObserver.getInstance().updateTreasuresCollected(list.getTreasuresCollected());
    	if (GameObserver.getInstance().inPositionToWin()) {
    		
    		// if you use a helicopter lift card and the team is in the position to win then you've won the game
    		GameObserver.getInstance().winGame();
    		return;
    		
    	}
    	
    	System.out.println("Give the x coordinate of the tile you would like to move to");
		int x = Game.getUserInput(0, 5); // Get user to pick option
		System.out.println("Give the y coordinate of the tile you would like to move to");

		int y = Game.getUserInput(0, 5); // Get user to pick option
		if (!board.isTile(new Coordinate(x,y)) || Board.getTile(new Coordinate(x,y)).getSinkStatus()) {
			// Coordinate is not a tile or the tile is sunk
			System.out.println("Can not move to tile");
			pawn.getHand().add(checkCard); // Put card back in hand
			return;
		}
		System.out.println("How many players would you like to move? (including yourself)");
		int numPlayers = Game.getUserInput(1, list.getNumPlayers()); // Get user to pick option
		List<Pawn> players = new ArrayList<Pawn>();
		for (int i = 1; i <= numPlayers; i++) { // Until all players have been chosen
			System.out.println("Choose a player");
			for (int j = 1; j <= list.getNumPlayers(); j++) {
	    		if (!players.contains(list.getPlayer(j))) { // Print players that weren't picked
	    			System.out.println(j + ": " + list.getPlayer(j).getClass().getSimpleName());
	    		}
	    	}
			do {
				playerNum = Game.getUserInput(1, list.getNumPlayers()); // Get user to pick option
				if (players.contains(list.getPlayer(playerNum)))
					System.out.println("Pawn has already been selected");
			} while (players.contains(list.getPlayer(playerNum)));
			players.add(list.getPlayer(playerNum)); // Add to list of selected players
			players.get(i-1).setPosition(new Coordinate(x, y)); // Move pawn to tile
		}
		GameObserver.getInstance().updatePlayerLocations(PlayerList.getInstance().getAllPlayers());
		System.out.println("Pawns have been moved");
		treasure.addToDiscardPile(checkCard); // Put card in discard pile
    }
    
    /**
	 * trySandbag is used to check if a sandbag card can be used
	 */
    public void trySandbag() {
    	Card checkCard = pawn.getCard(TreasureEnum.SandBag); // Check if card is in hand
    	if (checkCard == null) { // Card can not be found
    		System.out.println("No Sandbag card in hand");
    		return;
    	}
    	System.out.println("Give the x coordinate of the tile you would like to shore up");
		int x = Game.getUserInput(0, 5); // Get user to pick option
		System.out.println("Give the y coordinate of the tile you would like to shore up");
		int y = Game.getUserInput(0, 5); // Get user to pick option
		Coordinate tile = new Coordinate(x, y); // Create coordinate from x and y values
		if (!board.isTile(tile)) { // Coordinate is not a valid tile
			System.out.println("Tile does not exist");
			pawn.getHand().add(checkCard); // Put card back in hand
		}
		else if (pawn.shoreUp(tile)) { // Tile can be shored up
			System.out.println("Tile has been shored up");
			treasure.addToDiscardPile(checkCard); // Put card in discard pile
		}
		else {
			System.out.println("Tile can not be shored up because it has sunk");
			pawn.getHand().add(checkCard); // Put card back in hand
		}
    }
    
    /**
	 * drawTreasureCards draws cards from treasure deck
	 */
    public void drawTreasureCards() {
    	boolean playersOnTile = false;
    	for (int i = 0; i < 2; i++) { // Must draw 2 cards
    		boolean cardGiven = false;
    		Card drawnCard = treasure.drawCard(); // Get card from top of deck
    		System.out.println("A " + drawnCard.getName() + "Card has been drawn");
    		if (drawnCard instanceof WaterRiseCard) {
    			treasure.addToDiscardPile(drawnCard); // Add card to discard pile
    			meter.increaseWaterLevel(); // Increase water level
    			System.out.println("Water level is now " + meter.getWaterLevel());
    			continue;
    		}
    		for (int num = 1; num <= list.getNumPlayers(); num++) { // For all players
    			if (num != list.getPlayerIndex(pawn)) {
    				playersOnTile = pawn.getPosition().equals(list.getPlayer(num).getPosition());
    				if (playersOnTile) // Check if another player is on the same tile
    					break;
    			}
    		}
    		if (!playersOnTile && !(pawn instanceof MessengerPawn)) { // Card can not be given
    			System.out.println("No other pawns on tile so card will be kept");
    			pawn.getHand().add(drawnCard); // Put card in hand
    			checkHand(); // Ensure that no more than 5 cards are in hand
    		}
    		else {
	    		System.out.println("Do you want to keep it [1] or give it away [2]?");
	    		int choice = Game.getUserInput(1, 2); // Get user to pick option
	    		if (choice == 1) { // Keep card
	    			pawn.getHand().add(drawnCard); // Add card to hand
	    			checkHand(); // Make sure at most 5 cards are in hand
	    		}
	    		else {
	    			while (!cardGiven) { // Until card is given to another player
	    				int playerNum = list.getPlayerIndex(pawn); // Initialize to current pawn
		    			System.out.println("Who would you like to give the card to?");
		    			for (int j = 1; j <= list.getNumPlayers(); j++) {
		    	    		if (j != playerNum) { // Only print other players
		    	    			System.out.println(j + ": " + list.getPlayer(j).getClass().getSimpleName());
		    	    		}
		    	    	}
		    	    	while (playerNum == list.getPlayerIndex(pawn)) { // Pick another player
		    	    		playerNum = Game.getUserInput(1, list.getNumPlayers()); // Get user input
		    	    	}
		    	    	if (pawn instanceof MessengerPawn) { // Messenger can be on another tile
		    	    		list.getPlayer(playerNum).getHand().add(drawnCard); // Give to player
		    	    		System.out.println("Card has been given");
		    	    		cardGiven = true;
		    	    	}
		    	    	else if (pawn.getPosition().equals(list.getPlayer(playerNum).getPosition())) {
		    	    		// Players are on the same tile
		    	    		list.getPlayer(playerNum).getHand().add(drawnCard); // Give to player
		    	    		System.out.println("Card has been given");
		    	    		cardGiven = true;
		    	    	}
		    	    	else 
		    	    		System.out.println("Please choose a pawn that is on the same tile");
	    			}
	    		}
    		}
    	}
    }
    
    /**
	 * drawFloodCards draws cards from flood deck
	 */
    public void drawFloodCards() {
    	for (int i = 0; i < meter.getWaterLevel(); i++) { // Water level determines number of cards
    		Card drawnCard = flood.drawCard(); // Draw card from flood deck
    		Coordinate point = Board.findByName((TileNameEnum)drawnCard.getName()); // Corresponding tile

    		if (!Board.getTile(point).getFloodStatus()) { // Tile is not flooded
    			Board.getTile(point).setFloodStatus(true); // Flood tile
    			System.out.println(drawnCard.getName() + " has been flooded");
    		}
    		else if (!Board.getTile(point).getSinkStatus()) { // Tile is not sunk
    			Board.getTile(point).setSinkStatus(true); // Sink tile
    			System.out.println(drawnCard.getName() + " has sunk");
    		}
    		else {
    			System.out.println(drawnCard.getName() + " has already been sunk");
    		}
    		flood.addToDiscardPile(drawnCard); // Add card to discard pile
    	}
    }
    
    /**
	 * checkHand ensures that 5 or less cards are in the pawns hand
	 */
    public void checkHand() {
    	while (pawn.getHand().size() > 5) { // More than 5 cards are in hand
    		System.out.println(pawn.getHand().size() + " cards in hand, must have at most 5. Please select card to remove");
    		printHand(); // Show hand
    		int cardNum = Game.getUserInput(1, pawn.getHand().size()); // Get user to pick option
    		Card chosenCard = pawn.getHand().get(cardNum-1); // Get chosen card
    		pawn.getHand().remove(cardNum-1); // Remove card from hand
    		treasure.addToDiscardPile(chosenCard); // Add card to discard pile
    	}
    }
    
    /**
	 * printHand is used to display the players hand
	 */
    public void printHand() {
    	for (int i = 0; i < pawn.getHand().size(); i++) { // For all cards in hand
    		System.out.println(String.valueOf(i+1) + ": " + pawn.getHand().get(i).getName());
    	}
    }
    
    /**
	 * printCapturedTreasure shows all treasured that have been collected
	 */
    public void printCapturedTreasures() {
    	if (list.getTreasuresCollected().size() == 0) // No treasures in list
    		System.out.println("No treasures have been collected");
    	else
    		System.out.println(list.getTreasuresCollected().toString()); // Print list
    }
    
 // Special action to create various situations for testing, should be removed for deployment  
    public void specialAction() {
    	
//    	PlayerList.getInstance().collectTreasure(TreasureEnum.EarthStone);
//    	PlayerList.getInstance().collectTreasure(TreasureEnum.FireCrystal);
//    	PlayerList.getInstance().collectTreasure(TreasureEnum.OceanChalice);
//    	PlayerList.getInstance().collectTreasure(TreasureEnum.WindStatue);
//    	
//    	for (int i = 1; i < PlayerList.getInstance().getNumPlayers()-1; i++) {
//    		
//    		PlayerList.getInstance().getAllPlayers().get(i).setPosition(Board.findByName(TileNameEnum.FoolsLanding));
//    		PlayerList.getInstance().getAllPlayers().get(i).hand.add(new HelicopterLiftCard(TreasureEnum.HelicopterLift));
//    		
//    	}
//    	
//    	GameObserver.getInstance().updatePlayerLocations(PlayerList.getInstance().getAllPlayers());
    	
//    	Board.getTile(Board.findByName(TileNameEnum.TempleOfTheMoon)).setSinkStatus(true);
//    	Board.getTile(Board.findByName(TileNameEnum.TempleOfTheSun)).setSinkStatus(true);
	
//    	Board.getTile(Board.findByName(TileNameEnum.FoolsLanding)).setSinkStatus(true);

//    	WaterMeter.getInstance().increaseWaterLevel();
//    	WaterMeter.getInstance().increaseWaterLevel();
//    	WaterMeter.getInstance().increaseWaterLevel();
//    	WaterMeter.getInstance().increaseWaterLevel();
    	
//    	Coordinate position = this.pawn.getPosition();
//    	Pawn temp = new DiverPawn();
//    	temp.setPosition(position);
//    	this.pawn = temp;
//    	PlayerList.getInstance().setPlayer(1, temp);
//    	
//    	if (Board.getInstance().isTile(position.north())) {
//    		Board.getTile(position.north()).setSinkStatus(true);
//    	}
//    	if (Board.getInstance().isTile(position.south())) {
//    		Board.getTile(position.south()).setSinkStatus(true);
//    	}
//		if (Board.getInstance().isTile(position.east())) {
//    		Board.getTile(position.east()).setSinkStatus(true);
//		}
//		if (Board.getInstance().isTile(position.west())) {
//    		Board.getTile(position.west()).setSinkStatus(true);
//		}
//		
//    	if (Board.getInstance().isTile(position.northEast())) {
//    		Board.getTile(position.northEast()).setSinkStatus(true);
//    	}
//    	if (Board.getInstance().isTile(position.northWest())) {
//    		Board.getTile(position.northWest()).setSinkStatus(true);
//    	}
//		if (Board.getInstance().isTile(position.southEast())) {
//    		Board.getTile(position.southEast()).setSinkStatus(true);
//		}
//		if (Board.getInstance().isTile(position.southWest())) {
//    		Board.getTile(position.southWest()).setSinkStatus(true);
//		}
    	
    	
    	actions--;
    	
    }

}