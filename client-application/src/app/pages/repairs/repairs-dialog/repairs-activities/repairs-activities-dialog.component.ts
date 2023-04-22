import { HttpResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { UntypedFormBuilder, Validators } from '@angular/forms';
import { ActivityType } from 'src/app/entitites/activity-type.model';
import { Activity, ActivityRequest } from 'src/app/entitites/activity.model';
import { Employee } from 'src/app/entitites/employee-model';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'fixtab-repairs-activities-dialog',
  templateUrl: './repairs-activities-dialog.component.html',
  styleUrls: ['../../repairs.component.scss']
})

export class RepairsActivitiesDialogComponent implements OnInit, OnChanges {

  @Input() activity: ActivityRequest | null = null;
  @Input() activeItemIndex: number | null = null;

  @Output() activityFormValid = new EventEmitter<boolean>();

  activityForm = this.fb.group({
    employee: ['', Validators.required],
    description: ['', Validators.required],
    activityType: ['', Validators.required]
  });

  activityType: ActivityType = new ActivityType();
  employees: Employee[] = [];
  selectedEmployee: Employee | null = null;

  constructor(
    private fb: UntypedFormBuilder,
    private employeeService: EmployeeService
  ) { }

  ngOnInit() {
    this.loadEmployees();
    this.onActivityFormEmit();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.selectedEmployee = this.employees.find(item => item.employeeId === this.activity?.employeeId)!;
    this.activity!.activityType = this.activityType!;
  }

  loadEmployees(): void {
    this.employeeService.getAllNotDeleted().subscribe(
      (res: HttpResponse<Employee[]>) => {
        this.employees = res.body ?? [];
      }
    );
  }

  onActivityFormEmit(): void {
    this.activityFormValid.emit(this.activityForm.valid);
  }

  onEmployeeChange(): void {
    this.activity!.employeeId! = this.selectedEmployee?.employeeId!;
  }
}
