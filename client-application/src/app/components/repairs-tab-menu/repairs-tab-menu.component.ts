import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Activity, ActivityRequest } from 'src/app/entitites/activity.model';

@Component({
  selector: 'fixtab-repairs-tab-menu',
  templateUrl: './repairs-tab-menu.component.html'
})

export class RepairsTabMenuComponent implements OnInit {

  @Output() emitActivities = new EventEmitter<Activity[]>();
  @Output() emitActiveIndex = new EventEmitter<number>();

  activities: ActivityRequest[] = [];
  repairsHeaderItems: MenuItem[] = [];
  activeItem?: MenuItem;
  activeItemIndex?: number;
  isRequestChosen = true;
  edit = false;
  addActivityItem: MenuItem = {
    id: (this.repairsHeaderItems.length - 1).toString(),
    icon: 'pi pi-plus',
    command: () => {
      this.onMenuChange(this.repairsHeaderItems.length - 1);
    }
  };

  constructor() { }

  ngOnInit() {
    this.repairsHeaderItems = [
      {
        id: '0',
        label: 'Zlecenie',
        command: () => {
          this.onMenuChange(0);
        }
      },
      this.addActivityItem
    ];
    this.activeItem = this.repairsHeaderItems[0];
  }

  onMenuChange(index: number): void {
    this.activeItem = this.repairsHeaderItems[index];
    if(this.repairsHeaderItems.length === index + 1) {
      this.repairsHeaderItems[index] = { id: index.toString(), label: `Zadanie ${index}`, command: () => { this.onMenuChange(index); }};
      this.activeItem = this.repairsHeaderItems[index];
      this.repairsHeaderItems.push(this.addActivityItem);
      this.activities.push(new ActivityRequest(index, `Zadanie ${index}`));
      this.onEmitActivities();
    }
    this.activeItemIndex = Number(this.activeItem!.id!) - 1;
    this.onEmitActiveIndex();
    this.isRequestChosen = index == 0;
  }

  onDeleteActivity(index: number) {
    if(index === this.repairsHeaderItems.length - 2) {
      const item = this.repairsHeaderItems[index];
      this.repairsHeaderItems.splice(index, 1);
      this.activities.splice(index - 1, 1);
      this.activeItemIndex! -= 1;
    } else {
      const item = this.repairsHeaderItems[index];
      this.repairsHeaderItems.splice(index, 1);
      this.repairsHeaderItems.forEach((item, index) => {
        if(index > 0 && index !== this.repairsHeaderItems.length - 1) {
          item.id = index.toString(),
          item.label = `Zadanie ${index}`,
          item.command = () => { this.onMenuChange(index); }
        }
      });
      this.activities.splice(index - 1, 1);
      this.activities.forEach((item, index) => {
        item.sequenceNumber = index;
        item.description = `Zadanie ${index + 1}`
      });
    }
    this.onEmitActiveIndex();
    this.onEmitActivities();
    console.log(this.repairsHeaderItems);
    console.log(this.activities);
  }

  onEmitActivities(): void {
    this.emitActivities.emit(this.activities);
  }

  onEmitActiveIndex(): void {
    this.emitActiveIndex.emit(this.activeItemIndex);
  }
}
