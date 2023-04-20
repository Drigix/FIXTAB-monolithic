import { Injectable } from '@angular/core';
import { API_URL } from '../config/api-url.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Request } from '../entitites/request.model';

export type EntityArrayResponseType = HttpResponse<Request[]>;

@Injectable({providedIn: 'root'})
export class RequestRepairService {

  private url = API_URL + 'requestRepair'

  constructor(
    private http: HttpClient
  ) { }

  getAll(): Observable<EntityArrayResponseType> {
    return this.http.get<Request[]>(this.url + '/getAllRequestRepairs', {observe: 'response'});
  }

  getAllNotDeleted(): Observable<EntityArrayResponseType> {
    return this.http.get<Request[]>(this.url + '/getAllNotDeletedRequestRepairs', {observe: 'response'});
  }

  create(requestRepair: Request): Observable<HttpResponse<void>> {
    return this.http.post<HttpResponse<void>>(this.url + '/createRequestRepair', requestRepair);
  }

  update(requestRepair: Request): Observable<HttpResponse<void>> {
    return this.http.put<HttpResponse<void>>(this.url + '/editRequestRepair', requestRepair);
  }

  delete(requestRepairId: number): Observable<HttpResponse<void>> {
    return this.http.delete<HttpResponse<void>>(`${this.url}/deleteRequestRepair/${requestRepairId}`);
  }

}
