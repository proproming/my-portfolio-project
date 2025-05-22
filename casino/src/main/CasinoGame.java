package main;

import gameController.GameController;

public class CasinoGame {

	public static void main(String[] args) throws InterruptedException {	
		GameController game = new GameController();
		game.start(game);
		
		

	}
}
