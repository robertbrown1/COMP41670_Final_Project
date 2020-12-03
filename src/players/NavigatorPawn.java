package players;

import enums.TileName;

public class NavigatorPawn extends Pawn {
	
	private static MessengerPawn instance = null;
	
	public static MessengerPawn getInstance() {
		if (instance == null)
			instance = new MessengerPawn();
		return instance;
	}
	
	public NavigatorPawn() {
		super(TileName.GoldGate);
	}
	
}
