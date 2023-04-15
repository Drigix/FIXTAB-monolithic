import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { ComponentsModule } from 'src/app/components/components.module';
import { RepairsRoutingModule } from './repairs-routing.module';
import { RepairsComponent } from './repairs.component';
import { RepairsDialogComponent } from './repairs-dialog/repairs-dialog.component';
import { TabMenuModule } from 'primeng/tabmenu';


@NgModule({
  imports: [
    RepairsRoutingModule,
    ComponentsModule,
    ButtonModule,
    CommonModule,
    FormsModule,
    TabMenuModule,
    ReactiveFormsModule
  ],
  exports: [
    RepairsComponent,
    RepairsDialogComponent
  ],
  declarations: [
    RepairsComponent,
    RepairsDialogComponent
  ],
  providers: [],
})
export class RepairsModule { }
