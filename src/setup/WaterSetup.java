package setup;

import java.util.Scanner;

import gamePieces.WaterMeter;

public class WaterSetup {
	
	private WaterMeter setupWater;
	
	public WaterSetup() {
	    // Get first instance of Board and extract dimensions
		this.setupWater = WaterMeter.getInstance();
	}
	
	public void findDifficulty() {
		int difficulty = 0;
		Scanner sc = new Scanner(System.in);
		while (difficulty < 1 || difficulty > 4) {
			System.out.print("What difficulty would you like? Choose a number between 1 and 4: ");
			difficulty = sc.nextInt();
		}
		sc.close();
		setupWater.setWaterLevel(difficulty);
	}
	
}
