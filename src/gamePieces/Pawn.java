package gamePieces;

import java.util.*;

import cards.*;
import enums.*;

public class Pawn {

	private static final Map<Role, TileName> startingTiles = new HashMap<Role, TileName>() {{
			put(Role.Diver, TileName.IronGate);
			put(Role.Engineer, TileName.BronzeGate);
			put(Role.Explorer, TileName.CopperGate);
			put(Role.Messenger, TileName.SilverGate);
			put(Role.Navigator, TileName.GoldGate);
			put(Role.Pilot, TileName.FoolsLanding);
	}};

	private Role role;
	private List<Card> hand = new ArrayList<Card>();
	private Coordinate position;
	
	public Pawn(Role role) {
		this.role = role;
		position = Board.findByName(startingTiles.get(role));
	}
	
	public boolean shoreUp(Coordinate point) {
		Tile floodedTile = Board.getTile(point);
		if (floodedTile.getSinkStatus() == false) {
			floodedTile.setFloodStatus(false);
			return true;
		}
		else {
			return false;
		}
	}

	public Role getRole() {
		return this.role;
	}
	
	public List<Card> getHand(){
		return this.hand;
	}

	public Coordinate getPosition() {
		return this.position;
	}
	
}
