import { Injectable } from '@angular/core';
import { API_URL } from '../config/api-url.model';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { TargetObject, TargetObjectRequest } from '../entitites/object.model';
import { Observable } from 'rxjs';

export type EntityArrayResponseType = HttpResponse<TargetObject[]>;

@Injectable({providedIn: 'root'})
export class ObjectsService {

  private url = API_URL + 'targetObject'

  constructor(
    private http: HttpClient
  ) { }

  getAll(): Observable<EntityArrayResponseType> {
    return this.http.get<TargetObject[]>(this.url + '/getAllTargetObjects', {observe: 'response'});
  }

  getAllNotDeleted(): Observable<EntityArrayResponseType> {
    return this.http.get<TargetObject[]>(this.url + '/getAllNotDeletedTargetObjects', {observe: 'response'});
  }

  create(object: TargetObjectRequest): Observable<HttpResponse<void>> {
    return this.http.post<HttpResponse<void>>(this.url + '/createTargetObject', object);
  }

  update(object: TargetObject): Observable<HttpResponse<void>> {
    return this.http.put<HttpResponse<void>>(this.url + '/editTargetObject', object);
  }

  delete(objectId: number): Observable<HttpResponse<void>> {
    return this.http.delete<HttpResponse<void>>(`${this.url}/deleteTargetObject/${objectId}`);
  }

}
