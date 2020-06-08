import {Component, OnInit} from '@angular/core';
import {ApiService} from '../common/api.service';
import {Task} from '../model/task';
import {Router} from "@angular/router";
import {TaskStateService} from "../common/task-state.service";
import {LocalStorageService} from "../common/local-storage.service";
import {MatDialog, MatDialogConfig} from "@angular/material";
import {CreateTaskDialogComponent} from "../create-task-dialog/create-task-dialog.component";

@Component({
  selector: 'app-task-bar',
  templateUrl: './task-bar.component.html',
  styleUrls: ['./task-bar.component.css']
})
export class TaskBarComponent implements OnInit {

  constructor(private apiService: ApiService,
              private router: Router,
              private taskStateService: TaskStateService,
              private localStorageService : LocalStorageService,
              private dialog: MatDialog) {
  }

  tasks: Task[];

  ngOnInit() {
    console.log("task bar init");
    this.apiService.getAllTasks().subscribe(response => {
      this.tasks = response;
    }, error => {
      console.log(error);
    });
    console.log("TaskBarComponent Initialized");
  }

  openTask(id: string) {
    this.taskStateService.task = this.tasks.find(task => task.id === id);
    this.router.navigate(['/workspace']);
  }

  private isAdmin() {
    return this.localStorageService.getItem("userRole") === 'ROLE_ADMIN';
  }

  openCreateTaskDialog() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(CreateTaskDialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(val => {
      console.log("Dialog Output:", val);
      this.createNewTask(val);
    });
  }

  createNewTask(task : Task) {
      console.log("POST TASK : " + task);
  }



}
