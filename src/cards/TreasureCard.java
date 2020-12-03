package cards;

import enums.Treasure;

public class TreasureCard extends Card {
	/**
	 * calls the super constructor to create a card specific to the weapons in
	 * the game.
	 * 
	 * @param name
	 *            name of the weapon card
	 */
	public TreasureCard(Treasure name) {
		super(name);
	}
}