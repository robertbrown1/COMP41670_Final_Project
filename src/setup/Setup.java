package setup;

import java.util.Scanner;

public class Setup {
	
	//===========================================================
    // Variable Setup
    //===========================================================
	// Self singleton
    private static Setup theSetup;
    
    // Setup Controllers
    private PlayerSetup playerHandler;
    private FloodSetup floodHandler;
    private TreasureSetup treasureHandler;
    private BoardSetup boardHandler;
    private WaterSetup waterHandler;

    
    //===========================================================
    // Get Instance of Singleton
    //===========================================================
    /**
     * getInstance of singleton Setup class
     * @return instance of Setup class
     */
    public static Setup getInstance(){
        if(theSetup == null){
            theSetup = new Setup();
        }
        return theSetup;
    }
    
    
    //===========================================================
    // Constructor
    //===========================================================
    /**
     * Constructor class for Setup
     */
    private Setup() {
    	this.boardHandler = new BoardSetup();
        this.playerHandler = new PlayerSetup();
        this.floodHandler = new FloodSetup();
        this.treasureHandler = new TreasureSetup();
        this.waterHandler = new WaterSetup();
    }

    public void doAllSetup() {
    	
        boardHandler.assignTiles(); 
        floodHandler.startSinking();
        playerHandler.findPlayers();
        playerHandler.assignRoles();
        treasureHandler.dealTreasureCards();
        waterHandler.findDifficulty();
        
    }
    
}
