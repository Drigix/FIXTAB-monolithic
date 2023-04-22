import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, Validators } from '@angular/forms';
import { MenuItem, MessageService } from 'primeng/api';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Activity, ActivityRequest } from 'src/app/entitites/activity.model';
import { Employee } from 'src/app/entitites/employee-model';
import { TargetObject } from 'src/app/entitites/object.model';
import { Request, RequestRepairRequest } from 'src/app/entitites/request.model';
import { EmployeeService } from 'src/app/services/employee.service';
import { ObjectsService } from 'src/app/services/objects.service';
import { RequestRepairService } from 'src/app/services/request-repair.service';

@Component({
  selector: 'fixtab-repairs-dialog',
  templateUrl: './repairs-dialog.component.html',
  styleUrls: ['../repairs.component.scss']
})

export class RepairsDialogComponent implements OnInit {

  requestForm = this.fb.group({
    targetObject: ['', Validators.required],
    description: ['', Validators.required],
  });

  request: RequestRepairRequest = new RequestRepairRequest();
  activities: ActivityRequest[] = [];
  objects: TargetObject[] = [];
  selectedObject: TargetObject | null = null;
  currentEmployee: Employee | null = null;
  activeItemIndex?: number;
  isRequestChosen = true;
  edit = false;
  activityFormValid = false;

  constructor(
    private fb: UntypedFormBuilder,
    private ref: DynamicDialogRef,
    private config: DynamicDialogConfig,
    private objectsService: ObjectsService,
    private requestRepairService: RequestRepairService,
    private employeeService: EmployeeService,
    private messageService: MessageService
  ) { }

  ngOnInit() {
    this.loadObjects();
    this.loadCurrentEmployee();
  }

  loadObjects(): void {
    this.objectsService.getAllNotDeleted().subscribe(
      (res: HttpResponse<TargetObject[]>) => {
        this.objects = res.body ?? [];
      }
    );
  }

  loadCurrentEmployee(): void {
    this.employeeService.getCurrentEmployee().subscribe(
      (res: HttpResponse<Employee>) => {
        this.currentEmployee = res.body;
      }
    );
  }

  onActivityFormChange(valid: boolean): void {
    console.log(valid);
    this.activityFormValid = valid;
  }

  onActivitiesChange(activities: ActivityRequest[]) {
    this.activities = activities;
    console.log(this.activities);
  }

  onActiveIndexChange(activeIndex: number) {
    this.isRequestChosen = activeIndex < 0;
    this.activeItemIndex = activeIndex;
  }

  onEditRepair(): void {

  }

  onCreateRepair(): void {
    this.request.targetObjectId = this.selectedObject!.targetId;
    this.request.managerId = this.currentEmployee?.employeeId;
    this.request.activities = this.activities;
    this.requestRepairService.create(this.request).subscribe(
      {
        next: () => {
          this.messageService.add({key: 'mainToast', severity: 'success', summary: 'Success',
              detail: 'utworzono!'});
          this.ref.close();
        },
        error: () => {
          this.messageService.add({key: 'mainToast', severity: 'error', summary: 'Error',
              detail: 'nie utworzono!'});
        }
      }
    )
  }

  onCloseDialog(response = false): void {
    this.ref.close(response);
  }
}
