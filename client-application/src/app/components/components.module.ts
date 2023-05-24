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
import { RepairsTabMenuComponent } from './repairs-tab-menu/repairs-tab-menu.component';
import { TabMenuModule } from 'primeng/tabmenu';
import { FormsModule } from '@angular/forms';
import { TranslateModule } from '@ngx-translate/core';
import { FilterModule } from './filter/filter.module';
import { SearchDropdownComponent } from './search-dropdown/serach-dropdown.component';
import { DropdownModule } from 'primeng/dropdown';


@NgModule({
  declarations: [
    MenuComponent,
    TableButtonsComponent,
    DeleteDialogComponent,
    RepairsTabMenuComponent,
    SearchDropdownComponent
  ],
  imports: [
    CommonModule,
    PanelMenuModule,
    FontAwesomeModule,
    ButtonModule,
    ConfirmDialogModule,
    TabMenuModule,
    FormsModule,
    CommonModule,
    TranslateModule,
    FilterModule,
    DropdownModule
  ],
  exports: [
    MenuComponent,
    FixtabTableModule,
    TableButtonsComponent,
    DeleteDialogComponent,
    ExpandedTableModule,
    RepairsTabMenuComponent,
    FilterModule,
    SearchDropdownComponent
  ]
})
export class ComponentsModule { }
