package Cards;

import Enums.TileName;

public class TileCard extends Card {
	/**
	 * calls the super constructor to create a card specific to the weapons in
	 * the game.
	 * 
	 * @param name
	 *            name of the weapon card
	 */
	public TileCard(TileName name) {
		super(name);
	}
	
	public TileName getName() {
		return (TileName) super.getName();
	}
}