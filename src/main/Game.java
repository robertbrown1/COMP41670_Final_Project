package main;

import observer.Observer;
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
		} while (!Observer.getInstance().gameOver());
		
		System.out.println("Game Over: " + Observer.getInstance().gameOver());
		System.out.println("Game Won: " + Observer.getInstance().gameWon());
		System.out.println("Game Lost: " + Observer.getInstance().gameLost());
		return true;
		
	}
	
}
