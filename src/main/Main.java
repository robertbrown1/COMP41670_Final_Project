package main;

import java.util.Scanner;

import setup.Setup;

/**
 * Main class for Forbidden Island
 * 
 * @author  Barry McNicholl & Robert Brown
 * @since   21 12 2020
 * @version 1.0
 */
public class Main {
	
	//===========================================================
    // Variable Setup
    //===========================================================
	public static Scanner sc = new Scanner(System.in);
	
	/**
	 * Main method
	 */
    public static void main(String[] args) {
    	
    	Setup.getInstance().doAllSetup();; // Setup game
    	Game.getInstance().gameLoop(); // Run game

    	sc.close();
    	
    }
    
}

