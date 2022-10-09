package com.sessionmanager.dto;

import java.util.ArrayList;

import com.sessionmanager.entity.Game;

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
public class ResponseDto {
	
	private String sessionId;
	private ArrayList<Game> session;
}

