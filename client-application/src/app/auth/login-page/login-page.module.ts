import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { LoginPageComponent } from './login-page.component';


@NgModule({
  imports: [
    FormsModule,
    CommonModule,
    ButtonModule,
    InputTextModule
  ],
  exports: [
    LoginPageComponent
  ],
  declarations: [
    LoginPageComponent
  ],
  providers: [],
})
export class LoginPageModule { }
