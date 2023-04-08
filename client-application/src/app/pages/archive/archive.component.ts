import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UniversalTableColumn } from 'src/app/components/table/column.model';
import { Archive } from 'src/app/entitites/archive.model';
import { Client } from 'src/app/entitites/client.model';
import { Employee } from 'src/app/entitites/employee-model';
import { TargetObject } from 'src/app/entitites/object.model';
import { ClientsService } from 'src/app/services/clients.service';
import { EmployeeService } from 'src/app/services/employee.service';
import { ObjectsService } from 'src/app/services/objects.service';

@Component({
  selector: 'fixtab-archive',
  templateUrl: './archive.component.html',
  styleUrls: ['./archive.component.scss']
})

export class ArchiveComponent implements OnInit {

  archiveTables: Archive[] = [];
  selectedTable: Archive | null = null;
  columns: UniversalTableColumn[] = [];
  values: any[] = [];

  constructor(
    private employeeService: EmployeeService,
    private clientsService: ClientsService,
    private objectsService: ObjectsService
  ) { }

  ngOnInit() {
    this.loadArchiveTables();
  }

  loadColumns(optionalColumns: UniversalTableColumn[] | undefined = undefined): void {
    this.columns = [
      {
        header: 'Stworzony przez',
        field: 'createdBy'
      },
      {
        header: 'Data stworzenia',
        field: 'createdDate'
      },
      {
        header: 'Ostania edycja',
        field: 'modifiedBy'
      },
      {
        header: 'Data ostatniej edycjii',
        field: 'modifiedDate'
      }
    ];
    if(optionalColumns !== undefined) {
      this.columns = optionalColumns.concat(this.columns);
    }
  }

  loadArchiveTables(): void {
    this.archiveTables = [
      {
        archiveId: 1,
        tableName: 'Pracownicy'
      },
      {
        archiveId: 2,
        tableName: 'Naprawy'
      },
      {
        archiveId: 3,
        tableName: 'Klienci'
      },
      {
        archiveId: 4,
        tableName: 'Obiekty'
      },
    ];
  }

  onArchiveTableChange(): void {
    switch(this.selectedTable?.archiveId) {
      case 1:
        this.loadEmployees();
        break;
      case 2:
        this.loadRepairs();
        break;
      case 3:
        this.loadClients();
        break;
      case 4:
        this.loadObjects();
        break;
    }
  }

  loadEmployees(): void {
    this.loadColumns(
      [
        {
          header: 'Pracownik',
          field: 'name'
        }
      ]
    );
    this.employeeService.getAll().subscribe(
      (res: HttpResponse<Employee[]>) => {
        this.values = res.body ?? [];
        this.values.forEach((value: Employee) => {
          value.name += ' ' + value.surname;
        })
      }
    );
  }

  loadRepairs(): void {
    this.loadColumns();
  }

  loadClients(): void {
    this.loadColumns(
      [
        {
          header: 'Imie',
          field: 'name'
        }
      ]
    );
    this.clientsService.getAll().subscribe(
      (res: HttpResponse<Client[]>) => {
        this.values = res.body ?? [];
      }
    );
  }

  loadObjects(): void {
    this.loadColumns(
      [
        {
          header: 'Przedmiot',
          field: 'name'
        }
      ]
    );
    this.objectsService.getAll().subscribe(
      (res: HttpResponse<TargetObject[]>) => {
        this.values = res.body ?? [];
      }
    );
  }
}
