import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { ComponentsModule } from 'src/app/components/components.module';
import { CalendarModule } from 'primeng/calendar';
import { ArchiveRoutingModule } from './archive-routing.module';
import { ArchiveComponent } from './archive.component';
import { DropdownModule } from 'primeng/dropdown';


@NgModule({
  imports: [
    ArchiveRoutingModule,
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
    ArchiveComponent
  ],
  declarations: [
    ArchiveComponent
  ],
  providers: [],
})
export class ArchiveModule { }
