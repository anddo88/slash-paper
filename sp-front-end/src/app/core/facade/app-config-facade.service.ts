import {Injectable} from '@angular/core';
import {AppConfigApi} from '../api';
import {AppConfigState} from '../state';
import {shareReplay, tap} from 'rxjs/operators';
import {Observable} from 'rxjs';
import {AppConfig} from '../../common/interfaces';

@Injectable({providedIn: 'root'})
export class AppConfigFacade {
  constructor(private appConfigApi: AppConfigApi, private appConfigState: AppConfigState) {
  }

  isUpdating$(): Observable<boolean> {
    return this.appConfigState.isUpdating$();
  }

  getAppConfig$(): Observable<AppConfig> {
    return null;
  }
}
