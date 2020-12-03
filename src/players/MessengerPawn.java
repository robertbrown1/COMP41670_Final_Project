package players;

import enums.TileName;

public class MessengerPawn extends Pawn {
	
	private static MessengerPawn instance = null;
	
	public static MessengerPawn getInstance() {
		if (instance == null)
			instance = new MessengerPawn();
		return instance;
	}
	
	public MessengerPawn() {
		super(TileName.SilverGate);
	}
	
}
