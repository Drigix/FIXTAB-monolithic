import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { DialogService } from 'primeng/dynamicdialog';
import { UniversalTableColumn } from 'src/app/components/table/column.model';
import { Activity } from 'src/app/entitites/activity.model';
import { ActivitiesService } from 'src/app/services/activities.service';
import { ActivitiesDialogComponent } from './activities-dialog/activities-dialog.component';
import { Employee } from 'src/app/entitites/employee-model';
import { StatusDictionary } from 'src/app/entitites/result-dictionary.model';
import { AuthorityService } from 'src/app/auth/authority.service';
import { AuthorityGroupService } from 'src/app/auth/authority-group.service';

@Component({
  selector: 'fixtab-activities',
  templateUrl: './activities.component.html'
})

export class ActivitiesComponent implements OnInit {

  statusCancel = StatusDictionary.statusCancel;
  statusFinish = StatusDictionary.statusFinish;

  columns: UniversalTableColumn[] = [];
  activities: Activity[] = [];
  selectedActivity: Activity | null = null;
  isEndActivity = false;

  constructor(
    private activitiesService: ActivitiesService,
    private dialogService: DialogService,
    private authorityService: AuthorityService
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
        field: 'status',
        subField: 'name'
      }
    ];
  }

  loadActivities(): void {
    this.activitiesService.getAllNotDeletedForEmployee().subscribe(
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
    if((this.selectedActivity.status?.statusId === this.statusCancel.statusId ||
       this.selectedActivity.status?.statusId === this.statusFinish.statusId)) {
        this.isEndActivity = this.checkRole();
    } else {
      this.isEndActivity = false;
    }
  }

  checkRole(): boolean {
    let result = true;
    AuthorityGroupService.getManagementAuthorityGroup().forEach( (authority, index) => {
      if(authority.toString() === this.authorityService.getUserRole()) {
        result = false;
      }
    });
    return result;
  }

  openActivitysDialog(changeResult = false): void {
    const ref = this.dialogService.open(ActivitiesDialogComponent, {
      header: changeResult ? 'Edytuj status zadania' : 'Zadanie',
      width: '60%',
      height: '60%',
      data: {
        changeStatus: changeResult,
        activity: this.selectedActivity
      }
    });
    ref.onClose.subscribe((response) => this.handleActivityDialog(response));
  }

  handleActivityDialog(response: any): void {
    if(response) {
      this.loadActivities();
    }
 }
}
