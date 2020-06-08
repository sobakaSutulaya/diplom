import {Component, Inject, OnInit} from '@angular/core';
import {Task} from "../model/task";
import {ApiService} from "../common/api.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-create-task-dialog-component',
  templateUrl: './create-task-dialog.component.html',
  styleUrls: ['./create-task-dialog.component.css']
})
export class CreateTaskDialogComponent implements OnInit {

  form: FormGroup;

  constructor(private apiService : ApiService,
              private formBuilder: FormBuilder,
              private dialogRef: MatDialogRef<CreateTaskDialogComponent>) {

    this.form = formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      courseNumber: ['', Validators.required],
      subject: ['', Validators.required]
    })
  }

  ngOnInit() {
    console.log("CreateTaskDialogComponent Initialized")
  }

  save() {
    this.dialogRef.close(this.form.value)
  }

  close() {
    this.dialogRef.close();
  }


}
