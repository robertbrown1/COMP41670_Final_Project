package main;

import java.util.Scanner;

import observer.GameObserver;
import players.PlayerList;
import players.PlayerTurn;

/**
 * Game class runs the game loop
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class Game {
	
    //===========================================================
    // Variable Setup
    //===========================================================
	private static Game instance = null;

	//===========================================================
    // Get Instance of Singleton
    //===========================================================
    /**
     * getInstance method returns single instance of Game.
     * @return instance singleton Game object.
     */
	public static Game getInstance() {
		if (instance == null)
			instance = new Game();
		return instance;
	}
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Constructor for the Game Class
	 */
	private Game() {}
	
	//===========================================================
	// Methods
	//===========================================================
	
    /**
	 * gameLoop runs the main loop for player turns. Runs until the game is over
	 * @return boolean is the game won
	 */
	public boolean gameLoop(){
		
		int playerIndex = 1;	// the index of the current player

		do {
				// create a player turn 
			PlayerTurn turn = new PlayerTurn(PlayerList.getInstance().getPlayer(playerIndex));
			
			turn.doTurn();  // run the turn

			playerIndex++;  // increment the player index
			
				// condition for resetting the player index to 1
			if (playerIndex > PlayerList.getInstance().getNumPlayers())
				playerIndex = 1;
			
		} while (!GameObserver.getInstance().isGameOver()); // the game runs until its over

		System.out.println("Game Over: " + GameObserver.getInstance().isGameOver());
		boolean gameWon = GameObserver.getInstance().isGameWon();
		
		if (gameWon) {	// printing the results
			
			System.out.println("Congratulations, you won the game!");
			
		}
		else {
			
			System.out.println("You lost the game! Better luck next time");
			
		}
		
		return gameWon;
		
	}
	
    /**
	 * getUserInput allows the user to enter values
	 * @param minVal the smallest number in range of options
	 * @param maxVal the largest number in range of options
	 * @return the option that the user has selected
	 */
    public static int getUserInput(int minVal, int maxVal) {
    	
    	int userInput = 0;
	    boolean validInput = false;
	    Main.sc = new Scanner(System.in); // resetting the buffer
	    
		while (!validInput) { // Until number in range is selected
			
			String userString = Main.sc.nextLine(); // Scanner for user string
			try {userInput = Integer.parseInt(userString);} //Try to convert string to integer
	        catch (NumberFormatException e) {continue;}
				
			if ((userInput >= minVal) && (userInput <= maxVal)) { // Input is within range
				
				validInput = true;
				
			}
			if (!validInput) {
				
				System.out.println("Please enter a valid input");
				
			}
		}
		
		return userInput; // return the user input
		
    }
	
}
