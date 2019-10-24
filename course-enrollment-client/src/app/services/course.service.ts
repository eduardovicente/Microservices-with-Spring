import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Course } from "../model/Course";
import { Transaction } from "./../model/Transaction";
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

let API_URL = 'http://localhost:8765/api/course/service/';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private http: HttpClient) { }

  enroll(transaction: Transaction): Observable<any> {
    return this.http.post(API_URL + "enroll", JSON.stringify(transaction),
      {
        headers: {
          "Content-Type":"application/json; charset-UTF-8"
        }
      }
    );
  }

  findAllCourses(): Observable<any> {
    return this.http.post(API_URL,
      {
        headers: {
          "Content-Type": "application/json; charset-UTF-8"
        }
      }
    );
  }
}
