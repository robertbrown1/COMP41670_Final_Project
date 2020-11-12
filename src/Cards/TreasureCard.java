package Cards;

import Enums.TreasureEnum;

public class TreasureCard extends Card {
	/**
	 * calls the super constructor to create a card specific to the weapons in
	 * the game.
	 * 
	 * @param name
	 *            name of the weapon card
	 */
	public TreasureCard(TreasureEnum name) {
		super(name);
	}
}