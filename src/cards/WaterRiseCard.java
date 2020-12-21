package cards;

import enums.TreasureEnum;

/**
 * Class for WaterRiseCards Extends the Abstract Class Card
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class WaterRiseCard extends Card {
	/**
	 * Calls the super constructor to create a card specific to the water rise
	 * @param name name of the water rise card
	 */
	public WaterRiseCard(TreasureEnum name) {
		super(name);
	}
}