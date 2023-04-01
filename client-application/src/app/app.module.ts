import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { ButtonModule } from 'primeng/button';
import {InputTextModule} from 'primeng/inputtext';
import { LoginPageComponent } from './auth/login-page/login-page.component';
import { ComponentsModule } from './components/components.module';
import { MainModule } from './pages/main/main.module';
import { LoginPageModule } from './auth/login-page/login-page.module';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DialogService } from 'primeng/dynamicdialog';
import { ConfirmationService } from 'primeng/api';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { LoginService } from './services/login.service';

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
    LoginPageModule,
    ComponentsModule,
    AppRoutingModule,
    MainModule,
    BrowserAnimationsModule,
    HttpClientModule
  ],
  providers: [
    DialogService,
    ConfirmationService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
