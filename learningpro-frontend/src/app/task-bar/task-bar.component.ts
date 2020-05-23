import {Component, OnInit} from '@angular/core';
import {ApiService} from '../common/api.service';
import {Task} from '../model/task';
import {Router} from "@angular/router";
import {TaskStateService} from "../common/task-state.service";
import {LocalStorageService} from "../common/local-storage.service";

@Component({
  selector: 'app-task-bar',
  templateUrl: './task-bar.component.html',
  styleUrls: ['./task-bar.component.css']
})
export class TaskBarComponent implements OnInit {

  constructor(private apiService: ApiService
    , private router: Router,
              private taskStateService: TaskStateService,
              private localStorageService : LocalStorageService) {
  }

  tasks: Task[];

  ngOnInit() {
    console.log("task bar init");
    this.apiService.getAllTasks().subscribe(response => {
      this.tasks = response;
    }, error => {
      console.log(error);
    });
  }

  openTask(id: string) {
    this.taskStateService.task = this.tasks.find(task => task.id === id);
    this.router.navigate(['/workspace']);
  }

  private isAdmin() {
    return this.localStorageService.getItem("userRole") === 'ROLE_ADMIN';
  }



}
