import { ChangeDetectorRef, Component, OnChanges, OnInit, QueryList, Renderer2, SimpleChanges, ViewChild, ViewChildren } from '@angular/core';
import { TableComponent } from '../table/table.component';
import { Accordion, AccordionTab } from 'primeng/accordion';

@Component({
  selector: 'fixtab-expanded-table',
  templateUrl: './expanded-table.component.html',
  styleUrls: ['../table/table.component.scss']
})

export class ExpandedTableComponent extends TableComponent {

  activeAccordionIndex: number | null = null;

  constructor(
      public override cd: ChangeDetectorRef,
      public override renderer: Renderer2) {
    super(cd, renderer);
   }

   onActivityClick(activity: any) {
    console.log(activity);
  }
}
