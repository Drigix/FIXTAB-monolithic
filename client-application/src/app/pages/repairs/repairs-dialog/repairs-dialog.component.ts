import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, Validators } from '@angular/forms';
import { MenuItem, MessageService } from 'primeng/api';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Activity, ActivityRequest } from 'src/app/entitites/activity.model';
import { Employee } from 'src/app/entitites/employee-model';
import { TargetObject } from 'src/app/entitites/object.model';
import { Request, RequestRepairRequest } from 'src/app/entitites/request.model';
import { StatusDictionary } from 'src/app/entitites/result-dictionary.model';
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
    status: [''],
    result: ['']
  });

  request: RequestRepairRequest = new RequestRepairRequest();
  editRequest: Request | null = null;
  activities: ActivityRequest[] = [];
  objects: TargetObject[] = [];
  results = StatusDictionary.statusDictionaryList;
  selectedResult: StatusDictionary | null = null;
  selectedObject: TargetObject | null = null;
  currentEmployee: Employee | null = null;
  activeItemIndex?: number;
  isRequestChosen = true;
  edit = false;
  activityFormValid = false;
  resultDescription = '';

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
    this.edit = this.config.data.edit;
    if(this.edit) {
      this.editRequest = this.config.data.request;
      this.selectedResult = this.results.find( result => result.statusId === this.editRequest?.status?.statusId)!;
    }
    this.loadObjects();
    this.loadCurrentEmployee();
  }

  loadObjects(): void {
    this.objectsService.getAllNotDeleted().subscribe(
      (res: HttpResponse<TargetObject[]>) => {
        this.objects = res.body ?? [];
        if(this.edit) {
          this.selectedObject = this.objects.find( object => object.targetId === this.editRequest?.targetObject?.targetId)!;
        }
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

  onDescriptionChange(event: string): void {
    if(this.edit) {
      this.editRequest!.description = event;
    } else {
      this.request.description = event;
    }
  }

  onEditRepair(): void {
    this.editRequest!.targetObject = this.selectedObject!;
    this.editRequest!.status = this.selectedResult!;
    //this.editRequest!.result = this.resultDescription;
    this.requestRepairService.update(this.editRequest!).subscribe(
      {
        next: () => {
          this.messageService.add({key: 'mainToast', severity: 'success', summary: 'Success',
              detail: 'edytowano!'});
          this.ref.close();
        },
        error: () => {
          this.messageService.add({key: 'mainToast', severity: 'error', summary: 'Error',
              detail: 'nie edytowano!'});
        }
      }
    )
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
