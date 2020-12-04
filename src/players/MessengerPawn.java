package players;

import cards.Card;
import enums.TileNameEnum;

public class MessengerPawn extends Pawn {
	
	private static MessengerPawn instance = null;
	
	public static MessengerPawn getInstance() {
		if (instance == null)
			instance = new MessengerPawn();
		return instance;
	}
	
	public MessengerPawn() {
		super(TileNameEnum.SilverGate);
	}
	
	@Override
	public boolean giveTreasureCard(Card treasureCard, Pawn p) {
		p.getHand().add(treasureCard);
		return true;
	}
	
}
