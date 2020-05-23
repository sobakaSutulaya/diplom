import {Injectable} from "@angular/core";
import {BehaviorSubject} from "rxjs";
import {Event} from "../model/event";

@Injectable()
export class EventStateService {

  private readonly _event = new BehaviorSubject<Event>(null);
  readonly event$ = this._event.asObservable();

  get event() {
    return this._event.getValue();
  }

  set event(event : Event) {
    this._event.next(event);
  }
}
