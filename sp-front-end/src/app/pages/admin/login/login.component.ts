import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Subject} from 'rxjs';
import {takeUntil} from 'rxjs/operators';

import {NbAuthResult} from '@nebular/auth';

import {LocaleWord, Themes} from '../../../common/constant';
import {Theme} from '../../../common/interfaces';

import {EventFacade, UserManagementFacade} from '../../../core/facade';
import {ErrorHandlingUtil, LocaleHandlingUtil, NbUtil} from '../../../core/util';

@Component({
  selector: 'app-login',
  styleUrls: ['login.component.scss'],
  templateUrl: 'login.component.html',
})
export class LoginComponent implements OnInit, OnDestroy {

  private destroy$ = new Subject<void>();
  private readonly loginFormControls = {username: 'username', password: 'password', remember: 'remember'};

  themes: Theme[];
  submitted: boolean;
  rememberMe: boolean; // TODO
  loginForm: FormGroup;
  errors: string[];
  messages: string[];
  showMessages: { success: boolean, error: boolean }; // show/not show success/error messages

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private changeDetectorRef: ChangeDetectorRef,
              // Facades to retrieve data
              private userManagementFacade: UserManagementFacade,
              private eventFacade: EventFacade,
              // Utils
              private errorHandlingUtil: ErrorHandlingUtil,
              private localeHandlingUtil: LocaleHandlingUtil,
              private nbUtil: NbUtil) {

  }

  ngOnInit(): void {
    this.submitted = false;
    this.rememberMe = false;
    this.errors = [];
    this.messages = [];
    this.showMessages = {success: true, error: true};
    this.loginForm = this.formBuilder.group(
      {username: '', password: ''}
    );

    this.eventFacade.localeViewRendered$().pipe(takeUntil(this.destroy$))
      .subscribe((localeViewRendered: boolean) => {
        if (localeViewRendered) {
          this.themes = Object.keys(Themes).map(themeName => {
            return {name: themeName, translatedName: this.localeHandlingUtil.translationOf(themeName)};
          });
          this.changeDetectorRef.detectChanges();
        }
      });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  get usernameFormControl() {
    return this.loginForm.get(this.loginFormControls.username);
  }

  get passwordFormControl() {
    return this.loginForm.get(this.loginFormControls.password);
  }

  login(): void {
    const userCredentials = {username: this.usernameFormControl.value, password: this.passwordFormControl.value};
    this.userManagementFacade.authenticate('jwt', userCredentials).subscribe(
      (authResult: NbAuthResult) => {
        this.errors = [];
        console.log(authResult);
        if (authResult.isSuccess()) {
          this.router.navigate(['/pages/admin/users']).then(navigation => console.log(navigation.valueOf()));
        } else {
          this.errors.push(...this.errorHandlingUtil.messagesOfCodes(authResult.getErrors()));
        }
        this.errors.forEach(error => this.nbUtil.dangerToast(error, this.localeHandlingUtil.translationOf(LocaleWord.getInstance().NOTIFICATION_TITLE), 'bottom-right'));

      });
  }

  changeTheme(theme: Themes) {
    this.nbUtil.changeTheme(theme);
  }


}
