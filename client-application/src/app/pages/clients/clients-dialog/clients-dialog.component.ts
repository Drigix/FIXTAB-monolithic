import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, Validators } from '@angular/forms';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Address } from 'src/app/entitites/address.model';
import { Client } from 'src/app/entitites/client.model';
import { Employee } from 'src/app/entitites/employee-model';
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
  clientBirthDate: Date = new Date();
  edit = false;

  constructor(
    private fb: UntypedFormBuilder,
    private ref: DynamicDialogRef,
    private config: DynamicDialogConfig,
    private clientService: ClientsService
  ) { }

  ngOnInit() {
    this.edit = this.config.data.edit;
    if(this.edit) {
      this.client = this.config.data.client;
      this.clientBirthDate = new Date(this.client.birthDate!);
    }
  }

  onEditClient(): void {

  }

  onCreateClient(): void {
    this.client.address = this.address;
    this.clientService.create(this.client).subscribe(
      {
        next: (response) => {
          this.ref.close(response);
        },
        error: (error) => {
          console.log(error);
        }
      }
    )
  }

  onCloseDialog(): void {
    this.ref.close();
  }
}