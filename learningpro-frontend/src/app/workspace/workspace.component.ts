import {Component, OnDestroy, OnInit} from '@angular/core';
import {TaskStateService} from "../common/task-state.service";
import {Task} from "../model/task";

@Component({
  selector: 'app-workspace',
  templateUrl: './workspace.component.html',
  styleUrls: ['./workspace.component.css']
})
export class WorkspaceComponent implements OnInit, OnDestroy {

  constructor(private taskStateService: TaskStateService) { }

  task: Task;

  ngOnInit() {
    this.task = this.taskStateService.task;
    console.log(this.task);
  }

  ngOnDestroy() {
    this.task = null;
  }

}
