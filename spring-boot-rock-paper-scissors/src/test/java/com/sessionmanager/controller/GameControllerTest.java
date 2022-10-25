package com.sessionmanager.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sessionmanager.dto.GameDto;
import com.sessionmanager.dto.ResponseDto;
import com.sessionmanager.entity.Game;
import com.sessionmanager.service.GameService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@WebMvcTest(GameController.class)
public class GameControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private GameService service;
	
	@InjectMocks
	private GameController gameController;
	
	MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));

	
	@Test
	void createGameTest() throws Exception {
		ResponseDto mockResponse = new ResponseDto();
		
		GameDto game = new GameDto("xxx-xxx", null, null, "");
		mockResponse.setSessionId("xxx-xxx");
		
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(game);
        
		Mockito.when(service.addGameToSession(Mockito.any(GameDto.class))).thenReturn(mockResponse);

		mockMvc.perform(post("/game/create").content(json)
				.accept(MEDIA_TYPE_JSON_UTF8).contentType(MEDIA_TYPE_JSON_UTF8))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.sessionId").value("xxx-xxx"));
//		Mockito.verify(service, Mockito.times(1)).addGameToSession(game);
	}
	
	@Test
	void getSessionByIdTest() throws Exception {
		ArrayList<Game> mockResponse = new ArrayList<>();
		Game game = new Game();
		game.setResult("Draw");
		mockResponse.add(game);
		        
		Mockito.when(service.findGamesBySessionId(Mockito.anyString())).thenReturn(mockResponse);

		mockMvc.perform(get("/game/123")
				.accept(MEDIA_TYPE_JSON_UTF8).contentType(MEDIA_TYPE_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.[0].result").value("Draw"));	
	}
	
	@Test
	void getAllSessionsTest() throws Exception {
		ConcurrentHashMap<String, ArrayList<Game>> mockResponse = new ConcurrentHashMap<>();
		ArrayList<Game> gamesList = new ArrayList<>();

		Game game = new Game();
		game.setResult("Draw");
		
		gamesList.add(game);
		mockResponse.put("123", gamesList);
		        
		Mockito.when(service.getAllSessions()).thenReturn(mockResponse);

		mockMvc.perform(get("/game/get-all-sessions")
				.accept(MEDIA_TYPE_JSON_UTF8).contentType(MEDIA_TYPE_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").value(Matchers.hasKey("123")));	
	}
	
	@Test
	void deleteSessionByIdTest() throws Exception {
		ConcurrentHashMap<String, ArrayList<Game>> mockResponse = new ConcurrentHashMap<>();
		ArrayList<Game> gamesList = new ArrayList<>();

		Game game = new Game();
		game.setResult("Draw");
		
		gamesList.add(game);
		mockResponse.put("123", gamesList);
		        
		Mockito.when(service.getAllSessions()).thenReturn(mockResponse);

		mockMvc.perform(delete("/game/clear-session/123")
				.accept(MEDIA_TYPE_JSON_UTF8).contentType(MEDIA_TYPE_JSON_UTF8))
		.andExpect(status().isNoContent());
	}
	
	

	
}
