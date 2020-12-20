package observer;

public class Observer {
	
	private boolean gameOver;
	private boolean gameWon;
	private boolean gameLost;
	private static Observer instance = null;
	
	public static Observer getInstance() {
		if (instance == null)
			instance = new Observer();
		return instance;
	}
	
	public Observer() {

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
