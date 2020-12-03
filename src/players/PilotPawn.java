package players;

import enums.TileName;

public class PilotPawn extends Pawn {
	
	private static PilotPawn instance = null;
	
	public static PilotPawn getInstance() {
		if (instance == null)
			instance = new PilotPawn();
		return instance;
	}
	
	public PilotPawn() {
		super(TileName.FoolsLanding);
	}
	
}
