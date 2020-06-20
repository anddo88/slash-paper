import {Component} from '@angular/core';
import {InitializationUtil} from '../core/util';

@Component({
  selector: 'app-pages',
  template: `
    <router-outlet></router-outlet>
    <app-error-translate>
    </app-error-translate>
    <app-other-translate>
    </app-other-translate>`,
})
export class PagesComponent {

  constructor(private initializationUtil: InitializationUtil) {
    this.initializationUtil.initialize();
  }

}
