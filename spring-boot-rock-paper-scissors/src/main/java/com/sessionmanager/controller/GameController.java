package com.sessionmanager.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sessionmanager.dto.GameDto;
import com.sessionmanager.dto.ResponseDto;
import com.sessionmanager.entity.Game;
import com.sessionmanager.entity.OverallGame;
import com.sessionmanager.service.GameService;
import com.sessionmanager.service.GameServiceImpl;

@RestController
@RequestMapping("/game")
@CrossOrigin(origins = "http://localhost:4200")
public class GameController {
	
	@Autowired
	private GameService service;	
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> create(@RequestBody GameDto gameDto) {
		ResponseDto rdto = service.addGameToSession(gameDto);
	    return new ResponseEntity<>(rdto, HttpStatus.CREATED);

	}
	
	@RequestMapping("/{sessionId}")
	public ArrayList<Game> findGamesBySessionId(@PathVariable String sessionId) {
		return service.findGamesBySessionId(sessionId);
	}
	
	@RequestMapping("/get-all-sessions")
	public HashMap<String, ArrayList<Game>> getAllSessions(){
		return service.getAllSessions();
	}
	
	@RequestMapping("/get-overall-game-scores")
	public OverallGame getOverallGameScores(){
		return service.getOverallGameScores();
	}
	
	@RequestMapping("/get-total-rounds-played")
	public int getTotalRoundsPlayed(){
		return service.getTotalRoundsPlayed();
	}
		
	@DeleteMapping("/clear-session/{sessionId}")
	public ResponseEntity<String> deleteGamesBySessionId(@PathVariable String sessionId) {
		service.deleteGamesBySessionId(sessionId);

	    return new ResponseEntity<>(sessionId, HttpStatus.NO_CONTENT);
	}

}

