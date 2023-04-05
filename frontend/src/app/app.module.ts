import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login-component/login.component';
import { RegisterComponent } from './register-component/register.component';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http'
import {RouterModule} from "@angular/router";
import { RegistrationComponent } from './registration/registration.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    RegistrationComponent
  ],
  imports: [
    FormsModule,
    HttpClientModule,
    BrowserModule,
    RouterModule.forRoot([
      {path: 'rest/login', component: LoginComponent},
      {path: 'rest/registration', component: RegisterComponent},
    ]),
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
