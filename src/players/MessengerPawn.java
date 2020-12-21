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
		Card temp;
		temp = super.hand.get(super.hand.indexOf(treasureCard));
		super.hand.remove(treasureCard);
		p.getHand().add(temp);
		return true;
	}
	
}
