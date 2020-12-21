package players;

import main.Main;
import java.util.*;
import enums.*;
import observer.GameObserver;
import gamePieces.*;
import cards.*;

public class PlayerTurn {
	
		// ===========================================================
		// Setup Variables
		// ===========================================================
		private Pawn pawn;
		private Board board;
	    private int actions;
	    private PlayerList list;
//	    private EarthTreasure earth;
//	    private WindTreasure wind;
//	    private FireTreasure fire;
//	    private OceanTreasure ocean;
	    private TreasureDeck treasure;
	    private WaterMeter meter;
	    private FloodDeck flood;
		
		// ===========================================================
		// Constructor
		// ===========================================================
	    /**
	     * Constructor for a PlayerTurn.
	     * @param thisPlayer The player whose turn it is.
	     * @param inputScanner The one console input scanner.
	     */
	    public PlayerTurn(Pawn player) {
			this.pawn = player;
			this.board = Board.getInstance();
			this.list = PlayerList.getInstance();
//			this.earth = EarthTreasure.getInstance();
//			this.wind = WindTreasure.getInstance();
//			this.fire = FireTreasure.getInstance();
//			this.ocean = OceanTreasure.getInstance();
			this.actions = 3;
			this.treasure = TreasureDeck.getInstance();
			this.meter = WaterMeter.getInstance();
			this.flood = FloodDeck.getInstance();
		}

		
		// ===========================================================
		// Protected functions
		// ===========================================================
		/**
		 * Function that manages the players turn, giving them the 6
		 * possible options.
		 */
	    public void doTurn() {
	    	
	    	this.actions = 3;
	    	
	        System.out.println("It is " + pawn.getClass().getSimpleName() + "'s turn");
	        checkHand();

//	        pawn.getHand().add(new TreasureCard(TreasureEnum.EarthStone));
//	        pawn.getHand().add(new TreasureCard(TreasureEnum.EarthStone));
//	        pawn.getHand().add(new TreasureCard(TreasureEnum.EarthStone));
//	        pawn.getHand().add(new TreasureCard(TreasureEnum.EarthStone));
	        
	        //System.out.println(GameObserver.getInstance().isGameOver());
	        
	        while (this.actions > 0 && !GameObserver.getInstance().isGameOver()) {
					board.printBoard(this.pawn);

				giveOptions();
				int takeAction = getUserInput(0, 9);
				switch (takeAction) {
				    case 0:
				    	//GameObserver.getInstance().endGame(true);
				    	actions = 0;
				    	System.out.println("Player has decided to take no more actions");
				    	break;
				    case 1:
				    	tryMovement();
				    	break;
				    case 2:
				    	tryShoreUp();
				    	break;
				    case 3:
				    	tryGiveCard();
				    	break;
				    case 4:
				    	tryCaptureTreasure();
				    	break;
				    case 5:
				    	tryHelicopterLift();
				    	break;
				    case 6:
				    	trySandbag();
				    	break;
				    case 7:
				    	printHand();
				    	break;
				    case 8:
				    	board.printBoard(pawn);
				    	break;
				    case 9:
				    	//printCapturedTreasures();
				    	specialAction();
				    	break;
				    default:
				    	System.out.println("Error");
				}
			}
			drawTreasureCards();
			drawFloodCards();
			System.out.println("Your turn has ended.\n");
		}
	    
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
	    
	    public void tryMovement() {
	    	
	        System.out.println("Would you like to move up [1], down [2], left [3] or right [4]?");
			int direction = getUserInput(1, 4);
			if (pawn.movePawn(direction))
				actions--;
			
		}
	    
	    public void tryShoreUp() {
	    	
	    	int shoreUps = 0;
	    	int tileNum;
	    	
	    	Coordinate current = pawn.getPosition();
	    	Coordinate north = current.north();
			Coordinate south = current.south();
			Coordinate west = current.west();
			Coordinate east = current.east();
	    	Coordinate[] tiles = {current, north, south, west, east};
	    	if (pawn instanceof EngineerPawn) {
	    		System.out.println("How many tiles would you like to shore up? 1 or 2?");
	    		tileNum = getUserInput(1, 2);
	    	}
	    	else
	    		tileNum = 1;
	    	for (int i = 0; i < tileNum; i++) {
		    	while (Board.getTile(current).getFloodStatus() || (board.isTile(north) && Board.getTile(north).getFloodStatus()) ||
		    			(board.isTile(south) && Board.getTile(south).getFloodStatus()) || (board.isTile(west) && Board.getTile(west).getFloodStatus()) ||
		    			(board.isTile(east) && Board.getTile(east).getFloodStatus())) {
		    		System.out.println("Which tile do you want to shore up? current tile [0], up [1], down [2], left [3] or right [4]?");
		    		int direction = getUserInput(0, 4);
		    		if (!board.isTile(tiles[direction])) {
		    			System.out.println("There is no tile in this direction");
		    		}
		    		else if (pawn.shoreUp(tiles[direction])) {
		    			System.out.println("Tile has been shored up");
		    			shoreUps++;
		    			break;
		    		}
		    		else {
		    			System.out.println("Tile can not be shored up");
		    		}
		    	}
	    	}
	    	if (shoreUps == 0) {
	    		System.out.println("There are no adjacent tiles to shore up");
	    	}
	    	else
	    		actions--;
	    }
	    
