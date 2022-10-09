package com.sessionmanager.entity;

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
public class OverallGame {
	
	private int totalPlayer1Wins;
	private int totalPlayer2Wins;
	private int totalDraws;
	private int totalRoundsPlayed;


}
