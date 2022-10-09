package com.sessionmanager.game.logic;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sessionmanager.controller.GameController;
@WebMvcTest(GamePlay.class)
public class GameplayTest {
	
	@InjectMocks
	private GamePlay gamePlay;

	@Test
	void determineWinnerTest(){
		assertEquals(gamePlay.determineWinner(Move.ROCK, Move.ROCK), "Draw");
		assertEquals(gamePlay.determineWinner(Move.SCISSORS, Move.ROCK), "Player2");
		assertEquals(gamePlay.determineWinner(Move.PAPER, Move.ROCK), "Player1");
	}
	
}
