package com.sessionmanager.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.sessionmanager.dto.GameDto;
import com.sessionmanager.dto.ResponseDto;
import com.sessionmanager.entity.Game;
import com.sessionmanager.entity.OverallGame;

public interface GameService {
	public ResponseDto addGameToSession(GameDto gameDto);
	public ArrayList<Game> findGamesBySessionId(String sesssionId);	
	public HashMap<String, ArrayList<Game>> getAllSessions();
	public OverallGame getOverallGameScores();
	public int getTotalRoundsPlayed();
	public OverallGame resetGameData();
	public String deleteGamesBySessionId(String sesssionId);
	
}
