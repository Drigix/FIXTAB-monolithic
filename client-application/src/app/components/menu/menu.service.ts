import { Injectable } from '@angular/core';
import { IMenuItem } from 'src/app/entitites/menu-item.model';

@Injectable({providedIn: 'root'})
export class MenuService {

  constructor() { }

  getMenuItems(): IMenuItem[] {
    const items: IMenuItem[] = [
      this.createMenuItem('', 'Główna', '', []),
      this.createMenuItem('', 'Pracownicy', 'employees', [])
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
