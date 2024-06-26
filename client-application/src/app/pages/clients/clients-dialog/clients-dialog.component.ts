import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Address } from 'src/app/entitites/address.model';
import { Client } from 'src/app/entitites/client.model';
import { ChangeDateService } from 'src/app/services/change-date.service';
import { ClientsService } from 'src/app/services/clients.service';

@Component({
  selector: 'fixtab-clients-dialog',
  templateUrl: './clients-dialog.component.html'
})

export class ClientsDialogComponent implements OnInit {

  clientForm = this.fb.group({
    name: ['', Validators.required],
    surname: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    birthDay: ['', [Validators.required]],
    phoneNumber: ['', [Validators.required, Validators.pattern("[0-9]{11}")]],
    city: ['', Validators.required],
    postalCode: ['', Validators.required],
    street: ['', Validators.required],
    streetNumber: ['', Validators.required]
  });

  client: Client = new Client();
  address: Address = new Address();
  clientBirthDate: Date | null = null;
  maxClientBirthDate = new Date();
  edit = false;

  constructor(
    private fb: UntypedFormBuilder,
    private ref: DynamicDialogRef,
    private config: DynamicDialogConfig,
    private clientService: ClientsService,
    private messageService: MessageService,
    private changeDateService: ChangeDateService
  ) { }

  ngOnInit() {
    this.edit = this.config.data.edit;
    if(this.edit) {
      this.client = this.config.data.client;
      this.address = this.client.address!;
      this.clientBirthDate = new Date(this.client.birthDate!);
    }
    this.maxClientBirthDate.setFullYear(this.maxClientBirthDate.getFullYear() - 18);
  }

  onEditClient(): void {
    this.client.address = this.address;
    this.client.birthDate = this.changeDateService.changeDateToString(this.clientBirthDate!);
    this.clientService.update(this.client).subscribe(
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

  onCreateClient(): void {
    this.client.address = this.address;
    this.client.birthDate = this.changeDateService.changeDateToString(this.clientBirthDate!);
    this.clientService.create(this.client).subscribe(
      {
        next: (response) => {
          this.messageService.add({key: 'mainToast', severity: 'success', summary: 'Success',
                detail: 'utworzono!'});
          this.ref.close(response);
        },
        error: (error) => {
          this.messageService.add({key: 'mainToast', severity: 'success', summary: 'Success',
                detail: 'utworzono!'});
        }
      }
    )
  }

  onCloseDialog(): void {
    this.ref.close();
  }
}
