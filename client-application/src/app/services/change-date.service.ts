import { Injectable } from '@angular/core';

@Injectable({providedIn: 'root'})
export class ChangeDateService {
  constructor() { }

  changeDateToString(date: Date): string {
    let stringDate = date.getFullYear().toString() + '-';
    const day = date.getDate();
    const month = date.getMonth() + 1;
    if(month < 10) {
      stringDate += '0' + month + '-';
    } else {
      stringDate += month + '-';
    }
    if(day < 10) {
      stringDate += '0' + day;
    } else {
      stringDate += day;
    }
    return stringDate;
  }

  changeTimeToString(time: Date): string {
    let stringTime = '';
    const hour = time.getHours();
    const minute = time.getMinutes();
    if(hour < 10) {
      stringTime += '0' + hour + ':';
    } else {
      stringTime += hour + ':';
    }
    if(minute < 10) {
      stringTime += '0' + minute;
    } else {
      stringTime += minute;
    }
    return stringTime;
  }
}
