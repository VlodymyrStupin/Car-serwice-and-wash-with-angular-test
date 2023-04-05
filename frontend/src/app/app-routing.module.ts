import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login-component/login.component";
import {RegisterComponent} from "./register-component/register.component";

const routes: Routes = [
  {path: 'rest/login', component: LoginComponent},
  {path: 'rest/hello', component: RegisterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
