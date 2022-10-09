package com.sessionmanager.game.logic;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.sessionmanager.dto.GameDto;

@Service
public class GamePlay {
		
	public GameDto playRandomGame(GameDto randomGame) {
		randomGame.setPlayer1Choice(generateRandomMove());
		randomGame.setPlayer2Choice(Move.ROCK);
		randomGame.setResult(determineWinner(randomGame.getPlayer1Choice(),randomGame.getPlayer2Choice()));
		return randomGame;
	}
	
	private  Move generateRandomMove() {
	    Move[] moves = Move.values();
	    Random random = new Random();
	    int index = random.nextInt(moves.length);
	    return moves[index];
	}
	// Player 2 always chooses rock so we only assess player 1 choice
	public String determineWinner(Move playerOneChoice, Move playerTwoChoice) {
		String winner = "";
		if(playerOneChoice.equals(Move.SCISSORS) ){
            winner = "Player2";
        }else if( playerOneChoice.equals(Move.ROCK)){
            winner = "Draw";
        }
        if(playerOneChoice.equals(Move.PAPER)){
            winner = "Player1";
        }
		return winner;
		}
	}

