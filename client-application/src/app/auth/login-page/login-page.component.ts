import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UserLogin } from 'src/app/entitites/user-login.model';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'fixtab-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  user: UserLogin = new UserLogin();
  token: string | null = null;

  constructor(
    private loginService: LoginService
  ) { }

  ngOnInit(): void {
  }

  onLogin(): void {
    this.loginService.login(this.user).subscribe(
      (res: HttpResponse<string>) => {
        this.token = res.body;
      }
    )
  }

}
