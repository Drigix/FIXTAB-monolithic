import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuComponent } from './menu/menu.component';
import { PanelMenuModule } from 'primeng/panelmenu';
import { FixtabTableModule } from './table/table.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { TableButtonsComponent } from './table-buttons/table-buttons.component';
import { ButtonModule } from 'primeng/button';


@NgModule({
  declarations: [
    MenuComponent,
    TableButtonsComponent
  ],
  imports: [
    CommonModule,
    PanelMenuModule,
    FontAwesomeModule,
    ButtonModule
  ],
  exports: [
    MenuComponent,
    FixtabTableModule,
    TableButtonsComponent
  ]
})
export class ComponentsModule { }
