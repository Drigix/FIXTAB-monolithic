import { Component, OnInit } from '@angular/core';
import { UniversalTableColumn } from 'src/app/components/table/column.model';

@Component({
  selector: 'fixtab-employees',
  templateUrl: './employees.component.html'
})

export class EmployeesComponent implements OnInit {

  columns: UniversalTableColumn[] = [];
  values: any[] = [];

  constructor() { }

  ngOnInit() {
    this.columns = [
      {
        header: "Imie",
        name: "Name",
        field: "name"
      },
      {
        header: "Nazwisko",
        name: "Surname",
        field: "surname"
      },
    ];
    this.values = [
      {
        name: "Michał",
        surname: "Ławinski",
      },
      {
        name: "Michał",
        surname: "Kowalski",
      },
      {
        name: "Michał",
        surname: "Ławinski",
      },
      {
        name: "Michał",
        surname: "Kowalski",
      },{
        name: "Michał",
        surname: "Ławinski",
      },
      {
        name: "Michał",
        surname: "Kowalski",
      },{
        name: "Michał",
        surname: "Ławinski",
      },
      {
        name: "Michał",
        surname: "Kowalski",
      },
      {
        name: "Michał",
        surname: "Ławinski",
      },
      {
        name: "Michał",
        surname: "Kowalski",
      },
      {
        name: "Michał",
        surname: "Ławinski",
      },
      {
        name: "Michał",
        surname: "Kowalski",
      },{
        name: "Michał",
        surname: "Ławinski",
      },
      {
        name: "Michał",
        surname: "Kowalski",
      },{
        name: "Michał",
        surname: "Ławinski",
      },
      {
        name: "Michał",
        surname: "Kowalski",
      }
    ]
   }
}
