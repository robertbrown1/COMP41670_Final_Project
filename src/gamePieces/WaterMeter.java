package gamePieces;


public class WaterMeter {
	
	private int waterLevel;
	private static WaterMeter instance = null;
	
	public static WaterMeter getInstance(int difficulty) {
		if (instance == null)
			instance = new WaterMeter(difficulty);
		return instance;
	}
	
	private WaterMeter(int difficulty) {
		this.waterLevel = difficulty;
	}
	
	public int getWaterLevel() {
		return waterLevel;
	}
	
	public void increaseWaterLevel() {
		waterLevel++;
	}
	
}