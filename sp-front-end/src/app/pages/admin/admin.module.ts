import {NgModule} from '@angular/core';
import {AdminComponent} from './admin.component';
import {LoginComponent} from './login/login.component';
import {AdminRoutingModule} from './admin-routing.module';
import {UsersComponent} from './users/users.component';
import {SharedModule} from '../../shared/shared.module';

@NgModule({
  imports: [AdminRoutingModule, SharedModule],
  declarations: [AdminComponent, LoginComponent, UsersComponent],
})
export class AdminModule {
}
