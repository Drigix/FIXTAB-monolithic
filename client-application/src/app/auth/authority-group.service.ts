import { Injectable } from '@angular/core';
import { Authority } from './authority.model';

@Injectable({providedIn: 'root'})
export class AuthorityGroupService {

  constructor() { }

  static getAdminAuthorityGroup(): Authority[] {
    return [Authority.ROLE_ADMIN];
  }

  static getManagementAuthorityGroup(): Authority[] {
    return [Authority.ROLE_ADMIN, Authority.ROLE_MANAGER];
  }

  static getCompanyAuthorityGroup(): Authority[] {
    return [Authority.ROLE_ADMIN, Authority.ROLE_MANAGER, Authority.ROLE_EMPLOYEE];
  }
}
