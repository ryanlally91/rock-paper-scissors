export interface GameData {
    sessionId: string;
    player1Choice: Move;
    player2Choice: Move;
    result: string;
  }

  export enum Move {
    ROCK, PAPER, SCISSORS
  }