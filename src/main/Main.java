package main;

import java.util.Scanner;

import setup.Setup;
import gamePieces.Board;

public class Main {
	
	public static Scanner sc = new Scanner(System.in);
	
    public static void main(String[] args) {
    	
    	Setup setup = Setup.getInstance();
    	setup.doAllSetup();
    	Board.getInstance().printBoard();
    	
    }
    
}

