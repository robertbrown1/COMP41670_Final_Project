package players;

import main.Main;
import enums.TreasureEnum;
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
	    private int shoreUps;
	    private TreasureDeck treasure;
		
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
			this.shoreUps = 0;
			this.actions = 3;
			this.treasure = TreasureDeck.getInstance();
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
			while (actions > 0) {
				giveOptions(actions);
				int takeAction = getUserInput(0, 4);
				switch (takeAction) {
				    case 0:
				    	actions = 0;
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
				    default:
				    	System.out.println("CASE ERROR IN PlayerTurn.doTurn()");
				}
			}			
			System.out.println("Your turn has ended.\n");
		}
	    
	    private void giveOptions(int actions) {
	    	System.out.println("\nYou have " + actions + " actions left\nWhat action would you like to take?\nYour options are:");
	    	System.out.println("[1] Move");
	    	System.out.println("[2] Shore Up");
	    	System.out.println("[3] Give Treasure Card");
	    	System.out.println("[4] Capture Treasure");
	    	System.out.println("[0] End turn");
		}
	    
	    public void tryMovement() {
	        System.out.println("Would you like to move up [1], down [2], left [3] or right [4]?");
			int direction = getUserInput(1, 4);
			if (pawn.movePawn(direction))
				actions--;
		}
	    
	    public void tryShoreUp() {
	    	Coordinate floodedTile = null;
	        System.out.println("Which tile do you want to shore up? up [1], down [2], left [3] or right [4]?");
			int direction = getUserInput(1, 4);
			if (board.canMove(pawn.getPosition(), direction)) {
				switch (direction) {
					case 1:
						floodedTile = new Coordinate(pawn.getPosition().getX(),pawn.getPosition().getY()+1);
						break;
					case 2:
						floodedTile = new Coordinate(pawn.getPosition().getX(),pawn.getPosition().getY()-1);
						break;
					case 3:
						floodedTile = new Coordinate(pawn.getPosition().getX()-1,pawn.getPosition().getY());
						break;
					case 4:
						floodedTile = new Coordinate(pawn.getPosition().getX()+1,pawn.getPosition().getY());
						break;
				}
				if (pawn.shoreUp(floodedTile)) {
					System.out.println("Tile has been shored up");
					if (pawn instanceof EngineerPawn) {
						shoreUps++;
						if (shoreUps == 2)
							actions--;
					}
					else
						actions--;
				}
				else {
					System.out.println("Tile can not be shored up because it has sunk");
				}
			}
			else {
				System.out.println("There is no tile in this direction");
			}
		}
	    
	    public void tryGiveCard() {
	    	int playerNum = list.getPlayerIndex(pawn);
	    	int cardNum;
	    	System.out.println("Which player would you like to give a card to?");
	    	for (int i = 1; i < list.getNumPlayers(); i++) {
	    		if (i != playerNum) {
	    			System.out.println(i + ": " + list.getPlayer(i).getClass().getSimpleName());
	    		}
	    	}
	    	while (playerNum == list.getPlayerIndex(pawn)) {
	    		playerNum = getUserInput(1, list.getNumPlayers());
	    	}
	        System.out.println("Which card would you like to give?");
	    	for (int i = 0; i < pawn.getHand().size(); i++) {
	    		System.out.println(String.valueOf(i+1) + ": " + pawn.getHand().get(i).getName());
	    	}
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
	    	for (int i = 0; i < 3; i++) {
	    		System.out.println(String.valueOf(i+1) + ": " + treasures[i].toString());
	    	}
	    	int chosenTreasure = getUserInput(0, 3);
	    	switch (chosenTreasure) {
	    		case 1:
	    			if(pawn.captureTreasure(earth))
	    				capturedTreasure = true;
	    				actions--;
	    			break;
	    		case 2:
	    			if(pawn.captureTreasure(wind))
	    				capturedTreasure = true;
	    				actions--;
	    			break;
	    		case 3:
	    			if(pawn.captureTreasure(fire))
	    				capturedTreasure = true;
	    				actions--;
	    			break;
	    		case 4:
	    			if(pawn.captureTreasure(ocean))
	    				capturedTreasure = true;
	    				actions--;
	    			break;
	    	}
	    }
	    
	    public void checkHand() {
	    	while (pawn.getHand().size() > 5) {
	    		System.out.println(pawn.getHand().size() + " cards in hand, must have at most 5. Please select card to remove");
	    		for (int i = 0; i < pawn.getHand().size(); i++) {
		    		System.out.println(String.valueOf(i+1) + ": " + pawn.getHand().get(i).getName());
		    	}
	    		int cardNum = getUserInput(1, pawn.getHand().size());
	    		Card chosenCard = pawn.getHand().get(cardNum-1);
	    		pawn.getHand().remove(cardNum-1);
	    		treasure.addToDiscardPile(chosenCard);
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
			}
			return userInput;
	    }
	    
}
