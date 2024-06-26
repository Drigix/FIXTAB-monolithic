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
import { ConfirmationService, MessageService } from 'primeng/api';
import { HTTP_INTERCEPTORS, HttpClient, HttpClientModule } from '@angular/common/http';
import { LoginService } from './services/login.service';
import { UntypedFormBuilder } from '@angular/forms';
import { TokenInterceptor } from './config/token-interceptor.service';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { ProfileModule } from './pages/profile/profile.module';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { ToastModule } from 'primeng/toast';

export function HttpLoaderFactory(http: HttpClient): TranslateHttpLoader {
  return new TranslateHttpLoader(http);
}

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
    ProfileModule,
    ComponentsModule,
    AppRoutingModule,
    MainModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ToastModule,
    TranslateModule.forRoot({
      loader: {
      provide: TranslateLoader,
      useFactory: HttpLoaderFactory,
      deps: [HttpClient]
      }
      }),
  ],
  providers: [
    DialogService,
    ConfirmationService,
    UntypedFormBuilder,
    MessageService,
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
