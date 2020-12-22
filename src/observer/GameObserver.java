package observer;

import java.util.ArrayList;
import java.util.List;

import enums.TileNameEnum;
import enums.TreasureEnum;
import gamePieces.Board;
import gamePieces.Coordinate;
import gamePieces.Tile;
import players.Pawn;

public class GameObserver {
	
	private boolean gameOver;
	private boolean gameWon;
	private static GameObserver instance = null;
	private Board board;
	private List<Pawn> playerList;
	private List<Coordinate> playerLocations;
    private List<TreasureEnum> treasuresCollected;
	
	
	public static GameObserver getInstance() {
		if (instance == null)
			instance = new GameObserver();
		return instance;
	}
	
	public GameObserver() {

		this.gameOver = false;
		this.gameWon = false;
		board = Board.getInstance();
		
	}
	
	public void endGame(boolean won) {
		
		this.gameOver = true;
		this.gameWon = won;
		
	}
	
	public boolean isGameOver() {
		
		this.board = Board.getInstance();
		
		return this.isGameWon() || this.isGameLost();
		
	}
	
	public boolean isGameWon() {
		
		if (Board.getInstance().getTile(this.playerLocations.get(0)).getTileName() == TileNameEnum.FoolsLanding) {
			
			this.gameWon = true;
			
		}
		
		System.out.println("Checked: " + this.gameWon);
		
		return this.gameWon;
		
	}
	
	public boolean isGameLost() {
		
		// condition for FoolsLanding Sunk
		if (Board.getInstance().getTile(Board.findByName(TileNameEnum.FoolsLanding)).getSinkStatus()){
			return true;
		}
		
		//if ()
		
		
		
		return false;
		
	}
	
	public void updatePlayerLocations(List<Pawn> newPlayerList) {
		
		List<Coordinate> temp = new ArrayList<Coordinate>();
		this.playerList = newPlayerList;
		for (int i = 0; i < newPlayerList.size(); i++) {
			temp.add(newPlayerList.get(i).getPosition());
		}
		this.playerLocations = temp;
		System.out.println("Updated");
	}
	
	public List<TreasureEnum> checkTreasuresAvailible() {
		
		List<TreasureEnum> temp = new ArrayList<TreasureEnum>();
		
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 6; y++) {
				
				Tile tile = this.board.getTile(new Coordinate(x, y)); 
				
				if (!tile.getFloodStatus() && tile.getTreasure() != TreasureEnum.None && !temp.contains(tile.getTreasure())) {
					
					temp.add(tile.getTreasure());
					
				}
			}
		}
		
		temp.addAll(this.treasuresCollected);
		return temp;
		
	}
	
	public void updateTreasuresCollected(List<TreasureEnum> newTreasureList) {
		
		this.treasuresCollected = newTreasureList;
		
	}
	
}
