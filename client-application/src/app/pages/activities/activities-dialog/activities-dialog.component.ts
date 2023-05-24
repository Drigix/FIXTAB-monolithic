import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Activity } from 'src/app/entitites/activity.model';
import { ResultDictionary } from 'src/app/entitites/result-dictionary.model';
import { ActivitiesService } from 'src/app/services/activities.service';

@Component({
  selector: 'fixtab-activities-dialog',
  templateUrl: './activities-dialog.component.html',
  styleUrls: ['./activities-dialog.component.scss']
})

export class ActivitiesDialogComponent implements OnInit {

  statusOpen = ResultDictionary.statusOpen;
  statusProgress = ResultDictionary.statusProgress;
  statusCancel = ResultDictionary.statusCancel;
  statusFinish = ResultDictionary.statusFinish;

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
    private messageService: MessageService
  ) { }

  ngOnInit() {
    this.isChangeStatusDialog = this.config.data.changeStatus;
    this.activity = this.config.data.activity;
    if(this.activity!.result!.resultId === this.statusOpen.resultId) {
      this.isStatusOpen = true;
    } else if(this.activity!.result!.resultId === this.statusProgress.resultId) {
      this.isStatusProgress = true;
    }
  }

  onStatusEdit(): void {
    if(this.activity!.result!.resultId === this.statusOpen.resultId) {
      this.activity!.result = this.statusProgress;
    } else if(this.activity!.result!.resultId === this.statusProgress.resultId) {
      if(this.endResult === 'finishResult') {
        this.activity!.result = this.statusFinish;
      } else if(this.endResult === 'cancelResult') {
        this.activity!.result = this.statusCancel;
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
