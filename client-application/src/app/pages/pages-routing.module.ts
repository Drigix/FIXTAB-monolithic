import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';


@NgModule({
  imports: [
    RouterModule.forChild(
      [
        {
          path: '',
          loadChildren: () => import('./home/home.module').then(m => m.HomeModule)
        },
        {
          path: '',
          loadChildren: () => import('./employees/employees.module').then(m => m.EmployeesModule)
        },
        {
          path: '',
          loadChildren: () => import('./repairs/repairs.module').then(m => m.RepairsModule)
        }
      ]
    )
  ]
})
export class PagesRoutingModule { }
