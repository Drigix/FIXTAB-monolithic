import { Injectable } from '@angular/core';
import { IMenuItem } from 'src/app/entitites/menu-item.model';
import { Authority } from '../../auth/authority.model';

@Injectable({providedIn: 'root'})
export class MenuService {

  constructor() { }

  getMenuItems(): IMenuItem[] {
    const items: IMenuItem[] = [
      this.createMenuItem('fa fa-home', 'Główna', '/', [Authority.ROLE_ADMIN, Authority.ROLE_MANAGER, Authority.ROLE_EMPLOYEE]),
      this.createMenuItem('fa fa-users', 'Pracownicy', 'employees', [Authority.ROLE_ADMIN, Authority.ROLE_MANAGER]),
      this.createMenuItem('fa fa-gear', 'Naprawy', 'repairs', [Authority.ROLE_ADMIN, Authority.ROLE_MANAGER]),
      this.createMenuItem('fa fa-address-book', 'Klienci', 'clients', [Authority.ROLE_ADMIN, Authority.ROLE_MANAGER]),
      this.createMenuItem('fa fa-cube', 'Obiekty', 'objects', [Authority.ROLE_ADMIN, Authority.ROLE_MANAGER]),
      this.createMenuItem('fa fa-archive', 'Archiwum', 'archive', [Authority.ROLE_ADMIN, Authority.ROLE_MANAGER])
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
