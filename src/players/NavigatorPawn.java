package players;

import enums.TileNameEnum;

public class NavigatorPawn extends Pawn {
	
	private static MessengerPawn instance = null;
	
	public static MessengerPawn getInstance() {
		if (instance == null)
			instance = new MessengerPawn();
		return instance;
	}
	
	public NavigatorPawn() {
		super(TileNameEnum.GoldGate);
	}
	
}
