import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { ComponentsModule } from 'src/app/components/components.module';
import { RepairsRoutingModule } from './repairs-routing.module';
import { RepairsComponent } from './repairs.component';
import { RepairsDialogComponent } from './repairs-dialog/repairs-dialog.component';
import { TabMenuModule } from 'primeng/tabmenu';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { RepairsActivitiesDialogComponent } from './repairs-dialog/repairs-activities/repairs-activities-dialog.component';
import { OverlayPanelModule } from 'primeng/overlaypanel';

@NgModule({
  imports: [
    RepairsRoutingModule,
    ComponentsModule,
    ButtonModule,
    CommonModule,
    FormsModule,
    TabMenuModule,
    ReactiveFormsModule,
    DropdownModule,
    InputTextareaModule,
    OverlayPanelModule
  ],
  exports: [
    RepairsComponent,
    RepairsDialogComponent,
    RepairsActivitiesDialogComponent
  ],
  declarations: [
    RepairsComponent,
    RepairsDialogComponent,
    RepairsActivitiesDialogComponent
  ],
  providers: [],
})
export class RepairsModule { }
