package players;

import java.util.ArrayList;
import java.util.List;

import enums.TreasureEnum;
import observer.GameObserver;


/**
 * Singleton Class for managing the list of Players in a game
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */

public class PlayerList {

	//===========================================================
    // Variable Setup
    //===========================================================
    private static PlayerList thePlayerList;
    private List<Pawn> playerList;
    private List<TreasureEnum> treasuresCollected;
    
    
    //===========================================================
    // Get Instance of Singleton
    //===========================================================
    /**
     * Get the single instance of the List of Players in the game
     * @return The PlayerList object
     */
    public static PlayerList getInstance(){
    	
        if(thePlayerList == null){
        	
        	thePlayerList = new PlayerList();
        }
        
        return thePlayerList;
        
    }
    
    
    //===========================================================
    // Constructor
    //===========================================================
    /**
     * constructor of a new PlayerList
     */
    private PlayerList() { 
    	
    	this.playerList = new ArrayList<Pawn>();
    	this.treasuresCollected = new ArrayList<TreasureEnum>();
    	
    }
    
    //===========================================================
    // Getters and Setters
    //===========================================================
    /**
     * Get the number of Players in the PlayerList
     * @return integer representing number of Players in game
     */
    public int getNumPlayers() {
    	
    	return playerList.size();
    	
    }
    
    /**
     * Get the Player at a certain index in the PlayerList
     * @param i index
     * @return Player at that index
     */
    public Pawn getPlayer(int i) {
    	
    	return playerList.get(i-1);
    	
    }
    
    /**
     * Find the Players position in the PlayerList
     * @param player The player whose position you want to know
     * @return Their index, an integer number
     */
    public int getPlayerIndex(Pawn player){
    	
    	return playerList.indexOf(player)+1;
    	
    }
    
    /**
     * Sets a Player in the list equal to a different Player
     * @param i the index of the original Player
     * @param updatedPlayer The updated Player object
     */
    public void setPlayer(int i, Pawn updatedPlayer) {
    	
    	playerList.set(i-1, updatedPlayer);
    	
    }
    
    /**
     * Add a Player object to the PlayerList
     * @param newPlayer The Player object to be added
     */
    public void addPlayer(Pawn newPlayer) {
    	
    	playerList.add(newPlayer);
    	
    }
    
    /**
     * Get all the Players in PlayerList
     * @return a List of Player objects
     */
    public List<Pawn> getAllPlayers(){
    	
    	return playerList;
    	
    }
    
    /**
     * Get all the Players in PlayerList
     * @return a List of Player objects
     */
    public List<TreasureEnum> getTreasuresCollected(){
    	
    	return treasuresCollected;
    	
    }
    
    /**
     * Add a treasure to the list of collected treasures
     * @return true if the treasure is collected
     */
    public boolean collectTreasure(TreasureEnum treasure) {
    	
    	if (!this.treasuresCollected.contains(treasure) && treasure != TreasureEnum.None) {
    		
    		treasuresCollected.add(treasure);
    		GameObserver.getInstance().updateTreasuresCollected(treasuresCollected);
        	return true;
        	
    	}
    	else 
    		return false;
    	
    }
    
	//===========================================================
	// Singleton destroyer for unit testing ONLY
	//===========================================================
	
	public void destroyMe() {
		
	    playerList = null;
	    
	}
	
}
