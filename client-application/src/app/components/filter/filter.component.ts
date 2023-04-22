import { HttpResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Employee } from 'src/app/entitites/employee-model';
import { Request } from 'src/app/entitites/request.model';
import { ChangeDateService } from 'src/app/services/change-date.service';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'fixtab-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.scss']
})

export class FilterComponent implements OnInit {

  @Output() emitRepairs = new EventEmitter<Request[]>();

  @Input() repairs: Request[] = [];
  filterRepairs: Request[] = [];

  requestFilterOptions = [
    {
      name: 'Wszystkie'
    },
    {
      name: 'Moje'
    }
  ];
  activityEmployeeFilterOption: Employee[] = [];
  dateFilterOption = [
    {
      name: 'Dzisiaj',
      date: new Date()
    },
    {
      name: 'Od wczoraj',
      date: new Date(new Date().setDate(new Date().getDate() - 1))
    },
    {
      name: 'Ostatni tydzień',
      date: new Date(new Date().setDate(new Date().getDate() - 7))
    }
  ];

  constructor(
    private employeeService: EmployeeService,
    private changeDateService: ChangeDateService
  ) { }

  ngOnInit() {
    this.filterRepairs = this.repairs;
    this.loadEmployees();
  }

  loadEmployees(): void {
    this.employeeService.getAllNotDeleted().subscribe(
      (res: HttpResponse<Employee[]>) => {
        this.activityEmployeeFilterOption = res.body ?? [];
      }
    );
  }

  onManagerSelect(): void {

  }

  onEmployeeSelect(employee: Employee): void {
    if(employee) {
      this.repairs = this.filterRepairs.filter( repair => repair.activity?.some(activity => activity.employee?.employeeId === employee.employeeId));
    } else {
      this.repairs = this.filterRepairs;
    }
    this.emitRepairs.emit(this.repairs);
  }

  onDateSelect(date: any): void {
    if(date) {
      this.repairs = this.filterRepairs.filter( repair =>
        this.changeDateService.changeDateToString(new Date(repair.openDate!)) >= this.changeDateService.changeDateToString(date.date));
    } else {
      this.repairs = this.filterRepairs;
    }
    this.emitRepairs.emit(this.repairs);
  }
}
