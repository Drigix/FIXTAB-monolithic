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
import { ActivitiesDialogComponent } from './activities-dialog/activities-dialog.component';
import { RadioButtonModule } from 'primeng/radiobutton';


@NgModule({
  imports: [
    ActivitiesRoutingModule,
    ComponentsModule,
    ButtonModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    RadioButtonModule
  ],
  exports: [
    ActivitiesComponent,
    ActivitiesDialogComponent
  ],
  declarations: [
    ActivitiesComponent,
    ActivitiesDialogComponent
  ],
  providers: [],
})
export class ActivitiesModule { }
