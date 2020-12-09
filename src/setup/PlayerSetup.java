package setup;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

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
		Scanner sc = new Scanner(System.in);
		// Select number of players
		while (numOfPlayers < MIN_PLAYERS || numOfPlayers > MAX_PLAYERS) {
			System.out.print("How many players will play? Choose a number between 2 and 4: ");
			numOfPlayers = sc.nextInt();
		}
		sc.close();
		System.out.println("Number of players chosen: " + numOfPlayers);
	}
	
	public void assignRoles() {
		Set<RoleEnum> chosenRoles = new HashSet<>();
		RoleEnum[] roles = RoleEnum.values();
		Random rand = new Random();
		int choice;

		for (int i=0; i<numOfPlayers; i++) {
			// randomize role selection
			do {
				choice = rand.nextInt(roles.length);
			} while (chosenRoles.contains(roles[choice]));

			switch(roles[choice])
		    {
			    case Engineer:
			    	playerList.addPlayer(new EngineerPawn());
			    case Diver:
			    	playerList.addPlayer(new DiverPawn());
			    case Explorer:
			    	playerList.addPlayer(new ExplorerPawn());
			    case Pilot:
			    	playerList.addPlayer(new PilotPawn());
			    case Messenger:
			    	playerList.addPlayer(new MessengerPawn());
			    case Navigator:
			    	playerList.addPlayer(new NavigatorPawn());
		    }
			
			chosenRoles.add(roles[choice]);
		}

		System.out.println("\nThe following roles have been assigned");
		for(int i=0; i<playerList.getNumPlayers(); i++) {
			System.out.println("Player " + (i+1) + ": " + playerList.getPlayer(i).getClass().getName());
		}
	}
	
}