	    public void tryGiveCard() {
	    	int playerNum = list.getPlayerIndex(pawn);
	    	int cardNum;
	    	if (pawn.getHand().size() == 0) {
	    		System.out.println("No cards in hand to give away");
	    		return;
	    	}
	    	while (playerNum == list.getPlayerIndex(pawn)) {
	    		System.out.println("Which player would you like to give a card to?");
		    	for (int i = 1; i <= list.getNumPlayers(); i++) {
		    		if (i != playerNum) {
		    			System.out.println(i + ": " + list.getPlayer(i).getClass().getSimpleName());
		    		}
		    	}
	    		playerNum = getUserInput(1, list.getNumPlayers());
	    	}
	        System.out.println("Which card would you like to give?");
	    	printHand();
	    	cardNum = getUserInput(1, pawn.getHand().size());
	    	if (pawn.giveTreasureCard(pawn.getHand().get(cardNum-1), list.getPlayer(playerNum))) {
	    		System.out.println("Card has been given");
	    		actions--;
	    	}
	    	else {
	    		System.out.println("Could not give card because pawns are on different tiles");
	    	}
	    }
	    
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
	    
	    public void tryHelicopterLift() {
	    	int playerNum;
	    	Card checkCard = pawn.getCard(TreasureEnum.HelicopterLift);
	    	if (checkCard == null) {
	    		System.out.println("No Helicopter Lift card in hand");
	    		return;
	    	}
	    	System.out.println("Give the x coordinate of the tile you would like to move to");
			int x = getUserInput(0, 5);
			System.out.println("Give the y coordinate of the tile you would like to move to");
			int y = getUserInput(0, 5);
			if (!board.isTile(new Coordinate(x,y)) || Board.getTile(new Coordinate(x,y)).getSinkStatus()) {
				System.out.println("Can not move to tile");
				pawn.getHand().add(checkCard);
				return;
			}
			System.out.println("How many players would you like to move? (including yourself)");
			int numPlayers = getUserInput(1, list.getNumPlayers());
			List<Pawn> players = new ArrayList<Pawn>();
			for (int i = 1; i <= numPlayers; i++) {
				System.out.println("Choose a player");
				for (int j = 1; j <= list.getNumPlayers(); j++) {
		    		if (!players.contains(list.getPlayer(j))) {
		    			System.out.println(j + ": " + list.getPlayer(j).getClass().getSimpleName());
		    		}
		    	}
				do {
					playerNum = getUserInput(1, list.getNumPlayers());
				} while (players.contains(list.getPlayer(playerNum)));
				players.add(list.getPlayer(playerNum));
				players.get(i-1).setPosition(new Coordinate(x, y));
			}
			System.out.println("Pawns have been moved");
			treasure.addToDiscardPile(checkCard);
	    }
	    
	    public void trySandbag() {
	    	Card checkCard = pawn.getCard(TreasureEnum.SandBag);
	    	if (checkCard == null) {
	    		System.out.println("No Sandbag card in hand");
	    		return;
	    	}
	    	System.out.println("Give the x coordinate of the tile you would like to shore up");
			int x = getUserInput(0, 5);
			System.out.println("Give the y coordinate of the tile you would like to shore up");
			int y = getUserInput(0, 5);
			Coordinate tile = new Coordinate(x, y);
    		if (!board.isTile(tile)) {
    			System.out.println("Tile does not exist");
    			pawn.getHand().add(checkCard);
    		}
    		else if (pawn.shoreUp(tile)) {
    			System.out.println("Tile has been shored up");
    			treasure.addToDiscardPile(checkCard);
    		}
    		else {
    			System.out.println("Tile can not be shored up because it has sunk");
    			pawn.getHand().add(checkCard);
    		}
	    }
	    
