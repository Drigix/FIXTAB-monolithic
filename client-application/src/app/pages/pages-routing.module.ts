import { authGuard } from './../config/user-route-access.service';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Authority } from '../auth/authority.model';


@NgModule({
  imports: [
    RouterModule.forChild(
      [
        {
          path: '',
          data: {
            authorities: ['']
          },
          loadChildren: () => import('./home/home.module').then(m => m.HomeModule),
          canActivate: [authGuard]
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
