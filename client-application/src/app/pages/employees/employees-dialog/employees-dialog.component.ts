import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, Validators } from '@angular/forms';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Employee } from 'src/app/entitites/employee-model';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'fixtab-employees-dialog',
  templateUrl: './employees-dialog.component.html'
})

export class EmployeesDialogComponent implements OnInit {

  employeeForm = this.fb.group({
    name: ['', Validators.required],
    surname: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    birthDay: ['', [Validators.required]],
    phoneNumber: ['', [Validators.required, Validators.pattern("[0-9]{11}")]],
    gender: ['', Validators.required],
    pesel: ['', [Validators.required, Validators.pattern("[0-9]{11}")]]
  })

  employee: Employee = new Employee();
  employeeBirthDate: Date | null = null;
  showPassword = false;
  password: string | null = null;
  edit = false;

  constructor(
    private fb: UntypedFormBuilder,
    private employeeService: EmployeeService,
    private ref: DynamicDialogRef,
    public config: DynamicDialogConfig
  ) { }

  ngOnInit() {
    this.edit = this.config.data.edit;
    if(this.edit) {
      this.employee = this.config.data.employee;
      this.employeeBirthDate = new Date(this.employee.birthDate!);
    }
  }

  onCreateEmployee(): void {
    this.employee.birthDate = this.employeeBirthDate?.toString();
    this.employeeService.create(this.employee).subscribe(
      {
        next: (response) => {
          this.showPassword = true;
          this.password = response.body;
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
