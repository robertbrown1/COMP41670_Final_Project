package setup;

import gamePieces.WaterMeter;
import main.Main;
import players.PlayerTurn;

public class WaterSetup {
	
	private WaterMeter setupWater;
	
	public WaterSetup() {
	    // Get first instance of Board and extract dimensions
		this.setupWater = WaterMeter.getInstance();
	}
	
	public void findDifficulty() {
		int difficulty = 0;
		while (difficulty < 1 || difficulty > 4) {
			System.out.print("What difficulty would you like? Choose a number between 1 and 4: ");
			difficulty = PlayerTurn.getUserInput(1, 4);
			//difficulty = Main.sc.nextInt();
		}
		setupWater.setWaterLevel(difficulty);
	}
	
}
