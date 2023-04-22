import { Injectable } from '@angular/core';
import { IMenuItem } from 'src/app/entitites/menu-item.model';
import { Authority } from '../../auth/authority.model';
import { AuthorityGroupService } from 'src/app/auth/authority-group.service';

@Injectable({providedIn: 'root'})
export class MenuService {

  constructor() { }

  getMenuItems(): IMenuItem[] {
    const items: IMenuItem[] = [
      this.createMenuItem('fa fa-home', 'Główna', '/', AuthorityGroupService.getCompanyAuthorityGroup()),
      this.createMenuItem('fa fa-users', 'Pracownicy', 'employees', AuthorityGroupService.getAdminAuthorityGroup()),
      this.createMenuItem('fa fa-gear', 'Naprawy', 'repairs', AuthorityGroupService.getManagementAuthorityGroup()),
      this.createMenuItem('fa fa-address-book', 'Klienci', 'clients', AuthorityGroupService.getCompanyAuthorityGroup()),
      this.createMenuItem('fa fa-cube', 'Obiekty', 'objects', AuthorityGroupService.getCompanyAuthorityGroup()),
      this.createMenuItem('fa fa-tasks', 'Zadania', 'activities', AuthorityGroupService.getCompanyAuthorityGroup()),
      this.createMenuItem('fa fa-archive', 'Archiwum', 'archive', AuthorityGroupService.getManagementAuthorityGroup())
    ];
    return items;
  }

  private createMenuItem(icon: string | null, label: string, routerLink: string, auth: string[], additionalOptions?: any): IMenuItem {
    const menuItem = {
        icon: icon ? icon : '',
        label: label,
        routerLink,
        title: label,
        auth
    };
    Object.assign(menuItem, additionalOptions);
    return menuItem;
  }

}
