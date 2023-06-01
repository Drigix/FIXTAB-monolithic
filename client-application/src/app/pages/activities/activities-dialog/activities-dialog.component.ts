import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Activity } from 'src/app/entitites/activity.model';
import { StatusDictionary } from 'src/app/entitites/result-dictionary.model';
import { ActivitiesService } from 'src/app/services/activities.service';

@Component({
  selector: 'fixtab-activities-dialog',
  templateUrl: './activities-dialog.component.html',
  styleUrls: ['./activities-dialog.component.scss']
})

export class ActivitiesDialogComponent implements OnInit {

  activityDialogForm = this.fb.group({
    statusCancel: [null, Validators.required],
    statusFinish: [null, Validators.required],
    result: [null, Validators.required]
  });

  statusOpen = StatusDictionary.statusOpen;
  statusProgress = StatusDictionary.statusProgress;
  statusCancel = StatusDictionary.statusCancel;
  statusFinish = StatusDictionary.statusFinish;

  isChangeStatusDialog = false;
  activity: Activity | null = null;
  changeToProgress = false;
  endResult: string | null = null;
  isStatusOpen = false;
  isStatusProgress = false;

  constructor(
    private ref: DynamicDialogRef,
    private config: DynamicDialogConfig,
    private activitiesService: ActivitiesService,
    private messageService: MessageService,
    private fb: UntypedFormBuilder
  ) { }

  ngOnInit() {
    this.isChangeStatusDialog = this.config.data.changeStatus;
    this.activity = this.config.data.activity;
    if(this.activity!.status!.statusId === this.statusOpen.statusId) {
      this.isStatusOpen = true;
    } else if(this.activity!.status!.statusId === this.statusProgress.statusId) {
      this.isStatusProgress = true;
    }
  }

  updateValidators(): void {
    const activityDialogResult = this.activityDialogForm.get('result');
    const activityDialogStatusCancel = this.activityDialogForm.get('statusCancel');
    const activityDialogStatusFinish = this.activityDialogForm.get('statusFinish');
    if(this.endResult === 'finishResult') {
      activityDialogResult!.setValidators(null);
      activityDialogStatusCancel?.setValidators(null);
    } else if (this.endResult === 'cancelResult') {
      activityDialogStatusFinish?.setValidators(null);
      activityDialogResult?.setValidators(Validators.required);
    }
    activityDialogStatusCancel?.updateValueAndValidity();
    activityDialogStatusFinish?.updateValueAndValidity();
    activityDialogResult!.updateValueAndValidity();
  }

  onStatusEdit(): void {
    if(this.activity!.status!.statusId === this.statusOpen.statusId) {
      this.activity!.status = this.statusProgress;
    } else if(this.activity!.status!.statusId === this.statusProgress.statusId) {
      if(this.endResult === 'finishResult') {
        this.activity!.status = this.statusFinish;
      } else if(this.endResult === 'cancelResult') {
        this.activity!.status = this.statusCancel;
      }
    }
    this.activitiesService.update(this.activity!).subscribe(
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
