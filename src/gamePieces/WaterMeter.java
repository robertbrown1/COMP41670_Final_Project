package gamePieces;


public class WaterMeter {
	
	private int waterLevel;
	private static WaterMeter instance = null;
	
	public static WaterMeter getInstance() {
		if (instance == null)
			instance = new WaterMeter();
		return instance;
	}
	
	private WaterMeter() {
		this.waterLevel = 0;
	}
	
	public void setWaterLevel(int difficulty) {
		waterLevel = difficulty;
	}
	
	public int getWaterLevel() {
		return waterLevel;
	}
	
	public void increaseWaterLevel() {
		waterLevel++;
	}
	
}