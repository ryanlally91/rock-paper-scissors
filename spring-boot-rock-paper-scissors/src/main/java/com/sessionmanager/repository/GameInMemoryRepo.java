package com.sessionmanager.repository;

import org.springframework.stereotype.Repository;

import com.sessionmanager.entity.OverallGame;

@Repository
public class GameInMemoryRepo {
	
	//Note: Static - The static variable gets memory only once in the class area at the time of class loading.
	//Overall_game serves as our count of all games played across all instances of the application
	private static final OverallGame OVERALL_GAME = new OverallGame(0,0,0,0);
	
	
	public OverallGame updateTotals(String winner) {
		// Increment overall rounds played regardless
		OVERALL_GAME.setTotalRoundsPlayed(OVERALL_GAME.getTotalRoundsPlayed() + 1);
		    
	    switch (winner) {
	        case "Player1":
	    		OVERALL_GAME.setTotalPlayer1Wins(OVERALL_GAME.getTotalPlayer1Wins() + 1);
	            break;
	        case "Player2":
	    		OVERALL_GAME.setTotalPlayer2Wins(OVERALL_GAME.getTotalPlayer2Wins() + 1);
	            break;
	        case "Draw":
	    		OVERALL_GAME.setTotalDraws(OVERALL_GAME.getTotalDraws() + 1);
	            break;

	    }
		return OVERALL_GAME;
	}
	
	
	public OverallGame getOverallGameScores() {
		return OVERALL_GAME;
	}
	
	public int getTotalRoundsPlayed() {
		return OVERALL_GAME.getTotalRoundsPlayed();
	}
	
	public void resetTotalGamesPlayed() {
		OVERALL_GAME.setTotalRoundsPlayed(0);
	}

	// not necessary unless we want to permanently delete all totals info across all instances up app running
	public OverallGame resetGameData() {
		OVERALL_GAME.setTotalDraws(0);
		OVERALL_GAME.setTotalPlayer1Wins(0);
		OVERALL_GAME.setTotalPlayer2Wins(0);
		OVERALL_GAME.setTotalRoundsPlayed(0);
		return OVERALL_GAME;
	}
}
