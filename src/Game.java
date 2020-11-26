import Enums.*;
import GamePieces.*;
import Cards.*;
import java.util.*;

public class Game {
	private static final int MAX_PLAYERS = 4;
	private static final int MIN_PLAYERS = 2;
	private static Game instance = null;
	private Board gameBoard;
	private final static HashMap<Role, Tile> startingTiles = new HashMap<>();
	private List<Pawn> pawns = new ArrayList<>();
	private FloodDeck flood;
	private TreasureDeck treasure;
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
		gameBoard = new Board();
		Scanner sc = new Scanner(System.in);

		// Select number of players
		int numOfPlayers = 0;
		while (numOfPlayers < MIN_PLAYERS || numOfPlayers > MAX_PLAYERS) {
			System.out.print("How many players will play? Choose a number between 2 and 4: ");
			numOfPlayers = sc.nextInt();
		}
		sc.close();
		System.out.println("Number of players chosen: " + numOfPlayers);

		// Create flood deck
		flood = FloodDeck.getInstance();
		startSinking();
		
		// Assign player roles
		assignRoles(numOfPlayers);
		
		int difficulty = 0;
		while (difficulty < 1 || difficulty > 4) {
			System.out.print("What difficulty would you like? Choose a number between 1 and 4: ");
			difficulty = sc.nextInt();
		}
		
		// Assign water level based on difficulty
		meter = WaterMeter.getInstance(difficulty);
		
		//Create treasure deck
		treasure = TreasureDeck.getInstance();
		dealTreasureCards();
		
	}
	
	private void startSinking() {
		Card cardDrawn;
		for (int i = 0 ; i < 6 ; i++) {
			cardDrawn = flood.drawCard();
			Coordinate Tile = Board.findByName((TileName)cardDrawn.getName());
			Board.getTile(Tile).setFloodStatus(true);
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
		
		return gameWon() || gameLost();	
		
	}
	
	private boolean gameWon() {
		
		
		
	}
	
	private boolean gameLost() {
		
		
		
	}
	
}
