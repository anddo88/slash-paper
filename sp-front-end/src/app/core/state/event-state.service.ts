import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({providedIn: 'root'})
export class EventState {

  private _localeViewRendered$ = new BehaviorSubject<boolean>(false);

  localeViewRendered(isRendered: true) {
    this._localeViewRendered$.next(isRendered);
  }

  localeViewRendered$(): Observable<boolean> {
    return this._localeViewRendered$.asObservable();
  }
}
