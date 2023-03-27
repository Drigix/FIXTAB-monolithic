import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';


@NgModule({
  imports: [
    RouterModule.forChild(
      [
        {
          path: 'home',
          loadChildren: () => import('./main/main.module').then(m => m.MainModule)
        },
        {
          path: 'employees',
          loadChildren: () => import('./employees/employees.module').then(m => m.EmployeesModule)
        }
      ]
    )
  ]
})
export class PagesRoutingModule { }
