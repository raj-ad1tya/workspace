import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Login } from '../models/login.model';
import { Observable, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

   public apiUrl = 'http://localhost:8080';

   register(user: User): Observable<any> {
    return this.http.post(`${this.apiUrl}/api/register`, user);
   }

   login(login : Login): Observable<any> {
     return this.http.post<{ token: string, userId: number, userRole: string }>(`${this.apiUrl}/api/login`, login)
      .pipe(
        tap(response => {
          localStorage.setItem('token', response.token);
          // this.userRoleSubject.next(response.userRole);
          // this.userIdSubject.next(response.userId);
        })
      );
   }

}