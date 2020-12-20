package observer;

import gamePieces.Board;

public class GameObserver {
	
	private boolean gameOver;
	private boolean gameWon;
	private boolean gameLost;
	private static GameObserver instance = null;
	private Board board;
	
	
	public static GameObserver getInstance() {
		if (instance == null)
			instance = new GameObserver();
		return instance;
	}
	
	public GameObserver() {

		this.gameOver = false;
		this.gameWon = false;
		this.gameLost = false;
		
	}
	
	public void endGame(boolean won) {
		
		this.gameOver = true;
		
		if (won) {
			this.gameWon = true;
		}
		else {
			this.gameLost = true;
		}
		
	}
	
	public boolean gameOver() {
		
		return this.gameOver;
		
	}
	
	public boolean gameLost() {
		
		return this.gameLost;
		
	}
	
	public boolean gameWon() {
		
		return this.gameWon;
		
	}
}
