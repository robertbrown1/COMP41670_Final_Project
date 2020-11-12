package GamePieces;

public class WaterMeter {
	
	private int waterLevel;
	
	public WaterMeter() {
		this.waterLevel = 1;
	}
	
	public void increaseLevel() {
		waterLevel++;
	}
	
}