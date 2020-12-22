package gamePieces;

/**
 * WaterMeter singleton class.
 * A single water meter object is created and that object is called using the getInstance method.
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class WaterMeter {
	
	//===========================================================
    // Variable Setup
    //===========================================================
	private int waterLevel;
	private static WaterMeter instance = null;
	
	//===========================================================
	// Get Instance of Singleton
	//===========================================================
	/**
	 * gets the singleton instance of the WaterMeter object.
	 * @return the single WaterMeter object.
	 */
	public static WaterMeter getInstance() {
		if (instance == null)
			instance = new WaterMeter();
		return instance;
	}
	
	//===========================================================
	// Constructor
	//===========================================================
	/**
	 * Initializes the water level to 0
	 */
	private WaterMeter() {
		this.waterLevel = 0;
	}
	
	//===========================================================
    // Getters and Setters
    //===========================================================
	/**
     * setWaterLevel gives a value to the water level indicating the game difficulty
     * @param difficulty level of water
     */
	public void setWaterLevel(int difficulty) {
		waterLevel = difficulty;
		
	}
	
	/**
     * getWaterLevel returns the water level
     */
	public int getWaterLevel() {
		return waterLevel;
	}
	
	/**
     * increaseWaterLevekl increments the water level
     */
	public void increaseWaterLevel() {
		waterLevel++;
		
	}
	
}