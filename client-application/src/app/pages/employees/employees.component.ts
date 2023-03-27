import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'fixtab-employees',
  templateUrl: './employees.component.html'
})

export class EmployeesComponent implements OnInit {

  employee = 'pracownik działa';

  constructor() { }

  ngOnInit() { }
}
