package GamePieces;

import java.util.*;

import Enums.Role;
import Enums.TileName;

public class Pawn {
         
	private Role role;
	private List<Card> hand = new ArrayList<Card>();
	private TileName position;
	
	
	public Pawn(Role role) {
		
		this.role = role;
		
		switch(this.role)
	    {
		case Engineer:
			this.position = TileName.BronzeGate;
		case Diver:
			this.position = TileName.IronGate;
		case Explorer:
			this.position = TileName.CopperGate;
		case Pilot:
			this.position = TileName.FoolsLanding;
		case Messenger:
			this.position = TileName.SilverGate;
		case Navigator:
			this.position = TileName.GoldGate;
	    }	
		
	}
	
	
}
