import java.util.HashMap;

import Enums.Role;
import GamePieces.Tile;

public class Game {

	private static Game instance = null;

	final static HashMap<Role, Tile> startingTiles = new HashMap<>();

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
		/*
		TODO setup the game with user input, number of players, roles, board etc.
		 */
	}
	
	
}
