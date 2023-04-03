import { Component, OnInit } from '@angular/core';
import { UniversalTableColumn } from 'src/app/components/table/column.model';
import { DialogService } from 'primeng/dynamicdialog';
import { EmployeesDialogComponent } from './employees-dialog/employees-dialog.component';

@Component({
  selector: 'fixtab-employees',
  templateUrl: './employees.component.html'
})

export class EmployeesComponent implements OnInit {

  columns: UniversalTableColumn[] = [];
  values: any[] = [];
  selectedEmployee: any;
  openDeleteDialog = false;

  constructor(
    private dialogService: DialogService
  ) { }

  ngOnInit() {
    this.columns = [
      {
        header: "Imie",
        name: "Name",
        field: "name"
      },
      {
        header: "Nazwisko",
        name: "Surname",
        field: "surname"
      },
    ];
    this.values = [
      {
        name: "Michał",
        surname: "Ławinski",
      },
      {
        name: "Michał",
        surname: "Kowalski",
      },
      {
        name: "Michał",
        surname: "Ławinski",
      },
      {
        name: "Michał",
        surname: "Kowalski",
      },{
        name: "Michał",
        surname: "Ławinski",
      },
      {
        name: "Michał",
        surname: "Kowalski",
      },{
        name: "Michał",
        surname: "Ławinski",
      },
      {
        name: "Michał",
        surname: "Kowalski",
      },
      {
        name: "Michał",
        surname: "Ławinski",
      },
      {
        name: "Michał",
        surname: "Kowalski",
      },
      {
        name: "Michał",
        surname: "Ławinski",
      },
      {
        name: "Michał",
        surname: "Kowalski",
      },{
        name: "Michał",
        surname: "Ławinski",
      },
      {
        name: "Michał",
        surname: "Kowalski",
      },{
        name: "Michał",
        surname: "Ławinski",
      },
      {
        name: "Michał",
        surname: "Kowalski",
      }
    ]
   }

   onEmployeeSelected(event: any): void {
      this.selectedEmployee = event;
   }

   openEmployeesDialog(edit: boolean): void {
      const ref = this.dialogService.open(EmployeesDialogComponent, {
        header: edit ? 'Edytuj pracownika' : 'Dodaj pracownika',
        width: '60%',
        height: '60%'
      });
      ref.onClose.subscribe((response) => this.handleEmployeesDialog(response));
   }

   openEmployeesDeleteDialog(event: boolean) {
      this.openDeleteDialog = event;
   }

   handleEmployeesDialog(response: any): void {
      console.log(response);
   }

   handleEmployeesDeleteDialog(response: boolean): void {
    this.openDeleteDialog = false;
  }
}
