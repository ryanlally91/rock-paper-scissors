package com.sessionmanager.dto;

import com.sessionmanager.entity.Game;
import com.sessionmanager.game.logic.Move;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor 
@AllArgsConstructor
public class GameDto {

	private String sessionId;
	private Move player1Choice;
	private Move player2Choice;
	private String result;


}
