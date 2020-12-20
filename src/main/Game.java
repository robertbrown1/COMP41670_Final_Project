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
		
		int num = 0;
		do {
			PlayerTurn turn = new PlayerTurn(PlayerList.getInstance().getPlayer(1));
			turn.doTurn();
		} while (!GameObserver.getInstance().gameOver());
		
		System.out.println("Game Over: " + GameObserver.getInstance().gameOver());
		System.out.println("Game Won: " + GameObserver.getInstance().gameWon());
		System.out.println("Game Lost: " + GameObserver.getInstance().gameLost());
		return true;
		
	}
	
}
