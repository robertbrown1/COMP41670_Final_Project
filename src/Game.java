import Enums.Role;
import GamePieces.Pawn;
import GamePieces.Tile;
import GamePieces.WaterMeter;
import Cards.*;
import java.util.*;

public class Game {
	private static final int MAX_PLAYERS = 4;
	private static Game instance = null;
	private final static HashMap<Role, Tile> startingTiles = new HashMap<>();
	private List<Pawn> pawns = new ArrayList<>();
	private Cards.Deck flood;
	private Cards.Deck treasure;
	private WaterMeter meter;

	private Game() {}

	public static Game getInstance() {
		if (instance == null)
			instance = new Game();
		return instance;
	}

	public void start() {
		setup();
	}

	private void setup() {
		Scanner sc = new Scanner(System.in);

		// Select number of players
		int numOfPlayers = 0;
		while (numOfPlayers < 2 || numOfPlayers > MAX_PLAYERS) {
			System.out.print("How many players will play? Choose a number between 2 and 4: ");
			numOfPlayers = sc.nextInt();
		}
		sc.close();
		System.out.println("Number of players chosen: " + numOfPlayers);

		// Create flood deck
		flood = new FloodDeck();
		startSinking();
		
		// Assign player roles
		assignRoles(numOfPlayers);
		
		int difficulty = 0;
		while (difficulty < 1 || difficulty > 4) {
			System.out.print("What difficulty would you like? Choose a number between 1 and 4: ");
			difficulty = sc.nextInt();
		}
		
		// Assign water level based on difficulty
		meter = new WaterMeter(difficulty);
		
		//Create treasure deck
		treasure = new TreasureDeck();
		dealTreasureCards();
		
	}
	
	private void startSinking() {
		Card cardDrawn;
		for (int i = 0 ; i < 6 ; i++) {
			cardDrawn = flood.drawCard();
			flood.addToDiscardPile(cardDrawn);
		}
	}
	
	private void dealTreasureCards() {
		Card cardDrawn;
		for (Pawn p:pawns) {
			for (int i = 0 ; i < 2 ; i++) {
				cardDrawn = treasure.drawCard();
				while (cardDrawn instanceof WaterRiseCard) {
					treasure.replaceCard(cardDrawn);
					treasure.shuffleDeck();
					cardDrawn = treasure.drawCard();
				}
				p.getHand().add(cardDrawn);
			}
		}
	}
	
	private void assignRoles(int numOfPlayers) {
		Set<Role> chosenRoles = new HashSet<>();
		Role[] roles = Role.values();
		Random rand = new Random();
		int choice;

		for (int i=0; i<numOfPlayers; i++) {
			// randomize role selection
			do {
				choice = rand.nextInt(roles.length);
			} while (chosenRoles.contains(roles[choice]));

			pawns.add(new Pawn(roles[choice]));
			chosenRoles.add(roles[choice]);
		}

		System.out.println("\nThe following roles have been assigned");
		for(int i=0; i<pawns.size(); i++) {
			System.out.println("Player " + (i+1) + ": " + pawns.get(i).getRole());
		}
	}
	
	private boolean gameOver() {
		return false;	
	}
}
