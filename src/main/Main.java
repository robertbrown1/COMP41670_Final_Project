package main;

import java.util.Scanner;

import setup.Setup;

public class Main {
	
	public static Scanner sc = new Scanner(System.in);
	
    public static void main(String[] args) {
    	
    	Setup setup = Setup.getInstance();
    	Game game = Game.getInstance();
    	setup.doAllSetup();
    	game.gameLoop();
    	
    }
    
}

