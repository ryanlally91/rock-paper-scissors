import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GameData } from './models/GameData';
import { OverallGame } from './models/OverallGame';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(private http: HttpClient) { }

  userUrl = 'http://localhost:8080/game/';

  public getAllSessions(): Observable<any> {
    const usersUrl = this.userUrl + "get-all-sessions";
    console.log('All Sessions: ' + this.http.get<any>(usersUrl));
    return this.http.get<any>(usersUrl);
  }

    public getAllGamesInThisSession(sessionId : string): Observable<any> {
    const usersUrl = this.userUrl + sessionId;
    console.log('All Sessions: ' + this.http.get<any>(usersUrl));
    return this.http.get<any>(usersUrl);
  }

  updateTotalGamesPlayed(): Observable<number> {
    const usersUrl = this.userUrl + "get-total-rounds-played";
    console.log('Total Games Played : ' + this.http.get<number>(usersUrl));
    // this.http.get<number>(usersUrl).subscribe(data => this.totalGamesPlayed = data);
    return this.http.get<number>(usersUrl);
  }
  

    getOverAllGameScores(): Observable<OverallGame> {
    const usersUrl = this.userUrl + "get-overall-game-scores";
    console.log('over all games table service : ' + this.http.get<OverallGame>(usersUrl));
    return this.http.get<OverallGame>(usersUrl);
  }

    public playRandomGameService(game: GameData): Observable<any> {
    const usersUrl = this.userUrl + "create";
    debugger;
    return this.http.post<GameData>(usersUrl, game);
  }

    public resetGameDataService(overallGame: OverallGame) {
    const usersUrl = this.userUrl + "reset-game-data";
    debugger;
    return this.http.put<OverallGame>(usersUrl, overallGame);
  }

  public clearSessionDataService(sessionId : string) {
    const usersUrl = this.userUrl + "clear-session/" + sessionId;
    return this.http.delete<any>(usersUrl);
  }
  
}
