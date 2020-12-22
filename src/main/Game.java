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
		
		int num = 1;

		do {
			PlayerTurn turn = new PlayerTurn(PlayerList.getInstance().getPlayer(num));
			turn.doTurn();

			num++;
			if (num > PlayerList.getInstance().getNumPlayers())
				num = 1;
		} while (!GameObserver.getInstance().isGameOver());

		
		System.out.println("Game Over: " + GameObserver.getInstance().isGameOver());
		System.out.println("Game Won: " + GameObserver.getInstance().isGameWon());
		return GameObserver.getInstance().isGameWon();
		
	}
	
}
