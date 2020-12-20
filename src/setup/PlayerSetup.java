package setup;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import main.Main;

import enums.RoleEnum;
import players.*;

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
	
	public void findPlayers() {
		
		// Select number of players
		while (numOfPlayers < MIN_PLAYERS || numOfPlayers > MAX_PLAYERS) {
			System.out.print("How many players will play? Choose a number between 2 and 4: ");
			numOfPlayers = Main.sc.nextInt();
		}
		
		System.out.println("Number of players chosen: " + numOfPlayers);
	}
	
	public void assignRoles() {
		Set<RoleEnum> chosenRoles = new HashSet<>();
		RoleEnum[] roles = RoleEnum.values();
		Random rand = new Random();
		int choice;

		while (playerList.getNumPlayers() < this.numOfPlayers) {
			// Randomize role selection
			
			do {
				
				choice = rand.nextInt(roles.length);
				
			} while (chosenRoles.contains(roles[choice]));

			switch(roles[choice])
		    {
			    case Engineer:
			    	playerList.addPlayer(new EngineerPawn());
			    	break;
			    case Diver:
			    	playerList.addPlayer(new DiverPawn());
			    	break;
			    case Explorer:
			    	playerList.addPlayer(new ExplorerPawn());
			    	break;
			    case Pilot:
			    	playerList.addPlayer(new PilotPawn());
			    	break;
			    case Messenger:
			    	playerList.addPlayer(new MessengerPawn());
			    	break;
			    case Navigator:
			    	playerList.addPlayer(new NavigatorPawn());
			    	break;
		    }
			
			chosenRoles.add(roles[choice]);
			
		}

		System.out.println("\nThe following roles have been assigned");
		
		for(int i = 1; i <= playerList.getNumPlayers(); i++) {
			
			System.out.println("Player " + (i) + ": " + playerList.getPlayer(i).getClass().getSimpleName());
			
		}

	}
	
}
 