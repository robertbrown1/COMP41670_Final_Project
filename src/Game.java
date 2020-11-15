import Enums.Role;
import GamePieces.Pawn;
import GamePieces.Tile;
import java.util.*;

public class Game {
	private static final int MAX_PLAYERS = 4;
	private static Game instance = null;
	private final static HashMap<Role, Tile> startingTiles = new HashMap<>();
	private List<Pawn> pawns = new ArrayList<>();

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
		while (numOfPlayers < 2 || numOfPlayers > 4) {
			System.out.print("How many players will play? Choose a number between 2 and 4: ");
			numOfPlayers = sc.nextInt();
		}
		System.out.println("Number of players chosen: " + numOfPlayers);

		// Assign player roles
		assignRoles(numOfPlayers);
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
}
