import {Injectable} from '@angular/core';
import {NbAuthResult, NbAuthService} from '@nebular/auth';
import {Observable} from 'rxjs';
import {User} from '../../common/interfaces';

@Injectable({providedIn: 'root'})
export class UserManagementFacade {
  constructor(private nbAuthService: NbAuthService) {
  }

  authenticate(strategyName: string, data: User): Observable<NbAuthResult> {
    return this.nbAuthService.authenticate(strategyName, data);
  }
}
