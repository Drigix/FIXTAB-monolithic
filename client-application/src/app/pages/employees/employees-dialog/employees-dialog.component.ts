import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, Validators } from '@angular/forms';
import { Employee } from 'src/app/entitites/employee-model';

@Component({
  selector: 'fixtab-employees-dialog',
  templateUrl: './employees-dialog.component.html'
})

export class EmployeesDialogComponent implements OnInit {

  employeeForm = this.fb.group({
    name: ['', Validators.required],
    surname: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    birthDay: ['', [Validators.required, Validators.email]],
    phoneNumber: ['', [Validators.required, Validators.pattern("[0-9 ]{11}")]],
    password: ['', [Validators.required, Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}')]],
    confirmPassword: ['', [Validators.required, Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}')]],
  })

  employee: Employee = new Employee();

  constructor(
    private fb: UntypedFormBuilder
  ) { }

  ngOnInit() { }
}
