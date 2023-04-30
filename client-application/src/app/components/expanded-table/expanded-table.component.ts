import { ResultDictionary } from './../../entitites/result-dictionary.model';
import { ChangeDetectorRef, Component, EventEmitter, OnChanges, OnInit, Output, QueryList, Renderer2, SimpleChanges, ViewChild, ViewChildren } from '@angular/core';
import { TableComponent } from '../table/table.component';
import { Accordion, AccordionTab } from 'primeng/accordion';
import { Activity } from 'src/app/entitites/activity.model';
import { Request } from 'src/app/entitites/request.model';

@Component({
  selector: 'fixtab-expanded-table',
  templateUrl: './expanded-table.component.html',
  styleUrls: ['../table/table.component.scss', './expanded-table.component.scss']
})

export class ExpandedTableComponent extends TableComponent {

  @Output() filterClick = new EventEmitter<any>();
  @Output() selectedRequestRepair = new EventEmitter<Request>();
  @Output() editActivityEmit = new EventEmitter<Activity>();

  selectRow: Request | null = null;
  activeAccordionIndex: number | null = null;
  selectedActivity: Activity | null = null;

  constructor(
      public override cd: ChangeDetectorRef,
      public override renderer: Renderer2) {
    super(cd, renderer);
   }

  onRequestRepairClick(request: Request): void {
    this.selectRow = request;
    this.selectedRequestRepair.emit(request);
  }

   onActivityClick(activity: Activity) {
    this.selectedActivity = activity;
  }

  onFilterClick(event: any): void {
    this.filterClick.emit(event);
  }

  onEditActivity(): void {
    this.editActivityEmit.emit(this.selectedActivity!);
  }
}
