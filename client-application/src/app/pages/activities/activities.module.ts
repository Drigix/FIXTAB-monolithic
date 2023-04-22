import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { ComponentsModule } from 'src/app/components/components.module';
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { ActivitiesRoutingModule } from './activities-routing.module';
import { ActivitiesComponent } from './activities.component';


@NgModule({
  imports: [
    ActivitiesRoutingModule,
    ComponentsModule,
    ButtonModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [
    ActivitiesComponent
  ],
  declarations: [
    ActivitiesComponent
  ],
  providers: [],
})
export class ActivitiesModule { }
