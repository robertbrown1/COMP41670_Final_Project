package GamePieces;

public class WaterMeter {
	
	private int waterLevel;
	
	public WaterMeter() {
		this.waterLevel = 1;
	}
	
	public int getWaterLevel() {
		return waterLevel;
	}
	
	public void increaseWaterLevel() {
		waterLevel++;
	}
	
}