import { Component, OnInit } from '@angular/core';
import { AuthorityService } from './auth/authority.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  title = 'my-app';
  isAuthorized = false;

  constructor(
    private authorityService: AuthorityService
  ) {}

  ngOnInit(): void {
      this.isAuthorized = this.authorityService.getUserAuthenticate();
  }
}
