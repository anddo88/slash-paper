import {Injectable, isDevMode} from '@angular/core';

@Injectable({providedIn: 'root'})
export class LoggerUtil {

  error(message: any) {
    if(window.console) {
      console.log(message);
    }
  }
}
