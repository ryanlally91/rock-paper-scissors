package com.sessionmanager.session;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.sessionmanager.dto.ResponseDto;
import com.sessionmanager.entity.Game;
import com.sessionmanager.game.logic.Move;

@Repository
public class SessionInMemoryRegistry {

	//Note: Static - The static variable gets memory only once in the class area at the time of class loading.
	private static final HashMap<String, ArrayList<Game>> GAME_SESSIONS = new HashMap<>();

	public ArrayList<Game> findGamesBySessionId(String sessionId) {
		return GAME_SESSIONS.get(sessionId);
	}

	public HashMap<String, ArrayList<Game>> getAllSessions(){
		return GAME_SESSIONS;
	}

	public ResponseDto addGameSession(String sessionId, Move player1Move, Move player2Move, String result) {

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

	public String deleteGamesBySessionId(String sesssionId) {
		GAME_SESSIONS.remove(sesssionId);
		return sesssionId;
}
}
