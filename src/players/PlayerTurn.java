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
	    private EarthTreasure earth;
	    private WindTreasure wind;
	    private FireTreasure fire;
	    private OceanTreasure ocean;
	    private boolean capturedTreasure;
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
			this.earth = EarthTreasure.getInstance();
			this.wind = WindTreasure.getInstance();
			this.fire = FireTreasure.getInstance();
			this.ocean = OceanTreasure.getInstance();
			this.capturedTreasure = false;
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
	        System.out.println("It is " + pawn.getClass().getSimpleName() + "'s turn");
	        checkHand();
			while (actions > 0 && !GameObserver.getInstance().gameOver()) {
				board.printBoard(this.pawn);
				giveOptions();
				int takeAction = getUserInput(0, 7);
				switch (takeAction) {
				    case 0:
				    	GameObserver.getInstance().endGame(true);
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
				    default:
				    	System.out.println("CASE ERROR IN PlayerTurn.doTurn()");
				}
			}
			
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
	    	Coordinate current = new Coordinate(pawn.getPosition().getX(), pawn.getPosition().getY());
	    	Coordinate north = new Coordinate(pawn.getPosition().getX(), pawn.getPosition().getY()+1);
			Coordinate south = new Coordinate(pawn.getPosition().getX(), pawn.getPosition().getY()-1);
			Coordinate west = new Coordinate(pawn.getPosition().getX()-1, pawn.getPosition().getY());
			Coordinate east = new Coordinate(pawn.getPosition().getX()+1, pawn.getPosition().getY());
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
	    	if (capturedTreasure == true) {
	    		System.out.println("Cannot capture another treasure during this turn");
	    		return;
	    	}
	    	System.out.println("Which treasure would you like to capture?");
	    	TreasureEnum[] treasures = TreasureEnum.values();
	    	for (int i = 0; i < 4; i++) {
	    		System.out.println(String.valueOf(i+1) + ": " + treasures[i].toString());
	    	}
	    	int chosenTreasure = getUserInput(1, 4);
	    	if(pawn.captureTreasure(treasures[chosenTreasure-1])) {
				capturedTreasure = true;
				System.out.println("Treasure has been captured");
				actions--;
			}
	    	else {
	    		System.out.println("Cannot capture treasure from this tile");
	    	}
	    }
	    
	    public void tryHelicopterLift() {
	    	int playerNum;
	    	if (pawn.getCard(TreasureEnum.HelicopterLift) == null) {
	    		System.out.println("No Helicopter Lift card in hand");
	    		return;
	    	}
	    	System.out.println("Give the x coordinate of the tile you would like to move to");
			int x = getUserInput(0, 5);
			System.out.println("Give the y coordinate of the tile you would like to move to");
			int y = getUserInput(0, 5);
			if (!board.isTile(new Coordinate(x,y)) || Board.getTile(new Coordinate(x,y)).getSinkStatus()) {
				System.out.println("Can not move to tile");
				return;
			}
			System.out.println("How many players would you like to move? (including yourself)");
			int numPlayers = getUserInput(1, list.getNumPlayers());
			List<Pawn> players = new ArrayList<Pawn>();
			for (int i = 1; i <= numPlayers; i++) {
				System.out.println("Choose a player");
				for (int j = 1; i < list.getNumPlayers(); j++) {
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
	    }
	    
	    public void trySandbag() {
	    	if (pawn.getCard(TreasureEnum.SandBag) == null) {
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
    		}
    		else if (pawn.shoreUp(tile)) {
    			System.out.println("Tile has been shored up");
    		}
    		else {
    			System.out.println("Tile can not be shored up because it has sunk");
    		}
	    }
	    
	    public void drawTreasureCards() {
	    	int playerNum = list.getPlayerIndex(pawn);
	    	for (int i = 0; i < 2; i++) {
	    		Card drawnCard = treasure.drawCard();
	    		System.out.println("A " + drawnCard.getName() + "Card has been drawn");
	    		if (drawnCard instanceof WaterRiseCard) {
	    			treasure.addToDiscardPile(drawnCard);
	    			meter.increaseWaterLevel();
	    			System.out.println("Water level is now " + meter.getWaterLevel());
	    			continue;
	    		}
	    		System.out.println("Do you want to keep it [1] or give it away [2]?");
	    		int choice = getUserInput(1, 2);
	    		if (choice == 1) {
	    			pawn.getHand().add(drawnCard);
	    			checkHand();
	    		}
	    		else {
	    			System.out.println("Who would you like to give the card to?");
	    			for (int j = 1; i < list.getNumPlayers(); j++) {
	    	    		if (j != playerNum) {
	    	    			System.out.println(i + ": " + list.getPlayer(j).getClass().getSimpleName());
	    	    		}
	    	    	}
	    	    	while (playerNum == list.getPlayerIndex(pawn)) {
	    	    		playerNum = getUserInput(1, list.getNumPlayers());
	    	    	}
	    	    	if (pawn.giveTreasureCard(drawnCard, list.getPlayer(playerNum))) {
	    	    		System.out.println("Card has been given");
	    	    	}
	    	    	else {
	    	    		System.out.println("Pawns are on different tiles so card will be kept instead");
	    	    		pawn.getHand().add(drawnCard);
		    			checkHand();
	    	    	}
	    		}
	    	}
	    }
	    
	    public void drawFloodCards() {
	    	for (int i = 0; i < meter.getWaterLevel()-1; i++) {
	    		Card drawnCard = flood.drawCard();
	    		Coordinate point = Board.findByName((TileNameEnum)drawnCard.getName());
	    		if(!Board.getTile(point).getFloodStatus()) {
	    			Board.getTile(point).setFloodStatus(true);
	    		}
	    		else {
	    			Board.getTile(point).setSinkStatus(true);
	    		}
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
	    
}