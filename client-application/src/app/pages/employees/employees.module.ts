import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { ComponentsModule } from 'src/app/components/components.module';
import { EmployeesDialogComponent } from './employees-dialog/employees-dialog.component';
import { EmployeesRoutingModule } from './employees-routing.module';
import { EmployeesComponent } from './employees.component';


@NgModule({
  imports: [
    EmployeesRoutingModule,
    ComponentsModule,
    ButtonModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    InputTextModule
  ],
  exports: [
    EmployeesComponent,
    EmployeesDialogComponent
  ],
  declarations: [
    EmployeesComponent,
    EmployeesDialogComponent
  ],
  providers: [],
})
export class EmployeesModule { }
