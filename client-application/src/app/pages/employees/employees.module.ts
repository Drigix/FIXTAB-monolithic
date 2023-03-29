import { NgModule } from '@angular/core';
import { ComponentsModule } from 'src/app/components/components.module';
import { EmployeesRoutingModule } from './employees-routing.module';
import { EmployeesComponent } from './employees.component';


@NgModule({
  imports: [
    EmployeesRoutingModule,
    ComponentsModule
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
