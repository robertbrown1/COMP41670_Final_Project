package main;

import java.util.Scanner;

import observer.GameObserver;
import players.PlayerList;
import players.PlayerTurn;

public class Game {
	
	private static Game instance = null;

	private Game() {}

	public static Game getInstance() {
		if (instance == null)
			instance = new Game();
		return instance;
	}
	
	public boolean gameLoop(){
		
		int playerNum = 1;

		do {
			PlayerTurn turn = new PlayerTurn(PlayerList.getInstance().getPlayer(playerNum));
			turn.doTurn();

			playerNum++;
			
			if (playerNum > PlayerList.getInstance().getNumPlayers())
				playerNum = 1;
		} while (!GameObserver.getInstance().isGameOver());

		System.out.println("Game Over: " + GameObserver.getInstance().isGameOver());
		System.out.println("Game Won: " + GameObserver.getInstance().isGameWon());
		return GameObserver.getInstance().isGameWon();
		
	}
	
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
		return userInput;
		
    }
	
}
