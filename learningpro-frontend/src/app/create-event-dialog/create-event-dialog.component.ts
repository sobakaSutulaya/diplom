import {Component, OnInit} from '@angular/core';
import {ApiService} from "../common/api.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatDialogRef} from "@angular/material";
import {Task} from "../model/task";

@Component({
  selector: 'app-create-event-dialog-component',
  templateUrl: './create-event-dialog.component.html',
  styleUrls: ['./create-event-dialog.component.css']
})
export class CreateEventDialogComponent implements OnInit {

  form : FormGroup;
  groups: Array<String>;
  tasks: Task[];

  constructor(private apiService : ApiService,
              private formBuilder: FormBuilder,
              private dialogRef: MatDialogRef<CreateEventDialogComponent>) {

    this.formBuilder.group({
      name : ['', Validators.required],
      description: ['', Validators.required],
      taskIds: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      subject: ['', Validators.required],
      courseNumber: ['', Validators.required],
      groupNames: ['', Validators.required]
    })
  }

  ngOnInit() {
    this.loadGroups();
    this.loadTasks();
    console.log("CreateEventDialogComponent Initialized")
  }

  save() {
    this.dialogRef.close(this.form.value)
  }

  close() {
    this.dialogRef.close();
  }

  loadGroups() {
      this.apiService.getAllGroupNames().subscribe(response => {
        console.log(response);
        this.groups = response;
      }, error => {
        console.log(error)
      })
  }

  loadTasks() {
    this.apiService.getAllTasks().subscribe(response => {
      this.tasks = response;
    }, error => {
      console.log(error);
    })
  }
}
