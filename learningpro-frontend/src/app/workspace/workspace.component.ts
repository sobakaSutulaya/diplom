import {Component, OnInit} from '@angular/core';
import {TaskStateService} from "../common/task-state.service";

@Component({
  selector: 'app-workspace',
  templateUrl: './workspace.component.html',
  styleUrls: ['./workspace.component.css']
})
export class WorkspaceComponent implements OnInit {

  constructor(private taskStateService: TaskStateService) { }

  ngOnInit() {
    console.log(this.taskStateService.task);
  }

}
