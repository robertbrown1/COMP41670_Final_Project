package gamePieces;

/**
 * Class to specify x and y coordinates of a pawn or tile
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */

public class Coordinate {
	
	//===========================================================
    // Variable Setup
    //===========================================================
	private int x;
	private int y;
	
	//===========================================================
    // Constructor
    //===========================================================
    /**
     * Coordinate constructor
     * @param x the x coordinate
     * @param y the y coordinate
     */
	public Coordinate(int x, int y) {
		
		if (x >= 0 &&
				x < 6 &&
				y >= 0 &&
				y < 6) { // Ensure that x and y values are valid
			
			this.x = x;
			this.y = y;
					
		}
		else { // Invalid values have been entered
			this.x = -1;
			this.y = -1;
		}
		
	}
	
	//===========================================================
    // Getters and Setters
    //===========================================================
	/**
     * getX returns the x coordinate
     */
	public int getX() {
		
		return this.x;
		
	}
	
	/**
     * setX changes the x coordinate
     * @param xValue the value to change x to
     */
	public void setX(int xValue) {
		
		x = xValue;
		
	}
	
	/**
     * getY returns the y coordinate
     */
	public int getY() {
		
		return this.y;
		
	}
	
	/**
     * setY changes the y coordinate
     * @param yValue the value to change y to
     */
	public void setY(int yValue) {
		
		y = yValue;
		
	}
	
	//===========================================================
    // Nearby coordinates
    //===========================================================
	/**
     * returns the coordinate to the north of this coordinate
     */
	public Coordinate north() {
		
		return new Coordinate(this.x, this.y + 1);
		
	}
	
	/**
     * returns the coordinate to the south of this coordinate
     */
	public Coordinate south() {
		
		return new Coordinate(this.x, this.y - 1);
		
	}
	
	/**
     * returns the coordinate to the east of this coordinate
     */
	public Coordinate east() {
		
		return new Coordinate(this.x + 1, this.y);
		
	}
	
	/**
     * returns the coordinate to the west of this coordinate
     */
	public Coordinate west() {
		
		return new Coordinate(this.x - 1, this.y);
		
	}
	
	/**
     * returns the coordinate to the northwest of this coordinate
     */
	public Coordinate northWest() {
		
		return new Coordinate(this.x - 1, this.y + 1);
		
	}
	
	/**
     * returns the coordinate to the northeast of this coordinate
     */
	public Coordinate northEast() {
		
		return new Coordinate(this.x + 1, this.y + 1);
		
	}
	
	/**
     * returns the coordinate to the southwest of this coordinate
     */
	public Coordinate southWest() {
		
		return new Coordinate(this.x - 1, this.y - 1);
		
	}
	
	/**
     * returns the coordinate to the southeast of this coordinate
     */
	public Coordinate southEast() {
		
		return new Coordinate(this.x + 1, this.y - 1);
		
	}
	
	/**
     * equals is used to compare 2 coordinates
     * @param coordinate object to be compared
     * @return true or false if the are equal
     */
	@Override
	public boolean equals(Object o) {
		Coordinate position;
		if (o instanceof Coordinate) { // Ensure coordinates are being compared
			position = (Coordinate) o;
			if(position.getX() == x && position.getY() == y) { // x and y coordinates are equal
				return true;
			}
		}
		return false; // Objects are not equal
	}
	
}
