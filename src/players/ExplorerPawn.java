package players;

import enums.TileNameEnum;

public class ExplorerPawn extends Pawn {
	
	private static ExplorerPawn instance = null;
	
	public static ExplorerPawn getInstance() {
		if (instance == null)
			instance = new ExplorerPawn();
		return instance;
	}
	
	public ExplorerPawn() {
		super(TileNameEnum.CopperGate);
	}
	
}
