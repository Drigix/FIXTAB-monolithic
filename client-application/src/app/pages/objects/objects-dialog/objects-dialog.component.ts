import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Client } from 'src/app/entitites/client.model';
import { ObjectType, TargetObject, TargetObjectRequest } from 'src/app/entitites/object.model';
import { ChangeDateService } from 'src/app/services/change-date.service';
import { ClientsService } from 'src/app/services/clients.service';
import { ObjectsService } from 'src/app/services/objects.service';

@Component({
  selector: 'fixtab-objects-dialog',
  templateUrl: './objects-dialog.component.html',
  styleUrls: ['./objects-dialog.component.scss']
})

export class ObjectsDialogComponent implements OnInit {

  objectForm = this.fb.group({
    name: ['', Validators.required],
    client: ['', Validators.required],
    objectType: ['', Validators.required],
  });

  object: TargetObject = new TargetObject();
  selectedClient: Client | null = null;
  objectType: ObjectType = new ObjectType();
  clients: Client[] = [];
  edit = false;

  constructor(
    private fb: UntypedFormBuilder,
    private ref: DynamicDialogRef,
    private config: DynamicDialogConfig,
    private objectService: ObjectsService,
    private messageService: MessageService,
    private changeDateService: ChangeDateService,
    private clientsService: ClientsService
  ) { }

  ngOnInit() {
    this.edit = this.config.data.edit;
    if(this.edit) {
      this.object = this.config.data.object;
      this.objectType = this.object.objectType!;
    }
    this.loadClients();
  }

  loadClients(): void {
    this.clientsService.getAllNotDeleted().subscribe(
      (res: HttpResponse<Client[]>) => {
        this.clients = res.body ?? [];
        if(this.edit) {
          this.selectedClient = this.clients.find(client => client.clientId === this.object.client?.clientId)!;
        }
      }
    );
  }

  onEditObject(): void {
    this.objectService.update(this.object).subscribe(
      {
        next: (response) => {
          this.messageService.add({key: 'mainToast', severity: 'success', summary: 'Success',
                detail: 'edytowano!'});
          this.ref.close(response);
        },
        error: (error) => {
          this.messageService.add({key: 'mainToast', severity: 'error', summary: 'Error',
                detail: 'nie edytowano!'});
        }
      }
    )
  }

  onCreateObject(): void {
    const objectRequest = new TargetObjectRequest(undefined, this.object.name, this.selectedClient?.clientId, this.objectType);
    this.objectService.create(objectRequest).subscribe(
      {
        next: (response) => {
          this.messageService.add({key: 'mainToast', severity: 'success', summary: 'Success',
                detail: 'dodano!'});
          this.ref.close(response);
        },
        error: (error) => {
          this.messageService.add({key: 'mainToast', severity: 'error', summary: 'Error',
                detail: 'nie dodano!'});
        }
      }
    )
  }

  onCloseDialog(): void {
    this.ref.close();
  }
}
