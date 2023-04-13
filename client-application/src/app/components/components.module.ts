import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuComponent } from './menu/menu.component';
import { PanelMenuModule } from 'primeng/panelmenu';
import { FixtabTableModule } from './table/table.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { TableButtonsComponent } from './table-buttons/table-buttons.component';
import { ButtonModule } from 'primeng/button';
import { DeleteDialogComponent } from './delete-dialog/delete-dialog.component';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ExpandedTableModule } from './expanded-table/expanded-table.module';


@NgModule({
  declarations: [
    MenuComponent,
    TableButtonsComponent,
    DeleteDialogComponent
  ],
  imports: [
    CommonModule,
    PanelMenuModule,
    FontAwesomeModule,
    ButtonModule,
    ConfirmDialogModule
  ],
  exports: [
    MenuComponent,
    FixtabTableModule,
    TableButtonsComponent,
    DeleteDialogComponent,
    ExpandedTableModule
  ]
})
export class ComponentsModule { }
