package main;

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
		
		do {
			PlayerTurn turn = new PlayerTurn(PlayerList.getInstance().getPlayer(1));
			turn.doTurn();
		} while (!GameObserver.getInstance().isGameOver());
		
		System.out.println("Game Over: " + GameObserver.getInstance().isGameOver());
		System.out.println("Game Won: " + GameObserver.getInstance().isGameWon());
		return GameObserver.getInstance().isGameWon();
		
	}
	
}
