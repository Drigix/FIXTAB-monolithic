import { Injectable } from '@angular/core';
import { API_URL } from '../config/api-url.model';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Activity } from '../entitites/activity.model';
import { Employee } from '../entitites/employee-model';
import { ActivityType } from '../entitites/activity-type.model';

export type EntityArrayResponseType = HttpResponse<ActivityType[]>;
export type EntityResponseType = HttpResponse<ActivityType>;

@Injectable({providedIn: 'root'})
export class ActivityTypeService {

  private url = API_URL + 'activity-type'

  constructor(
    private http: HttpClient
  ) { }

  getAll(): Observable<EntityArrayResponseType> {
    return this.http.get<ActivityType[]>(this.url + '/getAllActivityTypes', {observe: 'response'});
  }
}
