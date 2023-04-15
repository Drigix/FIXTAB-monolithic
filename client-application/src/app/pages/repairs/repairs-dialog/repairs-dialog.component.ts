import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, Validators } from '@angular/forms';
import { MenuItem } from 'primeng/api';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Activity } from 'src/app/entitites/activity.model';

@Component({
  selector: 'fixtab-repairs-dialog',
  templateUrl: './repairs-dialog.component.html',
  styleUrls: ['../repairs.component.scss']
})

export class RepairsDialogComponent implements OnInit {

  repairsForm = this.fb.group({
    // name: ['', Validators.required],
    // surname: ['', Validators.required],
    // email: ['', [Validators.required, Validators.email]],
    // birthDay: ['', [Validators.required]],
    // phoneNumber: ['', [Validators.required, Validators.pattern("[0-9]{11}")]],
    // gender: ['', Validators.required],
    // pesel: ['', [Validators.required, Validators.pattern("[0-9]{11}")]],
    // role: ['', Validators.required]
  });

  activities: Activity[] = [];
  activeItemIndex?: number;
  isRequestChosen = true;
  edit = false;

  constructor(
    private fb: UntypedFormBuilder,
    private ref: DynamicDialogRef,
    private config: DynamicDialogConfig,
  ) { }

  ngOnInit() {

  }

  onActivitiesChange(activities: Activity[]) {
    this.activities = activities;
  }

  onActiveIndexChange(activeIndex: number) {
    this.isRequestChosen = activeIndex < 0;
    this.activeItemIndex = activeIndex;
  }

  onEditRepair(): void {

  }

  onCreateRepair(): void {

  }

  onCloseDialog(response = false): void {
    this.ref.close(response);
  }
}
