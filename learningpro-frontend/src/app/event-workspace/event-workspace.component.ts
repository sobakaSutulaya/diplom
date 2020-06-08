import { Component, OnInit } from '@angular/core';
import {EventStateService} from "../common/event-state.service";
import {Task} from '../model/task';
import {Event} from "../model/event";

@Component({
  selector: 'app-event-workspace',
  templateUrl: './event-workspace.component.html',
  styleUrls: ['./event-workspace.component.css']
})
export class EventWorkspaceComponent implements OnInit {

  constructor(private eventStateService : EventStateService) { }

  event: Event;
  currentTask: Task;

  ngOnInit() {
    this.event = this.eventStateService.event;
    console.log("EventWorkspaceComponent Initialized");
  }



}
