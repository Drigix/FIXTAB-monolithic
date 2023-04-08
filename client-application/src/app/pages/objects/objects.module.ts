import { NgModule } from '@angular/core';
import { ObjectsComponent } from './objects.component';
import { ObjectsRoutingModule } from './objects-routing.module';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { ComponentsModule } from 'src/app/components/components.module';
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { ObjectsDialogComponent } from './objects-dialog/objects-dialog.component';


@NgModule({
  imports: [
    ObjectsRoutingModule,
    ComponentsModule,
    ButtonModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    InputTextModule,
    CalendarModule,
    DropdownModule
  ],
  exports: [
    ObjectsComponent
  ],
  declarations: [ObjectsComponent, ObjectsDialogComponent],
  providers: [],
})
export class ObjectsModule { }