	    public void drawTreasureCards() {
	    	boolean playersOnTile = false;
	    	for (int i = 0; i < 2; i++) {
	    		boolean cardGiven = false;
	    		Card drawnCard = treasure.drawCard();
	    		System.out.println("A " + drawnCard.getName() + "Card has been drawn");
	    		if (drawnCard instanceof WaterRiseCard) {
	    			treasure.addToDiscardPile(drawnCard);
	    			meter.increaseWaterLevel();
	    			System.out.println("Water level is now " + meter.getWaterLevel());
	    			continue;
	    		}
	    		for (int num = 1; num <= list.getNumPlayers(); num++) {
	    			if (num != list.getPlayerIndex(pawn)) {
	    				playersOnTile = pawn.getPosition().equals(list.getPlayer(num).getPosition());
	    				if (playersOnTile)
	    					break;
	    			}
	    		}
	    		if (!playersOnTile && !(pawn instanceof MessengerPawn)) {
	    			System.out.println("No other pawns on tile so card will be kept");
	    			pawn.getHand().add(drawnCard);
	    			checkHand();
	    		}
	    		else {
		    		System.out.println("Do you want to keep it [1] or give it away [2]?");
		    		int choice = getUserInput(1, 2);
		    		if (choice == 1) {
		    			pawn.getHand().add(drawnCard);
		    			checkHand();
		    		}
		    		else {
		    			while (!cardGiven) {
		    				int playerNum = list.getPlayerIndex(pawn);
			    			System.out.println("Who would you like to give the card to?");
			    			for (int j = 1; j <= list.getNumPlayers(); j++) {
			    	    		if (j != playerNum) {
			    	    			System.out.println(j + ": " + list.getPlayer(j).getClass().getSimpleName());
			    	    		}
			    	    	}
			    	    	while (playerNum == list.getPlayerIndex(pawn)) {
			    	    		playerNum = getUserInput(1, list.getNumPlayers());
			    	    	}
			    	    	if (pawn instanceof MessengerPawn) {
			    	    		list.getPlayer(playerNum).getHand().add(drawnCard);
			    	    		System.out.println("Card has been given");
			    	    		cardGiven = true;
			    	    	}
			    	    	else if (pawn.getPosition().equals(list.getPlayer(playerNum).getPosition())) {
			    	    		list.getPlayer(playerNum).getHand().add(drawnCard);
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
	    
	    public void drawFloodCards() {
	    	for (int i = 0; i < meter.getWaterLevel(); i++) {
	    		Card drawnCard = flood.drawCard();
	    		Coordinate point = Board.findByName((TileNameEnum)drawnCard.getName());

	    		if (!Board.getTile(point).getFloodStatus()) {
	    			Board.getTile(point).setFloodStatus(true);
	    			System.out.println(drawnCard.getName() + " has been flooded");
	    		}
	    		else if (!Board.getTile(point).getSinkStatus()) {
	    			Board.getTile(point).setSinkStatus(true);
	    			System.out.println(drawnCard.getName() + " has sunk");
	    		}
	    		else {
	    			System.out.println(drawnCard.getName() + " has already been sunk");
	    		}
	    		flood.addToDiscardPile(drawnCard);
	    	}
	    }
	    
	    public void checkHand() {
	    	while (pawn.getHand().size() > 5) {
	    		System.out.println(pawn.getHand().size() + " cards in hand, must have at most 5. Please select card to remove");
	    		printHand();
	    		int cardNum = getUserInput(1, pawn.getHand().size());
	    		Card chosenCard = pawn.getHand().get(cardNum-1);
	    		pawn.getHand().remove(cardNum-1);
	    		treasure.addToDiscardPile(chosenCard);
	    	}
	    }
	    
	    public void printHand() {
	    	for (int i = 0; i < pawn.getHand().size(); i++) {
	    		System.out.println(String.valueOf(i+1) + ": " + pawn.getHand().get(i).getName());
	    	}
	    }
	    
	    public void printCapturedTreasures() {
	    	if (list.getTreasuresCollected().size() == 0)
	    		System.out.println("No treasures have been collected");
	    	else
	    		System.out.println(list.getTreasuresCollected().toString());
	    }
	    
	    public int getUserInput(int minVal, int maxVal) {
	    	int userInput = 0;
		    boolean validInput = false;
			while (!validInput) {
				String userString = Main.sc.nextLine();
				try {userInput = Integer.parseInt(userString);} 
		        catch (NumberFormatException e) {continue;}
					
				if ((userInput >= minVal) && (userInput <= maxVal)) {
					validInput = true;
				}
				if (!validInput) {
					System.out.println("Please enter a valid input");
				}
			}
			return userInput;
			
	    }
	    
	    public void specialAction() {
	    	
	    	Board.getTile(Board.findByName(TileNameEnum.TempleOfTheMoon)).setSinkStatus(true);
	    	Board.getTile(Board.findByName(TileNameEnum.TempleOfTheSun)).setSinkStatus(true);;
	    	actions--;
	    	
	    }
	    
}