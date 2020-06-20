import {ModuleWithProviders, NgModule, Optional, SkipSelf} from '@angular/core';
import {NbAuthModule} from '@nebular/auth';
import {TokenStrategyFacadeService} from './facade/token-strategy-facade.service';
import {NbJwtToken} from '../common/util';
import {NbThemeModule, NbToastrModule} from '@nebular/theme';

export const CORE_PROVIDERS = [
  NbAuthModule.forRoot({
      forms: {}, strategies: [
        TokenStrategyFacadeService.setup({
          name: 'jwt',
          token: {
            class: NbJwtToken
          }
        })
      ]
    }).providers,
  NbThemeModule.forRoot({
    name: 'default',
  }).providers,
  NbToastrModule.forRoot().providers
];

@NgModule({
  imports: [],
  declarations: [],
})
export class CoreModule {

  // https://angular.io/guide/singleton-services#prevent-reimport-of-the-greetingmodule
  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error(`CoreModule has already been loaded. Import Core modules in the AppModule only.`);
    }
  }

  static forRoot(): ModuleWithProviders<CoreModule> {
    return {
      ngModule: CoreModule,
      providers: [
        ...CORE_PROVIDERS,
      ],
    };
  }
}
