import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Principal } from '../model/principal';
import { Observable, BehaviorSubject } from 'rxjs';
import { map } from 'rxjs/operators';
import { LocalStorageService } from './local-storage.service';
import { ApiPath } from './api-path';
import { Router } from '@angular/router';


@Injectable()
export class AuthService {

    constructor(private httpClient: HttpClient,
        private localStorageService: LocalStorageService,
        private router: Router) { }

    loggedIn = new BehaviorSubject<boolean>(false);

    get isLoggedIn() {
        return this.loggedIn.asObservable();
    }

    setLoggedIn(isLoggedIn: boolean) {
        this.loggedIn.next(isLoggedIn);
    }

    login(loginPayload) {
        const headers = {
            Authorization: 'Basic ' + btoa('learning-pro:test-secret'),
            'Content-type': 'application/x-www-form-urlencoded'
        };
        return this.httpClient.post(ApiPath.OAUTH_TOKEN, loginPayload, { headers });
    }

    getPrincipal(): Observable<Principal> {
        let authHeader = {
            headers: { Authorization: ApiPath.BEARER + this.getToken() }
        };
        return this.httpClient.get<Principal>(ApiPath.GET_PRINCIPAL, authHeader);
        // return this.httpClient.get<Principal>(ApiPath.GET_PRINCIPAL, authHeader).pipe(
        //     map((data: Principal) => {
        //         const user = new Principal();
        //         Object.assign(user, data);
        //         return user;
        //     }));
    }

    getUserId(login) {
        const httpOptions = {
            headers: { Authorization: ApiPath.BEARER + this.getToken() },
            params: { 'login': login }
        };

        return this.httpClient.get(ApiPath.GET_USER_ID, httpOptions);
    }

    logout() {
        this.localStorageService.clear();
        this.loggedIn.next(false);
        this.router.navigate(['login']);
    }

    private getToken(): string {
        return this.localStorageService.getItem('token').access_token;
    }
}
