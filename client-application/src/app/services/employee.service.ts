import { Injectable } from '@angular/core';
import { API_URL } from '../config/api-url.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Employee, EmployeePassword } from '../entitites/employee-model';

export type CreateEmployeeResponseType = HttpResponse<string>;
export type EntityArrayResponseType = HttpResponse<Employee[]>;
export type EntityResponseType = HttpResponse<Employee>;
export type EmployeePasswordResponseType = HttpResponse<EmployeePassword>;

@Injectable({providedIn: 'root'})
export class EmployeeService {

  private url = API_URL + 'employees'

  constructor(
    private http: HttpClient
  ) { }

  getAll(): Observable<EntityArrayResponseType> {
    return this.http.get<Employee[]>(this.url + '/getAllEmployees', {observe: 'response'});
  }

  getAllNotDeleted(): Observable<EntityArrayResponseType> {
    return this.http.get<Employee[]>(this.url + '/getAllNotDeletedEmployees', {observe: 'response'});
  }

  create(employee: Employee): Observable<EmployeePasswordResponseType> {
    return this.http.post<EmployeePassword>(this.url + '/createEmployee', employee, {observe: 'response'});
  }

  changePassword(password: EmployeePassword): Observable<HttpResponse<void>> {
    return this.http.put<void>(this.url + '/changePassword', password, {observe: 'response'});
  }

  edit(employee: Employee): Observable<EntityResponseType> {
    return this.http.put<Employee>(this.url + '/editEmployee', employee, {observe: 'response'});
  }


  delete(employeeId: number): Observable<HttpResponse<void>> {
    return this.http.delete<void>(`${this.url}/deleteEmployee/${employeeId}`, {observe: 'response'});
  }

}
