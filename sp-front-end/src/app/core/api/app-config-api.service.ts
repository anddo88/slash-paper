import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AppConfig} from '../../common/interfaces';
import {BackEndUrls} from '../../common/config';

@Injectable({providedIn: 'root'})
export class AppConfigApi {

  readonly API = BackEndUrls.API_ENDPOINT(BackEndUrls.API_APP_CONFIG);

  constructor(private http: HttpClient) {
  }

  getAppConfig$(): Observable<AppConfig> {
    return this.http.get<AppConfig>(this.API);
  }

}
