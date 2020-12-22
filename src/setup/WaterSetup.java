package setup;

import gamePieces.WaterMeter;
import main.Main;

/**
 * Class to handle all aspects of setting up the water meter
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class WaterSetup {
	
	// ===========================================================
	// Variable Setup
	// ===========================================================
	private WaterMeter setupWater;
	
	// ===========================================================
	// Constructor
	// ===========================================================
	/**
	 * Constructor for WaterSetup class
	 */
	public WaterSetup() {
		this.setupWater = WaterMeter.getInstance();
	}
	
	//===========================================================
	// Methods
	//===========================================================
	/**
	 * Set difficulty of the game i.e. the water level
	 */
	public void findDifficulty() {
		int difficulty = 0;
		while (difficulty < 1 || difficulty > 4) { // Must be between 1 and 4
			System.out.print("What difficulty would you like? Choose a number between 1 and 4: ");
			difficulty = Main.sc.nextInt(); // Get value from user
		}
		setupWater.setWaterLevel(difficulty); // Set water level as user input
	}
	
}
