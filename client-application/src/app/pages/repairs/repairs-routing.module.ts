import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RepairsComponent } from './repairs.component';

export const routes: Routes = [
  {
    path: 'repairs',
    component: RepairsComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
  ],
  exports: [
    RouterModule
  ]
})
export class RepairsRoutingModule {}
