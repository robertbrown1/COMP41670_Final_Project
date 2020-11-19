package GamePieces;

public class WaterMeter {
	
	private int waterLevel;
	
	public WaterMeter(int difficulty) {
		this.waterLevel = difficulty;
	}
	
	public int getWaterLevel() {
		return waterLevel;
	}
	
	public void increaseWaterLevel() {
		waterLevel++;
	}
	
}