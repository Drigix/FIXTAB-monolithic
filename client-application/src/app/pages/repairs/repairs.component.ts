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

  ngOnInit() {
    this.loadColumns();
    this.loadRepairs();
  }

  loadColumns(): void {
    this.columns = [
      {
        header: 'Nazwa',
        field: 'name'
      },
      {
        header: 'Data',
        field: 'date'
      },
    ];
  }

  loadRepairs(): void {
    this.repairs = [
      {
        id: 1,
        name: 'Naprawa',
        date: '2022-02-02',
        activities: [
          {
            id:1,
            name: 'Zpreperować drzwi',
            status: true
          },
          {
            id:2,
            name: 'Zpreperować okno',
            status: null
          },
          {
            id:2,
            name: 'Zpreperować klamke',
            status: false
          }
        ]
      },
    ]
  }

  onRepairSelected(event: any): void {

  }

  openRepairsDialog(event: any): void {

  }

  openRepairsDeleteDialog(event: any): void {

  }
}
