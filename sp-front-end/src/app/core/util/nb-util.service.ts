import {Injectable} from '@angular/core';
import {NbComponentStatus, NbGlobalPosition, NbThemeService, NbToastrService} from '@nebular/theme';
import {Themes} from '../../common/constant';

@Injectable({providedIn: 'root'})
export class NbUtil {

  constructor(private nbToastrService: NbToastrService,
              private nbThemeService: NbThemeService) {
  }

  private toastService(): NbToastrService {
    return this.nbToastrService;
  }

  private themeService(): NbThemeService {
    return this.nbThemeService;
  }

  showToast(message: string, title: string, position: NbGlobalPosition, status: NbComponentStatus) {
    this.toastService().show(message, title, {position: position, status: status});
  }

  dangerToast(message: string, title: string, position: string | NbGlobalPosition) {
    this.toastService().danger(message, title, {position: position as NbGlobalPosition});
  }

  changeTheme(theme: Themes): void {
    this.themeService().changeTheme(Themes[theme]);
  }


}
