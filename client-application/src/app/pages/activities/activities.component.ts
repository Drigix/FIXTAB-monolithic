import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { DialogService } from 'primeng/dynamicdialog';
import { UniversalTableColumn } from 'src/app/components/table/column.model';
import { Activity } from 'src/app/entitites/activity.model';
import { ActivitiesService } from 'src/app/services/activities.service';
import { ActivitiesDialogComponent } from './activities-dialog/activities-dialog.component';
import { Employee } from 'src/app/entitites/employee-model';

@Component({
  selector: 'fixtab-activities',
  templateUrl: './activities.component.html'
})

export class ActivitiesComponent implements OnInit {

  columns: UniversalTableColumn[] = [];
  activities: Activity[] = [];
  selectedActivity: Activity | null = null;

  constructor(
    private activitiesService: ActivitiesService,
    private dialogService: DialogService
  ) { }

  ngOnInit() {
    this.loadColumns();
    this.loadActivities();
  }

  loadColumns(): void {
    this.columns = [
      {
        header: 'Zlecone przez',
        field: 'manager',
        subField: 'fullName'
      },
      {
        header: 'Data utworzenia',
        field: 'createDate'
      },
      {
        header: 'Status zadania',
        field: 'result',
        subField: 'name'
      }
    ];
  }

  loadActivities(): void {
    this.activitiesService.getAllNotDeleted().subscribe(
      (res: HttpResponse<Activity[]>) => {
        this.activities = res.body ?? [];
        this.activities.forEach( activity => {
          this.activitiesService.getActivityManager(activity.activityId!).subscribe(
            (res: HttpResponse<Employee>) => {
              activity.manager = res.body!;
            }
          );
        });
      }
    );
  }

  onActivitySelected(activity: Activity): void {
    this.selectedActivity = activity;
  }

  openActivitysDialog(changeResult = false): void {
    this.dialogService.open(ActivitiesDialogComponent, {
      header: changeResult ? 'Edytuj status zadania' : 'Zadanie',
      width: '60%',
      height: '60%',
      data: {
        changeStatus: changeResult,
        activity: this.selectedActivity
      }
    });
  }
}
