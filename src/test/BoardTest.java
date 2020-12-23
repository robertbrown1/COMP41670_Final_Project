package test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import enums.TileNameEnum;
import gamePieces.*;
import setup.BoardSetup;

/**
 * JUnit test cases for the Board class
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */

public class BoardTest {
	
	private BoardSetup boardSetup;
	private Board testBoard;
	
	@Before
	public void setUp() throws Exception {
        boardSetup = new BoardSetup();
        boardSetup.assignTiles();
        testBoard = Board.getInstance();
	}
	
	@After
    public void teardown() {
        boardSetup = null;
        testBoard = null;
    }
    
	@Test
	public void testAssignTiles() {
		List<Tile> checkTiles = new ArrayList<Tile>();
		for (int x = 0; x < 6; x++) { // For each x coordinate
			for (int y = 0; y < 6; y++) { // For each y coordinate
				if (Math.abs(2.5-x) + Math.abs(2.5-y) < 4) { // Tile can be placed at this coordinate
					assertFalse("Duplicate Tiles", checkTiles.contains(testBoard.getBoard()[x][y]));
					checkTiles.add(testBoard.getBoard()[x][y]);
				}
				else { // Tile can not be placed here
					assertNull("Tile should be null", testBoard.getBoard()[x][y]);
				}
			}
		}
	}

	@Test
	public void testFindByName() {
		TileNameEnum[] tileNames = TileNameEnum.values(); // Get names of tiles
		for (TileNameEnum i: tileNames) { // For each tile
			Coordinate position = Board.findByName(i); // Try to find coordinate from tile name
			Tile tile = testBoard.getBoard()[position.getX()][position.getY()]; // Corresponding tile
			assertEquals("Wrong tile found", i, tile.getTileName());
		}
	}
	
	@Test
	public void testCanMove() {
		for (int x = 0; x < 6; x++) { // For each x coordinate
			for (int y = 0; y < 6; y++) { // For each y coordinate
				for (int z = 0; z < 5; z++) { // For all directions
					// Current position
					Coordinate point = new Coordinate(x, y);
					// Position north of current
					boolean north = testBoard.isTile(point.north()) && !Board.getTile(point.north()).getSinkStatus();
					// Position south of current
					boolean south = testBoard.isTile(point.south()) && !Board.getTile(point.south()).getSinkStatus();
					// Position west of current
					boolean west = testBoard.isTile(point.west()) && !Board.getTile(point.west()).getSinkStatus();
					// Position east of current
					boolean east = testBoard.isTile(point.east()) && !Board.getTile(point.east()).getSinkStatus();
					// Check if move possible in direction
					boolean movePossible = testBoard.canMove(point, z);
					if (!testBoard.isTile(point)) // Not a valid tile so pawn shouldn't be here
						assertFalse("Tile is null so can't move", movePossible);
					else {
						switch(z) {
							case 0: // Any direction
								if (north || south || west || east)
									assertTrue("Can move in at least one direction", movePossible);
								else
									assertFalse("No move possible", movePossible);
								break;
							case 1: // North
								if (north)
									assertTrue("Can move north", movePossible);
								else
									assertFalse("Can't move north", movePossible);
								break;
							case 2: // South
								if (south)
									assertTrue("Can move south", movePossible);
								else
									assertFalse("Can't move south", movePossible);
								break;
							case 3: // West
								if (west)
									assertTrue("Can move west", movePossible);
								else
									assertFalse("Can't move west", movePossible);
								break;
							case 4: // East
								if (east)
									assertTrue("Can move east", movePossible);
								else
									assertFalse("Can't move east", movePossible);
								break;
						}
					}
				}
			}
		}
	}
	
	@Test
	public void testIsTile() {
		for (int x = 0; x < 6; x++) { // For each x coordinate
			for (int y = 0; y < 6; y++) { // For each y coordinate
				if (Math.abs(2.5-x) + Math.abs(2.5-y) < 4) // Should be a tile here
					assertTrue("Is a valid tile", testBoard.getBoard()[x][y] != null);
				else // Tile can't be here
					assertTrue("Not a tile because it is null", testBoard.getBoard()[x][y] == null);
			}
		}
	}
	
	@Test
	public void testGetTile() {
		for (int x = 0; x < 6; x++) { // For each x coordinate
			for (int y = 0; y < 6; y++) { // For each y coordinate
				Coordinate point = new Coordinate(x, y); // Coordinate of tile
				assertEquals("Tiles should be equal", testBoard.getBoard()[x][y], Board.getTile(point));
			}
		}
	}
	
}
