package cards;

import enums.TreasureEnum;

/**
 * Class for HelicopterLiftCards Extends the Abstract Class Card
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class HelicopterLiftCard extends Card {
	/**
	 * Calls the super constructor to create a card specific to the helicopter lift
	 * @param name name of the helicopter lift card
	 */
	public HelicopterLiftCard(TreasureEnum name) {
		super(name);
	}
}