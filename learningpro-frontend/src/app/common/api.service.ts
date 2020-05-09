import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class ApiService {
  constructor(private httpClient: HttpClient) {}
  baseUrl = 'http://localhost:8080/';

  login(loginPayload) {
    const headers = {
      Authorization: 'Basic ' + btoa('learning-pro:test-secret'),
      'Content-type' : 'application/x-www-form-urlencoded',
      'Access-Control-Allow-Origin': '*'
    };
    return this.httpClient.post(this.baseUrl + 'oauth/token', loginPayload, {headers});
  }

}
