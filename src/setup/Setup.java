package setup;

/**
 * Singleton Facade to handle all required setup for the game
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
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

    //===========================================================
    // Methods
    //===========================================================
    /**
     * Method that sets up everything
     */
    public void doAllSetup() {
    	
        boardHandler.assignTiles(); // Randomly assign tile
        floodHandler.startSinking(); // Start sinking tiles
        playerHandler.findPlayers(); // Input players
        playerHandler.assignRoles(); // Randomly assign roles
        treasureHandler.dealTreasureCards(); // Give treasure cards to players
        waterHandler.findDifficulty(); // Get user to input difficulty
        
    }
    
}
