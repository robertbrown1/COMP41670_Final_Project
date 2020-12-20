package players;

import java.util.concurrent.TimeUnit;
import main.Main;
import observer.Observer;
import gamePieces.*;

public class PlayerTurn {
	
	// ===========================================================
		// Setup Variables
		// ===========================================================
		private Pawn pawn;
		private Board board;
	    private int actions;

		
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
//			this.dice = new Dice();
//			this.didMovement = false;
//			this.didHypothesis = false;
//			this.correctAccusation = false;
//			this.inputScanner = inputScanner;
//			this.eventList = EventList.getInstance();
//			getEnums();
			actions = 3;
		}

		
		// ===========================================================
		// Protected functions
		// ===========================================================
		/**
		 * Function that manages the players turn, giving them the 6
		 * possible options.
		 */
	    public void doTurn() {
			
			boolean turnOver = false;
			
	        System.out.println("It is " + pawn.getClass().getSimpleName() + "'s turn!");
	        System.out.println("X: " + pawn.getPosition().getX());
	        System.out.println("Y: " + pawn.getPosition().getY());
			while (actions > 0) {
				Board.getInstance().printBoard();
				giveOptions(actions);
				int takeAction = getUserInput();
				switch (takeAction) {
				    case 0:
				    	//actions = 1;
				    	Observer.getInstance().endGame(true);
				    	break;
				    case 1:
				    	tryMovement();
				    	break;
				    case 2:
				    	tryShoreUp();
				    	break;
				    case 3:
//				    	tryNotepad();
				    	break;
				    case 4:
//				    	tryHypothesis();
				    	break;
				    default:
				    	System.out.println("CASE ERROR IN PlayerTurn.doTurn()");
				}
				actions--;
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
			int direction = getUserInput();
			if (pawn.movePawn(direction))
				actions--;
		}
	    
	    public void tryShoreUp() {
	    	Coordinate floodedTile = null;
	        System.out.println("Which tile do you want to shore up? up [1], down [2], left [3] or right [4]?");
			int direction = getUserInput();
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
	    
	    public int getUserInput() {
	    	int userInput = 0;
		    boolean validInput = false;
			while (!validInput) {
				String userString = Main.sc.nextLine();
					
				try {userInput = Integer.parseInt(userString);} 
		        catch (NumberFormatException e) {continue;}
					
				if ((userInput >= 1) && (userInput <= 4)) {
					validInput = true;
				}
			}
			return userInput;
	    }
	    
}
