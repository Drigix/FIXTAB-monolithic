import { Component, OnInit } from '@angular/core';
import { UntypedFormArray, UntypedFormBuilder, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { DynamicDialogRef } from 'primeng/dynamicdialog';
import { EmployeePassword } from 'src/app/entitites/employee-model';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'fixtab-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})

export class ProfileComponent implements OnInit {

  passwordForm = this.fb.group({
    oldPassword: ['', [Validators.required]],
    newPassword: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(30)]],
    confirmPassword: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(30)]]
  })

  password: EmployeePassword = new EmployeePassword();
  confirmPassword: string | null = null;

  constructor(
    private fb: UntypedFormBuilder,
    private ref: DynamicDialogRef,
    private messageService: MessageService,
    private employeeService: EmployeeService
  ) { }

  ngOnInit() { }

  onPasswordChange(): void {
    if(this.password.password !== this.confirmPassword) {
      this.messageService.add({key: 'mainToast', severity: 'error', summary: 'Error',
              detail: 'Hasła róznią się od siebie!'});
    } else {
      this.employeeService.changePassword(this.password).subscribe(
        {
          next: (response) => {
            console.log(response);
          },
          error: (error) => {
            console.log(error);
          }
        }
      )
    }
  }

  onClose(): void {
    this.ref.close();
  }
}
