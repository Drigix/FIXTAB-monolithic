import { Injectable } from '@angular/core';

@Injectable({providedIn: 'root'})
export class AuthorityService {

  private isUserAuthenticate = false;

  constructor() { }

  private checkAuthentication(): boolean {
    return window.sessionStorage.getItem('token') !== ('' || undefined || null) ?? false;
  }

  setUserAuthenticate(auth: boolean): void {
    this.isUserAuthenticate = auth;
  }

  getUserAuthenticate(): boolean {
    this.setUserAuthenticate(this.checkAuthentication());
    return this.isUserAuthenticate;
  }

}
