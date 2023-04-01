import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UserLogin } from 'src/app/entitites/user-login.model';
import { LoginService } from 'src/app/services/login.service';
import { AuthorityService } from '../authority.service';

@Component({
  selector: 'fixtab-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  user: UserLogin = new UserLogin();
  token: any | null = null;

  constructor(
    private loginService: LoginService,
    private authorityService: AuthorityService
  ) { }

  ngOnInit(): void {
  }

  onLogin(): void {
    this.loginService.login(this.user).subscribe(
      (res: HttpResponse<string>) => {
        this.token = res.body;
        console.log(this.token['accessToken']);
        this.authorityService.setToken(this.token['accessToken'], this.token['expirationDate']);
        window.location.reload();
      }
    )
  }

}
