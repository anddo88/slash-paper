import {EventEmitter, Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Injectable({providedIn: 'root'})
export class EventUtil {

  localeViewRendered = new BehaviorSubject<boolean>(false);

  constructor() {
  }

  localeViewsRendered(isRendered: boolean) {
    this.localeViewsRendered(isRendered);
  }
}
