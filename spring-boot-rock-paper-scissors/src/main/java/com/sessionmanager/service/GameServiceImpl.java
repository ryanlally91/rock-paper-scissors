package com.sessionmanager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sessionmanager.dto.GameDto;
import com.sessionmanager.dto.ResponseDto;
import com.sessionmanager.entity.Game;
import com.sessionmanager.entity.OverallGame;
import com.sessionmanager.game.logic.GamePlay;
import com.sessionmanager.repository.GameInMemoryRepo;
import com.sessionmanager.session.SessionInMemoryRegistry;

@Service("gameService")
public class GameServiceImpl implements GameService{
	
	@Autowired
	private SessionInMemoryRegistry sessionRegistry;
	
	@Autowired
	private GameInMemoryRepo repo;
	
	@Autowired
	private GamePlay gameplay;

	@Override
	public ResponseDto addGameToSession(GameDto gameDto) {
		gameDto = gameplay.playRandomGame(gameDto);
		repo.updateTotals(gameDto.getResult());
		return sessionRegistry.addGameSession(gameDto.getSessionId(), gameDto.getPlayer1Choice(), gameDto.getPlayer2Choice(), gameDto.getResult());
	}
	

	
	@Override
	public ArrayList<Game> findGamesBySessionId(String sesssionId){
		return sessionRegistry.findGamesBySessionId(sesssionId);
	}
	
	@Override
	public ConcurrentHashMap<String, ArrayList<Game>> getAllSessions(){
		return sessionRegistry.getAllSessions();
	}
	
	@Override
	public OverallGame getOverallGameScores(){
		return repo.getOverallGameScores();
	}
	
	@Override
	public int getTotalRoundsPlayed(){
		return repo.getTotalRoundsPlayed();
	}
	
	@Override
	public OverallGame resetGameData(){
		 return repo.resetGameData();
	}

	@Override
	public String deleteGamesBySessionId(String sesssionId) {
		 return sessionRegistry.deleteGamesBySessionId(sesssionId);		
	}
}
