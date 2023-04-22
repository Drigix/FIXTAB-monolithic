import { authGuard } from './../config/user-route-access.service';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthorityGroupService } from '../auth/authority-group.service';


@NgModule({
  imports: [
    RouterModule.forChild(
      [
        {
          path: '',
          data: {
            authorities: AuthorityGroupService.getCompanyAuthorityGroup()
          },
          loadChildren: () => import('./home/home.module').then(m => m.HomeModule),
          canActivate: [authGuard]
        },
        {
          path: '',
          data: {
            authorities: AuthorityGroupService.getAdminAuthorityGroup()
          },
          loadChildren: () => import('./employees/employees.module').then(m => m.EmployeesModule),
          canActivate: [authGuard]
        },
        {
          path: '',
          data: {
            authorities: AuthorityGroupService.getManagementAuthorityGroup()
          },
          loadChildren: () => import('./repairs/repairs.module').then(m => m.RepairsModule),
          canActivate: [authGuard]
        },
        {
          path: '',
          data: {
            authorities: AuthorityGroupService.getCompanyAuthorityGroup()
          },
          loadChildren: () => import('./clients/clients.module').then(m => m.ClientsModule),
          canActivate: [authGuard]
        },
        {
          path: '',
          data: {
            authorities: AuthorityGroupService.getCompanyAuthorityGroup()
          },
          loadChildren: () => import('./objects/objects.module').then(m => m.ObjectsModule),
          canActivate: [authGuard]
        },
        {
          path: '',
          data: {
            authorities: AuthorityGroupService.getManagementAuthorityGroup()
          },
          loadChildren: () => import('./archive/archive.module').then(m => m.ArchiveModule),
          canActivate: [authGuard]
        },
        {
          path: '',
          data: {
            authorities: AuthorityGroupService.getCompanyAuthorityGroup()
          },
          loadChildren: () => import('./activities/activities.module').then(m => m.ActivitiesModule),
          canActivate: [authGuard]
        }
      ]
    )
  ]
})
export class PagesRoutingModule { }
