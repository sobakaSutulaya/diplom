import { Component, OnInit } from '@angular/core';
import { ApiService } from '../common/api.service';
import { Event } from '../model/event';

@Component({
  selector: 'app-event-bar',
  templateUrl: './event-bar.component.html',
  styleUrls: ['./event-bar.component.css']
})
export class EventBarComponent implements OnInit {

  constructor(private apiService: ApiService) { }

  events: Event[]

  ngOnInit() {
    this.apiService.getAllEvents().subscribe(response => {
      console.log(response);
      this.events = response;
    }, error => {
      console.log("events request failed");
      console.log(error);
    });
  }

  openTask(id: string) {
    console.log(id);
  }

}
