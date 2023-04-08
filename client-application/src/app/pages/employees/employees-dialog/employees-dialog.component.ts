import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Employee, EmployeePassword } from 'src/app/entitites/employee-model';
import { EmployeeRole } from 'src/app/entitites/employee-role.model';
import { ChangeDateService } from 'src/app/services/change-date.service';
import { EmployeeRoleService } from 'src/app/services/employee-role.service';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'fixtab-employees-dialog',
  templateUrl: './employees-dialog.component.html',
  styleUrls: ['./employees-dialog.component.scss']
})

export class EmployeesDialogComponent implements OnInit {

  employeeForm = this.fb.group({
    name: ['', Validators.required],
    surname: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    birthDay: ['', [Validators.required]],
    phoneNumber: ['', [Validators.required, Validators.pattern("[0-9]{11}")]],
    gender: ['', Validators.required],
    pesel: ['', [Validators.required, Validators.pattern("[0-9]{11}")]],
    role: ['', Validators.required]
  });

  employee: Employee = new Employee();
  employeeBirthDate: Date | null = null;
  roles: EmployeeRole[] = [];
  selectedRole: EmployeeRole | null = null;
  showPassword = false;
  password: EmployeePassword | null = new EmployeePassword();
  edit = false;

  constructor(
    private fb: UntypedFormBuilder,
    private employeeService: EmployeeService,
    private ref: DynamicDialogRef,
    private config: DynamicDialogConfig,
    private changeDateService: ChangeDateService,
    private employeeRoleService: EmployeeRoleService,
    private messageService: MessageService
  ) { }

  ngOnInit() {
    this.loadRoles();
    this.edit = this.config.data.edit;
    if(this.edit) {
      this.employee = this.config.data.employee;
      this.employeeBirthDate = new Date(this.employee.birthDate!);
    }
  }

  loadRoles(): void {
    this.employeeRoleService.getAll().subscribe(
      (res: HttpResponse<EmployeeRole[]>) => {
        this.roles = res.body ?? [];
        if(this.edit) {
          this.selectedRole = this.roles.find(role => role.roleId === this.employee.roleId)!;
        }
      }
    )
  }

  onCreateEmployee(): void {
    this.employee.birthDate = this.changeDateService.changeDateToString(this.employeeBirthDate!);
    this.employee.roleId = this.selectedRole?.roleId;
    this.employeeService.create(this.employee).subscribe(
      (res: HttpResponse<EmployeePassword>) => {
        this.password = res.body;
        this.showPassword = true;
        this.messageService.add({key: 'mainToast', severity: 'success', summary: 'Success',
              detail: 'utworzono!'});
      }
    );
  }

  onEditEmployee(): void {
    this.employee.birthDate = this.changeDateService.changeDateToString(this.employeeBirthDate!);
    this.employee.roleId = this.selectedRole?.roleId;
    this.employeeService.edit(this.employee).subscribe(
      {
        next: (response) => {
          this.messageService.add({key: 'mainToast', severity: 'success', summary: 'Success',
              detail: 'edytowano!'});
          this.ref.close(response);
        },
        error: (error) => {
          this.messageService.add({key: 'mainToast', severity: 'success', summary: 'Success',
              detail: 'nie edytowano!'});
        }
      }
    );
  }

  onCloseDialog(response = false): void {
    this.ref.close(response);
  }
}
