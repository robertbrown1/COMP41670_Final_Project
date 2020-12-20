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
		
		int num = 0;
		do {
			PlayerTurn turn = new PlayerTurn(PlayerList.getInstance().getPlayer(1));
			turn.doTurn();
		} while (!Observer.getInstance().gameOver());
		
		System.out.println("Game Over: " + Observer.getInstance().gameOver());
		System.out.println("Game Won: " + Observer.getInstance().gameWon());
		System.out.println("Game Lost: " + Observer.getInstance().gameLost());
		return true;
		
	}
	
}
