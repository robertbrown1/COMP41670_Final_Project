package cards;

import enums.TileNameEnum;

/**
 * Class for TileCards Extends the Abstract Class Card
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class TileCard extends Card {
	/**
	 * Calls the super constructor to create a card specific to the tile
	 * @param name name of the tile card
	 */
	public TileCard(TileNameEnum name) {
		super(name);
	}
}