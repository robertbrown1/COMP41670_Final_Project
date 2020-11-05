package Cards;

import java.util.*;
import Enums.TreasureEnum;

public class Deck {
	
	private List<Card> cardsInDeck;
	
	public Deck() {
	    // Prepare empty array of Cards
	    this.cardsInDeck = new ArrayList<Card>();
	    
	    // Create TreasureCards
	    for (int i=0;i<5;i++) {
		    cardsInDeck.add(new TreasureCard(TreasureEnum.EarthStone));
		    cardsInDeck.add(new TreasureCard(TreasureEnum.WindStatue));
		    cardsInDeck.add(new TreasureCard(TreasureEnum.FireCrystal));
		    cardsInDeck.add(new TreasureCard(TreasureEnum.OceanChalice));
	    }

        // Create WeaponCards
	    cardsInDeck.add(new WeaponCard(WeaponsEnums.CANDLESTICK));
	    cardsInDeck.add(new WeaponCard(WeaponsEnums.KNIFE));
	    cardsInDeck.add(new WeaponCard(WeaponsEnums.LEAD_PIPE));
	    cardsInDeck.add(new WeaponCard(WeaponsEnums.POISON));
	    cardsInDeck.add(new WeaponCard(WeaponsEnums.REVOLVER));
	    cardsInDeck.add(new WeaponCard(WeaponsEnums.ROPE));
        
        // Create RoomCards
	    cardsInDeck.add(new RoomCard(RoomsEnums.BALLROOM));
	    cardsInDeck.add(new RoomCard(RoomsEnums.BILLIARD_ROOM));
	    cardsInDeck.add(new RoomCard(RoomsEnums.CONSERVATORY));
	    cardsInDeck.add(new RoomCard(RoomsEnums.DINING_ROOM));
	    cardsInDeck.add(new RoomCard(RoomsEnums.HALL));
	    cardsInDeck.add(new RoomCard(RoomsEnums.KITCHEN));
	    cardsInDeck.add(new RoomCard(RoomsEnums.LIBRARY));
	    cardsInDeck.add(new RoomCard(RoomsEnums.LOUNGE));
	    cardsInDeck.add(new RoomCard(RoomsEnums.STUDY));
	}
}
