import { Component, OnInit } from '@angular/core';
import { UniversalTableColumn } from 'src/app/components/table/column.model';
import { DialogService } from 'primeng/dynamicdialog';
import { EmployeesDialogComponent } from './employees-dialog/employees-dialog.component';
import { EmployeeService } from 'src/app/services/employee.service';
import { HttpResponse } from '@angular/common/http';
import { Employee } from 'src/app/entitites/employee-model';

@Component({
  selector: 'fixtab-employees',
  templateUrl: './employees.component.html'
})

export class EmployeesComponent implements OnInit {

  columns: UniversalTableColumn[] = [];
  employees: Employee[] = [];
  selectedEmployee: Employee | null = null;
  openDeleteDialog = false;

  constructor(
    private dialogService: DialogService,
    private employeeService: EmployeeService
  ) { }

  ngOnInit() {
    this.loadColums();
    this.loadEmployees();
   }

   loadColums(): void {
    this.columns = [
      {
        header: "Imie",
        field: "name"
      },
      {
        header: "Nazwisko",
        field: "surname"
      },
      {
        header: "Telefon",
        field: "phoneNumber"
      },
      {
        header: "Data urodzenia",
        field: "birthDate"
      },
      // {
      //   header: "Rola",
      //   field: "roleId",
      //   subField: "name"
      // },
    ];
   }

   loadEmployees(): void {
      this.employeeService.getAll().subscribe(
        (res: HttpResponse<Employee[]>) => {
          this.employees = res.body ?? [];
        }
      );
   }

   onEmployeeSelected(event: Employee): void {
      this.selectedEmployee = event;
      console.log(this.selectedEmployee);
   }

   openEmployeesDialog(edit: boolean): void {
      const ref = this.dialogService.open(EmployeesDialogComponent, {
        header: edit ? 'Edytuj pracownika' : 'Dodaj pracownika',
        width: '60%',
        height: '60%',
        data: {
          edit: edit,
          employee: this.selectedEmployee
        }
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
    if(response) {
      this.employeeService.delete(this.selectedEmployee?.employeeId!).subscribe(
        {
          next: () => {
            console.log('usunieto');
          },
          error: () => {
            console.log('error');
          }
        }
      )
    }
    this.openDeleteDialog = false;
  }
}
