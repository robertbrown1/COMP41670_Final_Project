package gamePieces;

public class Coordinate {
	
	private int x;
	private int y;
	
	public Coordinate(int x, int y) {
		
		if (x >= 0 &&
				x < 6 &&
				y >= 0 &&
				y < 6) {
			
			this.x = x;
			this.y = y;
					
		}
		else {
			this.x = -1;
			this.y = -1;
		}
		
	}
	
	public int getX() {
		
		return this.x;
		
	}
	
	public void setX(int xValue) {
		
		x = xValue;
		
	}
	
	public int getY() {
		
		return this.y;
		
	}
	
	public void setY(int yValue) {
		
		y = yValue;
		
	}
	
	public Coordinate north() {
		
		return new Coordinate(this.x, this.y + 1);
		
	}
	
	public Coordinate south() {
		
		return new Coordinate(this.x, this.y - 1);
		
	}
	
	public Coordinate east() {
		
		return new Coordinate(this.x + 1, this.y);
		
	}
	
	public Coordinate west() {
		
		return new Coordinate(this.x - 1, this.y);
		
	}
	
	public Coordinate northWest() {
		
		return new Coordinate(this.x - 1, this.y + 1);
		
	}
	
	public Coordinate northEast() {
		
		return new Coordinate(this.x + 1, this.y + 1);
		
	}
	
	public Coordinate southWest() {
		
		return new Coordinate(this.x - 1, this.y - 1);
		
	}
	
	public Coordinate southEast() {
		
		return new Coordinate(this.x + 1, this.y - 1);
		
	}

}
