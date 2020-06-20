import {NbAuthResult, NbAuthStrategy, NbAuthStrategyClass, NbPasswordAuthStrategyOptions} from '@nebular/auth';
import {Injectable} from '@angular/core';
import {Observable, of as observableOf} from 'rxjs';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {NbAuthStrategyOptions} from '@nebular/auth/strategies/auth-strategy-options';
import {ApiError, JwtToken, User} from '../../common/interfaces';
import {BackEndUrls} from '../../common/config';
import {catchError, map} from 'rxjs/operators';
import {NbJwtToken} from '../../common/util';
import {CryptoUtil, ErrorHandlingUtil, LoggerUtil} from '../util';
import {ErrorName} from '../../common/constant';

@Injectable({providedIn: 'root'})
export class TokenStrategyFacadeService extends NbAuthStrategy {

  static setup(options: NbAuthStrategyOptions): [NbAuthStrategyClass, NbPasswordAuthStrategyOptions] {
    return [TokenStrategyFacadeService, options];
  }

  constructor(protected httpClient: HttpClient,
              private cryptoUtil: CryptoUtil,
              private loggerUtil: LoggerUtil,
              private errorHandlingUtil: ErrorHandlingUtil) {
    super();
  }

  authenticate(data?: any): Observable<NbAuthResult> {
    const userData = <User> data;

    userData.password = this.encrypt(userData.password);

    const api = BackEndUrls.API_ENDPOINT(BackEndUrls.API_LOGIN);

    return this.httpClient.post<JwtToken>(api, userData, {observe: 'response'}).pipe(
      map(
        (response: HttpResponse<JwtToken>) =>
          new NbAuthResult(true, response, null, [], [], new NbJwtToken(response.body.token, this.getName()))
      ),
      catchError((error: any, caught: Observable<NbAuthResult>) => {
          this.loggerUtil.error(error);
          const errorCodesOrNames: string[] = [];

          if (<ApiError> error.error) {
            errorCodesOrNames.push(...this.errorHandlingUtil.codesOf(<ApiError> error.error));
          } else {
            errorCodesOrNames.push(...ErrorName.getInstance().UNEXPECTED_ERROR);
          }
          return observableOf(new NbAuthResult(false, caught, null, errorCodesOrNames));
        }
      ));
  }


  logout(): Observable<NbAuthResult> {
    console.log('logout method call');
    return undefined;
  }

  refreshToken(data?: any): Observable<NbAuthResult> {
    console.log('refreshToken method call');
    return undefined;
  }

  register(data?: any): Observable<NbAuthResult> {
    console.log('register method call');
    return undefined;
  }

  requestPassword(data?: any): Observable<NbAuthResult> {
    console.log('requestPassword method call');
    return undefined;
  }

  resetPassword(data?: any): Observable<NbAuthResult> {
    console.log('resetPassword method call');
    return undefined;
  }

  private encrypt(password: string) {
    const aesKey = this.cryptoUtil.generateAesKey();
    const encryptedKey = this.cryptoUtil.encryptRsa(aesKey);
    const encryptedPassword = this.cryptoUtil.encryptAes(password, aesKey);
    return this.cryptoUtil.encode64(encryptedKey) + ';' + this.cryptoUtil.encode64(encryptedPassword);
  }
}
