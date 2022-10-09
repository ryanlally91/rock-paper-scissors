package com.sessionmanager.entity;

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
public class Game {
	
	private Move player1Choice;
	private Move player2Choice;
	private String result;

	

}
