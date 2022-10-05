import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GameServiceService {

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:59503/users';
  }

    public findAll(): Observable<User[]> {
    return this.http.get<string>(this.usersUrl);
  }

  }
