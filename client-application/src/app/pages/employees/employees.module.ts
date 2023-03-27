import { NgModule } from '@angular/core';
import { EmployeesRoutingModule } from './employees-routing.module';
import { EmployeesComponent } from './employees.component';


@NgModule({
  imports: [
    EmployeesRoutingModule
  ],
  exports: [
    EmployeesComponent,
  ],
  declarations: [
    EmployeesComponent
  ],
  providers: [],
})
export class EmployeesModule { }
