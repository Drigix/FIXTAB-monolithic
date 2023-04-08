import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { DialogService } from 'primeng/dynamicdialog';
import { UniversalTableColumn } from 'src/app/components/table/column.model';
import { TargetObject } from 'src/app/entitites/object.model';
import { ObjectsService } from 'src/app/services/objects.service';
import { ObjectsDialogComponent } from './objects-dialog/objects-dialog.component';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'fixtab-objects',
  templateUrl: './objects.component.html'
})

export class ObjectsComponent implements OnInit {

  objects: TargetObject[] = [];
  columns: UniversalTableColumn[] = [];
  selectedObject: TargetObject | null = null;
  openDeleteDialog = false;

  constructor(
    private objectsService: ObjectsService,
    private dialogService: DialogService,
    private messageService: MessageService
  ) { }

  ngOnInit() {
    this.loadColumns();
    this.loadObjects();
   }

  loadColumns(): void {
    this.columns = [
      {
        header: 'Nazwa',
        field: 'name'
      },
      {
        header: 'Klient',
        field: 'client',
        subField: 'name'
      },
      {
        header: 'Typ',
        field: 'objectType',
        subField: 'nameType'
      },
    ];
  }

  loadObjects(): void {
    this.objectsService.getAllNotDeleted().subscribe(
      (res: HttpResponse<TargetObject[]>) => {
        this.objects = res.body ?? [];
        this.objects.forEach( object => {
          object!.client!.name += " " + object.client?.surname;
        })
      }
    );
  }

  onObjectSelected(object: TargetObject): void {
    this.selectedObject = object;
  }

  openObjectDialog(edit: boolean): void {
    const ref = this.dialogService.open(ObjectsDialogComponent, {
      header: edit ? 'Edytuj obiekt' : 'Dodaj obiekt',
      width: '60%',
      data: {
        edit: edit,
        object: this.selectedObject
      }
    });
    ref.onClose.subscribe((response) => this.handleClientDialog(response));
  }

  openObjectDeleteDialog(response: boolean): void {
    this.openDeleteDialog = response;
  }

  handleObjectDeleteDialog(event: boolean): void {
    if(event) {
      this.objectsService.delete(this.selectedObject?.targetId!).subscribe(
        {
          next: () => {
            this.messageService.add({key: 'mainToast', severity: 'success', summary: 'Success',
                detail: 'usunięto!'});
            this.loadObjects();
          },
          error: () => {
            this.messageService.add({key: 'mainToast', severity: 'error', summary: 'Error',
                detail: 'nie usunięto!'});
          }
        }
      )
    }
    this.openDeleteDialog = false;
  }

  handleClientDialog(response: any): void {
    if(response) {
      this.loadObjects();
    }
  }
}
