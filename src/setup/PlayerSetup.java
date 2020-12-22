package setup;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import main.Main;
import observer.GameObserver;
import enums.RoleEnum;
import players.*;

/**
 * Class to handle all aspects of setting up the players
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class PlayerSetup {
	
	// ===========================================================
	// Variable Setup
	// ===========================================================
	private PlayerList playerList;
	private int numOfPlayers;
	private static final int MAX_PLAYERS = 4;
	private static final int MIN_PLAYERS = 2;

	// ===========================================================
	// Constructor
	// ===========================================================
	/**
	 * Constructor for PlayerSetup class
	 */
	public PlayerSetup() {
		this.playerList = PlayerList.getInstance();
		this.numOfPlayers = 0;
	}
	
	//===========================================================
	// Methods
	//===========================================================
	/**
	 * Get user to input number of players
	 */
	public void findPlayers() {
		// Select number of players
		while (numOfPlayers < MIN_PLAYERS || numOfPlayers > MAX_PLAYERS) { // Until input is valid
			System.out.print("How many players will play? Choose a number between 2 and 4: ");
			numOfPlayers = PlayerTurn.getUserInput(2, 4); // Get input from user
			//numOfPlayers = Main.sc.nextInt();
		}
		
		System.out.println("Number of players chosen: " + numOfPlayers);
	}
	
	/**
	 * Randomly assign roles to players
	 */
	public void assignRoles() {
		Set<RoleEnum> chosenRoles = new HashSet<>(); // Initialize list of chosen roles
		RoleEnum[] roles = RoleEnum.values(); // List of roles
		Random rand = new Random();
		int choice;

		while (playerList.getNumPlayers() < this.numOfPlayers) { // Until all players are chosen
			// Randomize role selection
			
			do {
				
				choice = rand.nextInt(roles.length); // Randomly select role
				
			} while (chosenRoles.contains(roles[choice])); // Ensure role has not been selected already

			switch(roles[choice])
		    {
			    case Engineer: // Create Engineer pawn and add to list
			    	playerList.addPlayer(new EngineerPawn());
			    	break;
			    case Diver: // Create Diver pawn and add to list
			    	playerList.addPlayer(new DiverPawn());
			    	break;
			    case Explorer: // Create Explorer pawn and add to list
			    	playerList.addPlayer(new ExplorerPawn());
			    	break;
			    case Pilot: // Create Pilot pawn and add to list
			    	playerList.addPlayer(new PilotPawn());
			    	break;
			    case Messenger: // Create Messenger pawn and add to list
			    	playerList.addPlayer(new MessengerPawn());
			    	break;
			    case Navigator: // Create Navigator pawn and add to list
			    	playerList.addPlayer(new NavigatorPawn());
			    	break;
		    }
			
			chosenRoles.add(roles[choice]); // Add role to list of chosen roles
			
		}

		System.out.println("\nThe following roles have been assigned");
		
		GameObserver.getInstance().updatePlayerLocations(PlayerList.getInstance().getAllPlayers());
		
		for(int i = 1; i <= playerList.getNumPlayers(); i++) { // Print all players
			
			System.out.println("Player " + (i) + ": " + playerList.getPlayer(i).getClass().getSimpleName());
			
		}

	}
	
}
 