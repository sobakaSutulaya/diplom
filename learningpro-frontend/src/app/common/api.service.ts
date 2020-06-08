import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LocalStorageService} from './local-storage.service';
import {ApiPath} from './api-path';
import {Task} from '../model/task';
import {Event} from '../model/event';
import {Observable} from "rxjs";


@Injectable()
export class ApiService {

    constructor(private httpClient: HttpClient, private localStorage: LocalStorageService) { }

    private getToken(): string {
        return this.localStorage.getItem('token').access_token;
    }

    getAllTasks() {
        return this.httpClient.get<Task[]>(ApiPath.GET_ALL_TASKS, this.authHeader());
    }

    getAllEvents() {
        return this.httpClient.get<Event[]>(ApiPath.GET_ALL_EVENTS, this.authHeader());
    }

    createTask(task: Task) {
      return this.httpClient.post(ApiPath.POST_CREATE_TASK, JSON.stringify(task), this.authHeader());
    }

    getAllGroupNames() {
      return this.httpClient.get<Array<string>>(ApiPath.GET_ALL_GROUPS, this.authHeader())
    }

    private authHeader() {
      return {
          headers: {Authorization: ApiPath.BEARER + this.getToken()}
        };
    }
}
