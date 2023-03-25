import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { ComponentsModule } from 'src/app/components/components.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MainComponent } from './main.component';

@NgModule({
  imports: [
    AppRoutingModule,
    ComponentsModule,
    FontAwesomeModule,
    CommonModule,
    FormsModule,
    BrowserAnimationsModule
  ],
  exports: [
    MainComponent,
  ],
  declarations: [
    MainComponent
  ],
  providers: [],
})
export class MainModule { }
