import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';


@NgModule({
  imports: [
    RouterModule.forChild(
      [
        {
          path: '',
          loadChildren: () => import('./main/main.module').then(m => m.MainModule)
        }
      ]
    )
  ]
})
export class PagesRoutingModule { }
