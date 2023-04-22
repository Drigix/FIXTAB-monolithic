import { Injectable } from '@angular/core';
import { API_URL } from '../config/api-url.model';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Activity } from '../entitites/activity.model';

export type EntityArrayResponseType = HttpResponse<Activity[]>;

@Injectable({providedIn: 'root'})
export class ActivitiesService {

  private url = API_URL + 'activity'

  constructor(
    private http: HttpClient
  ) { }

  getAll(): Observable<EntityArrayResponseType> {
    return this.http.get<Activity[]>(this.url + '/getAllActivities', {observe: 'response'});
  }

  getAllNotDeleted(): Observable<EntityArrayResponseType> {
    return this.http.get<Activity[]>(this.url + '/getAllNotDeletedActivities', {observe: 'response'});
  }

  create(Activity: Activity): Observable<HttpResponse<void>> {
    return this.http.post<HttpResponse<void>>(this.url + '/createActivity', Activity);
  }

  update(Activity: Activity): Observable<HttpResponse<void>> {
    return this.http.put<HttpResponse<void>>(this.url + '/editActivity', Activity);
  }

  delete(ActivityId: number): Observable<HttpResponse<void>> {
    return this.http.delete<HttpResponse<void>>(`${this.url}/deleteActivity/${ActivityId}`);
  }
}
