import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { ComponentsModule } from 'src/app/components/components.module';
import { RepairsRoutingModule } from './repairs-routing.module';
import { RepairsComponent } from './repairs.component';


@NgModule({
  imports: [
    RepairsRoutingModule,
    ComponentsModule,
    ButtonModule,
    CommonModule,
    FormsModule
  ],
  exports: [
    RepairsComponent
  ],
  declarations: [
    RepairsComponent
  ],
  providers: [],
})
export class RepairsModule { }
