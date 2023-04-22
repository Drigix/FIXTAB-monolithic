import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UniversalTableColumn } from 'src/app/components/table/column.model';
import { Activity } from 'src/app/entitites/activity.model';
import { ActivitiesService } from 'src/app/services/activities.service';

@Component({
  selector: 'fixtab-activities',
  templateUrl: './activities.component.html'
})

export class ActivitiesComponent implements OnInit {

  columns: UniversalTableColumn[] = [];
  activities: Activity[] = [];
  selectedActivity: Activity | null = null;

  constructor(
    private activitiesService: ActivitiesService
  ) { }

  ngOnInit() {
    this.loadColumns();
    this.loadActivities();
  }

  loadColumns(): void {
    this.columns = [
      {
        header: 'Zlecone przez',
        field: 'createdBy'
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
      }
    );
  }

  onActivitySelected(activity: Activity): void {
    this.selectedActivity = activity;
  }

  openActivitysDialog(changeResult = false): void {

  }
}
