import { NgModule } from '@angular/core';
import { FilterComponent } from './filter.component';
import { FormsModule } from '@angular/forms';
import { DropdownModule } from 'primeng/dropdown';


@NgModule({
  imports: [
    FormsModule,
    DropdownModule
  ],
  exports: [
    FilterComponent
  ],
  declarations: [
    FilterComponent
  ],
  providers: [],
})
export class FilterModule { }
