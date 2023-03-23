import { NgModule } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { ComponentsModule } from 'src/app/components/components.module';

import { MainComponent } from './main.component';

@NgModule({
  imports: [
    AppRoutingModule,
    ComponentsModule,
    FontAwesomeModule
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
