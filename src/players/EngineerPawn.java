package players;

import enums.TileNameEnum;

public class EngineerPawn extends Pawn {
	
	private static EngineerPawn instance = null;
	
	public static EngineerPawn getInstance() {
		if (instance == null)
			instance = new EngineerPawn();
		return instance;
	}
	
	public EngineerPawn() {
		super(TileNameEnum.BronzeGate);
	}
	
}
