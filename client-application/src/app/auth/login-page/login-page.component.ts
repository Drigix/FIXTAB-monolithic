import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'fixtab-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  value = '';

  constructor() { }

  ngOnInit(): void {
  }

}
