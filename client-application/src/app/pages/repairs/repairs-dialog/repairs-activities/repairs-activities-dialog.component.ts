import { ActivitiesService } from './../../../../services/activities.service';
import { HttpResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { UntypedFormBuilder, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { ActivityType } from 'src/app/entitites/activity-type.model';
import { Activity, ActivityRequest } from 'src/app/entitites/activity.model';
import { Employee } from 'src/app/entitites/employee-model';
import { ActivityTypeService } from 'src/app/services/activity-type.service';
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

  editActivity: Activity | null = null;
  activityType: ActivityType = new ActivityType();
  employees: Employee[] = [];
  activityTypes: ActivityType[] = [];
  selectedEmployee: Employee | null = null;
  selectedActivityType: ActivityType | null = null;
  edit = false;

  constructor(
    private fb: UntypedFormBuilder,
    private employeeService: EmployeeService,
    private config: DynamicDialogConfig,
    private messageService: MessageService,
    private activitiesService: ActivitiesService,
    private activityTypeService: ActivityTypeService,
    private ref: DynamicDialogRef
  ) { }

  ngOnInit() {
    this.edit = this.config.data.edit;
    if(this.edit) {
      this.editActivity = this.config.data.activity;
      this.updateValidators(true);
    }
    this.loadEmployees();
    this.loadActivityTypes();
    this.onActivityFormEmit();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(!this.edit) {
      this.selectedEmployee = this.employees.find(item => item.employeeId === this.activity?.employeeId)!;
      this.selectedActivityType = this.activityTypes.find( item => item.typeId === this.activity?.activityType?.typeId)!;
    }
  }

  loadEmployees(): void {
    this.employeeService.getAllNotDeleted().subscribe(
      (res: HttpResponse<Employee[]>) => {
        this.employees = res.body ?? [];
        if(this.edit) {
          this.selectedEmployee = this.employees.find( employee => employee.employeeId === this.editActivity?.employee?.employeeId)!;
        }
      }
    );
  }

  loadActivityTypes(): void {
    this.activityTypeService.getAll().subscribe(
      (res: HttpResponse<ActivityType[]>) => {
        this.activityTypes = res.body ?? [];
        if(this.edit) {
          this.selectedActivityType = this.activityTypes.find( activityType => activityType.typeId === this.editActivity?.activityType?.typeId)!;
        }
      }
    );
  }

  onActivityFormEmit(): void {
    this.activityFormValid.emit(this.activityForm.valid);
  }

  onEmployeeChange(employee: Employee): void {
    if(employee) {
      if(this.edit) {
        this.selectedEmployee = employee;
      } else {
        this.activity!.employeeId = employee.employeeId;
      }
      this.updateValidators(true, false, true);
    } else {
      this.updateValidators(true, false, false);
    }
  }

  onActivityTypeChange(activityType: ActivityType): void {
    if(activityType) {
      if(this.edit) {
        this.selectedActivityType = activityType;
      } else {
        this.activity!.activityType = activityType;
      }
      this.updateValidators(false, true, true);
    } else {
      this.updateValidators(false, true, false);
    }
  }

  updateValidators(updateValidatorsEmployee = false, updateValidatorsActivityType = false, isClear = false): void {
    if(updateValidatorsEmployee) {
      const employeeControl = this.activityForm.get('employee');
      if(isClear) {
        employeeControl!.setValidators(null);
      } else {
        employeeControl?.setValidators(Validators.required);
      }
      employeeControl!.updateValueAndValidity();
    } else if(updateValidatorsActivityType) {
      const activityTypeControl = this.activityForm.get('activityType');
      if(isClear) {
        activityTypeControl!.setValidators(null);
      } else {
        activityTypeControl?.setValidators(Validators.required);
      }
      activityTypeControl!.updateValueAndValidity();
    }
  }

  onEditActivity(): void {
    this.editActivity!.employee = this.selectedEmployee!;
    this.editActivity!.activityType = this.selectedActivityType!;
    this.activitiesService.update(this.editActivity!).subscribe(
      {
        next: (response) => {
          this.messageService.add({key: 'mainToast', severity: 'success', summary: 'Success',
              detail: 'edytowano!'});
          this.ref.close(response);
        },
        error: () => {
          this.messageService.add({key: 'mainToast', severity: 'error', summary: 'Error',
              detail: 'nie edytowano!'});
        }
      }
    );
  }

  onCloseDialog(): void {
    this.ref.close();
  }
}
