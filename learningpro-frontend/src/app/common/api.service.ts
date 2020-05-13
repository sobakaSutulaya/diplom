import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Principal } from '../model/principal';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable()
export class ApiService {
  constructor(private httpClient: HttpClient) { }
  baseUrl = 'http://localhost:8080/';
  apiPrefixUrl = this.baseUrl + 'learning-pro/';


  login(loginPayload) {
    const headers = {
      Authorization: 'Basic ' + btoa('learning-pro:test-secret'),
      'Content-type': 'application/x-www-form-urlencoded'
      // 'Access-Control-Allow-Origin': '*'
    };
    return this.httpClient.post(this.baseUrl + 'oauth/token', loginPayload, { headers });
  }

  getPrincipal(): Observable<Principal> {
    return this.httpClient.get<Principal>(this.apiPrefixUrl + 'users/me' + this.getToken()).pipe(
      map((data: Principal) => {
        const user = new Principal();
        Object.assign(user, data);
        return user;
      }));
  }

  getUserId(login) {
    return this.httpClient.get(this.apiPrefixUrl + "users/id/" + login + this.getToken());
  }

  private getToken(): string {
    return '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
  }
}
