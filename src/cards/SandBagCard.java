package cards;

import enums.TreasureEnum;

/**
 * Class for SandBagCards Extends the Abstract Class Card
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class SandBagCard extends Card {
	/**
	 * Calls the super constructor to create a card specific to the sand bag
	 * @param name name of the sand bag card
	 */
	public SandBagCard(TreasureEnum name) {
		super(name);
	}
}