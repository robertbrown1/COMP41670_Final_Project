package main;

import java.util.Scanner;

import setup.Setup;
import players.*;

public class Main {
	
	public static Scanner sc = new Scanner(System.in);
	
    public static void main(String[] args) {
    	
    	Setup setup = Setup.getInstance();
    	setup.doAllSetup();
    	PlayerList list = PlayerList.getInstance();
    	PlayerTurn turn = new PlayerTurn(list.getPlayer(1));
    	turn.doTurn();
    }
    
}

