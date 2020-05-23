import {Injectable} from "@angular/core";
import {BehaviorSubject} from "rxjs";
import {Task} from "../model/task";

@Injectable()
export class TaskStateService {

  private readonly _task = new BehaviorSubject<Task>(null);
  readonly task$ = this._task.asObservable();

  constructor() {
    console.log("Create instance of state service : " + Date.now());
  }

  get task() {
    return this._task.getValue();
  }

  set task(task : Task) {
    this._task.next(task)
  }




}
