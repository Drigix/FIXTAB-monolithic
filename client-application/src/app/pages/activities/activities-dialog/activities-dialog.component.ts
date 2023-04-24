import { Component, OnInit } from '@angular/core';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Activity } from 'src/app/entitites/activity.model';
import { ResultDictionary } from 'src/app/entitites/result-dictionary.model';

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
  endResult = false;

  constructor(
    private ref: DynamicDialogRef,
    private config: DynamicDialogConfig
  ) { }

  ngOnInit() {
    this.isChangeStatusDialog = this.config.data.changeStatus;
    this.activity = this.config.data.activity;
  }

  onStatusEdit(): void {

  }

  onCloseDialog(): void {
    this.ref.close();
  }
}
