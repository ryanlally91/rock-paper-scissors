import { Component } from "@angular/core";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { DataSource } from '@angular/cdk/collections';
import {MatTableDataSource} from '@angular/material/table';
import { GameService } from './game.service';
import { OverallGame } from "./models/OverallGame";
import { GameData, Move } from "./models/GameData";
import { ThisReceiver } from "@angular/compiler";



@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
})
export class AppComponent {
  title = "rps-ui";

  dataSource = new MatTableDataSource<GameData>();

  dataSourceTotal: OverallGame = {
  totalPlayer1Wins: 0,
	totalPlayer2Wins: 0,
	totalDraws: 0,
  totalRoundsPlayed: 0,
};

  displayedColumns = ['Player1Choice', 'Player2Choice', 'Result'];
  displayedColumnsTotal = ['Total P1 Wins', 'Total P2 Wins', 'Total Draws', 'Total Games'];

  totalGamesPlayed: number = 0;
  isTotalInfoDisplayed = false;

  sessionId = '';


  constructor(private gameService: GameService) {}

  ngOnInit(){
    this.sessionId = this.createUUID();
  }

  tableRefresh() {
   this.gameService.getAllGamesInThisSession(this.sessionId).subscribe(
    data => {
    debugger;
    console.log('Filling Data Source: ' + data);
    this.dataSource = data;
  }
);

this.gameService.getOverAllGameScores().subscribe(
    data => {
    debugger;
    console.log('Filling total Data Source: ' + data);
    this.dataSourceTotal = data;
  }
);

}

 createUUID() {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
    var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
    return v.toString(16);
  });
}


 



  playRandomGame() {
    let game: GameData = {
      sessionId: this.sessionId,
      player1Choice: Move.PAPER,
      player2Choice: Move.PAPER,
      result: "",
    };
    this.gameService.playRandomGameService(game).subscribe((response: any) => {
      console.log(response);
    });
    this.updateAllGameData()
  }

  updateAllGameData(){
      this.tableRefresh();
      this.gameService.updateTotalGamesPlayed().subscribe((response: any) => {
      console.log(response);
    });
  }


// INCORRECT
  resetGameData(){
    //   let overall: OverallGame = {
    //     totalRoundsPlayed: this.totalGamesPlayed,
    //     totalPlayer1Wins: 0,
	  //     totalPlayer2Wins: 0,
	  //     totalDraws: 0
    // };
    
    // this.gameService.resetGameDataService(overall).subscribe((response: any) => {
    //   console.log('Resetting game' + response);
    // });
    // this.updateAllGameData();

    this.gameService.clearSessionDataService(this.sessionId).subscribe((response: any) => {
        console.log('Clearing session data' + response);
      });
      this.updateAllGameData();
  }



  showTotalsInfo(){
    this.isTotalInfoDisplayed = !this.isTotalInfoDisplayed;
  }

}







