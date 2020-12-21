package cards;

import enums.TreasureEnum;

/**
 * Class for TreasureCards Extends the Abstract Class Card
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class TreasureCard extends Card {
	/**
	 * Calls the super constructor to create a card specific to the treasure
	 * @param name name of the treasure card
	 */
	public TreasureCard(TreasureEnum name) {
		super(name);
	}
}