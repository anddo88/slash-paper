import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {AppConfig} from '../../common/interfaces';

@Injectable({providedIn: 'root'})
export class AppConfigState {

  private appConfig$ = new BehaviorSubject<AppConfig>(JSON.parse(localStorage.getItem('app_config')));
  private updating$ = new BehaviorSubject<boolean>(false);
  private initialized = JSON.parse(localStorage.getItem('app_config')) !== null;

  setAppConfig(value: AppConfig) {
    this.updating$.next(true);

    localStorage.setItem('app_config', JSON.stringify(value));
    this.appConfig$.next(value);

    this.initialized = true;
    this.updating$.next(false);
  }

  isUpdating$() : Observable<boolean> {
    return this.updating$.asObservable();
  }

  isInitialized() : boolean {
    return this.initialized;
  }

}
