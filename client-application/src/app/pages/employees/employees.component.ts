import { Component, OnInit } from '@angular/core';
import { UniversalTableColumn } from 'src/app/components/table/column.model';
import { DialogService } from 'primeng/dynamicdialog';
import { EmployeesDialogComponent } from './employees-dialog/employees-dialog.component';
import { EmployeeService } from 'src/app/services/employee.service';
import { HttpResponse } from '@angular/common/http';
import { Employee } from 'src/app/entitites/employee-model';
import { MessageService } from 'primeng/api';

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
    private employeeService: EmployeeService,
    private messageService: MessageService
  ) { }

  ngOnInit() {
    this.loadColums();
    this.loadEmployees();
   }

   loadColums(): void {
    this.columns = [
      {
        header: "Pracownik",
        field: "fullName"
      },
      {
        header: "Telefon",
        field: "phoneNumber"
      },
      {
        header: "Email",
        field: "email"
      },
      // {
      //   header: "Rola",
      //   field: "roleId",
      //   subField: "name"
      // },
    ];
   }

   loadEmployees(): void {
      this.employeeService.getAllNotDeleted().subscribe(
        (res: HttpResponse<Employee[]>) => {
          this.employees = res.body ?? [];
        }
      );
   }

   onEmployeeSelected(event: Employee): void {
      this.selectedEmployee = event;
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
      if(response) {
        this.loadEmployees();
      }
   }

   handleEmployeesDeleteDialog(response: boolean): void {
    if(response) {
      this.employeeService.delete(this.selectedEmployee?.employeeId!).subscribe(
        {
          next: () => {
            this.messageService.add({key: 'mainToast', severity: 'success', summary: 'Success',
              detail: 'usunięto!'});
            this.loadEmployees();
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
}
