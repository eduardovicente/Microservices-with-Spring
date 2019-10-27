import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from './../model/user';

let API_URL = 'http://localhost:8765/api/user/service/';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {}

  logIn(user: User): Observable<any>{
    const headers = new HttpHeaders(user ? {
      authorization: 'Basic ' + btoa(user.username + ":" + user.password)
    }:{});
    return this.http.get<any>(API_URL+"user", {headers: headers})
      .pipe(
        map( 
          response => {
            if (response) {
                localStorage.setItem('currentUser', JSON.stringify(response));
            }
            return response;
            }
          )
        );
  }
  logOut(): Observable<any>{
    return this.http.post(API_URL+"logout",{})
      .pipe(
        map(
          response => {
            localStorage.removeItem('currentUser');
          }
        )
      );
  }
  register(user: User): Observable<any>{
    return this.http.post(API_URL + "registration", JSON.stringify(user),{headers: 
                                                                              {"Content-Type":"application/json; charset-UTF-8"}
                                                                          });
  }
}
