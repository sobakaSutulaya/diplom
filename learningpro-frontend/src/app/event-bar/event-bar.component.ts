import {Component, OnInit} from '@angular/core';
import {ApiService} from '../common/api.service';
import {Event} from '../model/event';
import {LocalStorageService} from "../common/local-storage.service";
import {EventStateService} from "../common/event-state.service";
import {Router} from "@angular/router";
import {MatDialog, MatDialogConfig} from "@angular/material";
import {CreateEventDialogComponent} from "../create-event-dialog/create-event-dialog.component";

@Component({
  selector: 'app-event-bar',
  templateUrl: './event-bar.component.html',
  styleUrls: ['./event-bar.component.css']
})
export class EventBarComponent implements OnInit {

  constructor(private apiService: ApiService,
              private router : Router,
              private localStorageService: LocalStorageService,
              private eventStateService : EventStateService,
              private dialog: MatDialog) { }

  events: Event[];

  ngOnInit() {
    this.apiService.getAllEvents().subscribe(response => {
      console.log(response);
      this.events = response;
    }, error => {
      console.log("events request failed");
      console.log(error);
    });
    console.log("EventBarComponent Initialized");
  }

  openEvent(id: string) {
    this.eventStateService.event = this.events.find(event => event.id === id);
    this.router.navigate(['/event-workspace'])
  }

  isAdmin() {
    return this.localStorageService.getItem("userRole") === 'ROLE_ADMIN';
  }

  openCreateEventDialog() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(CreateEventDialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(val => {
      console.log("Dialog Output:", val);
      this.createNewEvent(val);
    });
  }

  createNewEvent(event: Event) {
    console.log("POST EVENT : " + event)
  }

}
