package gamePieces;

import enums.TreasureEnum;

public class EarthTreasure extends Treasure {
	
	private static EarthTreasure instance = null;
	
	public static EarthTreasure getInstance() {
		if (instance == null)
			instance = new EarthTreasure();
		return instance;
	}
	
	public EarthTreasure() {
		super(TreasureEnum.EarthStone);
	}
	
}
