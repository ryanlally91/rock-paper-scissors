package com.sessionmanager.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sessionmanager.dto.GameDto;
import com.sessionmanager.dto.ResponseDto;
import com.sessionmanager.entity.Game;
import com.sessionmanager.entity.OverallGame;
import com.sessionmanager.game.logic.GamePlay;
import com.sessionmanager.repository.GameInMemoryRepo;
import com.sessionmanager.session.SessionInMemoryRegistry;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class GameServiceTest {

	
	@Mock
	private SessionInMemoryRegistry sessionRegistry;
	
	@Mock
	private GameInMemoryRepo repo;
	
	@Mock
	private GamePlay gameplay;
	
	@InjectMocks
	private GameServiceImpl  gameService;
	
    private ArrayList<Game> games;
    private GameDto gameDto;
    private Game game;
    private ResponseDto response;
	private HashMap<String, ArrayList<Game>> responseHash;

    
    @BeforeEach 
    void init() {
		games = new ArrayList<>();
		game = new Game();
		game.setResult("Draw");
		games.add(game); 
		
		gameDto = new GameDto();
		gameDto.setResult("Draw");
		
		response = new ResponseDto();
		response.setSessionId("123");
		
		responseHash = new HashMap<String, ArrayList<Game>>();
		responseHash.put("123", games);

    }
	
	@Test
	public void addGameToSessionTest() {

		
		Mockito.when(gameplay.playRandomGame(Mockito.any(GameDto.class))).thenReturn(gameDto);	
		Mockito.when(sessionRegistry.addGameSession(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(response);
		
		ResponseDto result = gameService.addGameToSession(gameDto);
		
		assertEquals(result.getSessionId(), "123");
	}
	
	@Test
	void findGamesBySessionIdTest(){

		
		Mockito.when(sessionRegistry.findGamesBySessionId(Mockito.anyString())).thenReturn(games);
		ArrayList<Game> result = gameService.findGamesBySessionId("123");
		
		assertEquals(result.get(0).getResult(), "Draw");
		
	}
	
	@Test
	void getAllSessionsTest(){

		
		Mockito.when(sessionRegistry.getAllSessions()).thenReturn(responseHash);

		HashMap<String, ArrayList<Game>> result = gameService.getAllSessions();

		
		assertEquals(result.get("123").get(0).getResult(), "Draw");
	}

	@Test
	void deleteGamesBySessionIdTest() {
		String sessionIdResponse = "123";
		Mockito.when(sessionRegistry.deleteGamesBySessionId(Mockito.anyString())).thenReturn(sessionIdResponse);
		String result = gameService.deleteGamesBySessionId("123");
		
		assertEquals(result, sessionIdResponse);
	}
	
}
