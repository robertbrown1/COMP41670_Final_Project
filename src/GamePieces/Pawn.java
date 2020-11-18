package GamePieces;

import java.util.*;

import Enums.Role;
import Enums.TileName;

public class Pawn {

	private static final Map<Role, TileName> startingTiles = new HashMap<>() {{
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

	public Role getRole() {
		return role;
	}
	
	public Coordinate getPosition() {
		return this.position;
	}
	
}
