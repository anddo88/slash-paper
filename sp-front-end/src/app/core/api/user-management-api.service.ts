import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {BackEndUrls} from '../../common/config';

@Injectable({providedIn: 'root'})
export class UserManagementApi {

  readonly API = BackEndUrls.API_ENDPOINT(BackEndUrls.API_LOGIN);

  constructor(private http: HttpClient) {
  }

}
