import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ButtonModule } from 'primeng/button';
import {InputTextModule} from 'primeng/inputtext';
import { LoginPageComponent } from './auth/login-page/login-page.component';
import { ComponentsModule } from './components/components.module';
import { MainModule } from './pages/main/main.module';
import { LoginPageModule } from './auth/login-page/login-page.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    ButtonModule,
    InputTextModule,
    ComponentsModule,
    MainModule,
    LoginPageModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
