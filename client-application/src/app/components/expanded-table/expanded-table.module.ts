import { NgModule } from '@angular/core';
import { ExpandedTableComponent } from './expanded-table.component';
import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table';
import { InputTextModule } from 'primeng/inputtext';
import { TranslateModule } from '@ngx-translate/core';
import { TooltipModule } from 'primeng/tooltip';
import { DialogModule } from 'primeng/dialog';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ButtonModule } from 'primeng/button';
import { AccordionModule } from 'primeng/accordion';
import { CheckboxModule } from 'primeng/checkbox';
import { FormsModule } from '@angular/forms';


@NgModule({
  imports: [
    CommonModule,
    TableModule,
    InputTextModule,
    TranslateModule,
    TooltipModule,
    DialogModule,
    FontAwesomeModule,
    ButtonModule,
    AccordionModule,
    CheckboxModule,
    FormsModule
  ],
  exports: [
    ExpandedTableComponent
  ],
  declarations: [ExpandedTableComponent],
  providers: [],
})
export class ExpandedTableModule { }
