import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuComponent } from './menu/menu.component';
import { PanelMenuModule } from 'primeng/panelmenu';
import { FixtabTableModule } from './table/table.module';


@NgModule({
  declarations: [
    MenuComponent,
  ],
  imports: [
    CommonModule,
    PanelMenuModule
  ],
  exports: [
    MenuComponent,
    FixtabTableModule
  ]
})
export class ComponentsModule { }
