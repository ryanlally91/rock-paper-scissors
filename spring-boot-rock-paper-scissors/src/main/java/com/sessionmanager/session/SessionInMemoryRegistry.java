package com.sessionmanager.session;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.sessionmanager.dto.ResponseDto;
import com.sessionmanager.entity.Game;
import com.sessionmanager.game.logic.Move;

@Repository
public class SessionInMemoryRegistry {

	//Note: Static - The static variable gets memory only once in the class area at the time of class loading.
	//This is essentially our in-memory DB. we use a key-value system to store all games for a given sessionId/user
	private static final ConcurrentHashMap<String, ArrayList<Game>> GAME_SESSIONS = new ConcurrentHashMap<>();

	public ArrayList<Game> findGamesBySessionId(String sessionId) {
		return GAME_SESSIONS.get(sessionId);
	}

	public ConcurrentHashMap<String, ArrayList<Game>> getAllSessions(){
		return GAME_SESSIONS;
	}

	public ResponseDto addGameSession(String sessionId, Move player1Move, Move player2Move, String result) {

		//Creating session IDs in service layer here simply so we can use Postman to test this
		ArrayList<Game> gameSession;
		if(sessionId.isEmpty()||sessionId==null) {
			sessionId = generateSessionId();
		}
		if(GAME_SESSIONS.get(sessionId)!=null) {
			gameSession = GAME_SESSIONS.get(sessionId);
		}
		else {
			gameSession = new ArrayList<>();
		}
		gameSession.add(new Game(player1Move, player2Move, result));
		GAME_SESSIONS.put(sessionId, gameSession);
		ResponseDto rdto = new ResponseDto(sessionId,GAME_SESSIONS.get(sessionId));
		return rdto;
	}
	
	private static String generateSessionId(){
		return new String(
				Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)
						)
				);
	}
	
	//We can 'clear the session' by deletingall games associated with that sessionId
	public String deleteGamesBySessionId(String sesssionId) {
		GAME_SESSIONS.remove(sesssionId);
		return sesssionId;
}
}
