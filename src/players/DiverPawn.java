package players;

import enums.TileNameEnum;

public class DiverPawn extends Pawn {
	
	private static DiverPawn instance = null;
	
	public static DiverPawn getInstance() {
		if (instance == null)
			instance = new DiverPawn();
		return instance;
	}
	
	public DiverPawn() {
		super(TileNameEnum.IronGate);
	}
	
	//public boolean movePawn()
	
}
