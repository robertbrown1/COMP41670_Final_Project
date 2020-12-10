package main;

public class Game {
	
	private static Game instance = null;

	private Game() {}

	public static Game getInstance() {
		if (instance == null)
			instance = new Game();
		return instance;
	}
	
	private boolean gameOver() {
		
		return gameWon() || gameLost();
		
	}
	
	private boolean gameWon() {
		
		return true;
		
	}
	
	private boolean gameLost() {
		
		return true;
		
	}
	
}
