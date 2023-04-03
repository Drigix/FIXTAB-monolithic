import { Component, OnInit } from '@angular/core';
import { UniversalTableColumn } from 'src/app/components/table/column.model';

@Component({
  selector: 'fixtab-repairs',
  templateUrl: './repairs.component.html',
  styleUrls: ['./repairs.component.scss']
})

export class RepairsComponent implements OnInit {

  selectedRepair: any;
  columns: UniversalTableColumn[] = [];
  repairs: any[] = [];

  constructor() { }

  ngOnInit() { }

  onRepairSelected(event: any): void {

  }

  openRepairsDialog(event: any): void {

  }

  openRepairsDeleteDialog(event: any): void {

  }
}
